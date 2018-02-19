package nl.tudelft.atlarge.gdeploy.reports.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.tudelft.atlarge.gdeploy.reports.data.View;
import nl.tudelft.atlarge.gdeploy.reports.data.couchdb.CouchDBDesign;
import nl.tudelft.atlarge.gdeploy.reports.data.couchdb.CouchDBView;
import nl.tudelft.atlarge.gdeploy.reports.util.FileUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * API for interacting with CouchDB.
 */
public class CouchDBAPI implements JsonDatabaseAPI {

    private static final String DEFAULT_DESIGN = "/couchdb/designs/default.json";

    /**
     * The Jackson object mapper that allows for
     * mapping JSON strings to files.
     */
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * The provider of the CouchDB database
     * service.
     */
    private String provider = "http://127.0.0.1:5984";

    /**
     * The currently selected database.
     */
    private String database = "default";

    /**
     * The URI to the database that is currently
     * selected and worked on.
     */
    private String databaseUri = provider + "/" + database;

    /**
     * The list of known databases as provided by
     * the /_all_dbs request.
     */
    private List<String> knownDatabases;

    /**
     * The design containing the different views
     * that can be used to query the database.
     */
    private CouchDBDesign design = mapper.readValue(
            FileUtil.readTextFromResource(DEFAULT_DESIGN), CouchDBDesign.class);

    /**
     * Empty constructor for constructing a new
     * CouchDBAPI with default values.
     */
    public CouchDBAPI() throws IOException {
        updateIndexes();
    }

    /**
     * Creates a new CouchDBAPI from the provider
     * where the CouchDB server is hosted.
     *
     * @param provider host of the CouchDB service.
     */
    public CouchDBAPI(String provider) throws IOException {
        this.provider = provider;
        updateIndexes();
    }

    @Override
    public void updateIndexes() throws IOException {
        updateKnownDatabases();
        updateViews();
    }

    /**
     * Updates the known database index.
     */
    private void updateKnownDatabases() throws IOException {
        String entity = responseContent(RequestBuilder
                .get(provider + "/_all_dbs")
                .build());

        Matcher matcher = Pattern.compile("\"(.+?)\"").matcher(entity);
        knownDatabases = new ArrayList<>();
        while (matcher.find()) {
            knownDatabases.add(matcher.group(1));
        }
    }

    /**
     * Updates the local version of the design document
     * with the version on the currently selected database.
     *
     * @return {@code true} when the update is successful,
     * {@code false} otherwise.
     */
    private boolean updateViews() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpUriRequest designGet = RequestBuilder
                .get(databaseUri + "/_design/views")
                .build();

        CloseableHttpResponse response = client.execute(designGet);
        boolean successful = response
                .getStatusLine()
                .getStatusCode() == 200;

        if (successful) {
            design = mapper.readValue(response.getEntity().getContent(), CouchDBDesign.class);
        }

        return successful;
    }

    /**
     * Flushes the current design document to the currently
     * selected CouchDB database.
     *
     * @return {@code true} when the flush is successful,
     * {@code false} otherwise.
     */
    private boolean flushViews() throws IOException {
        try {
            return respondsWith(RequestBuilder
                    .put(databaseUri + "/_design/views")
                    .addHeader("Content-Type", "application/json")
                    .setEntity(new StringEntity(mapper.writeValueAsString(design)))
                    .build(), 200);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Executes the given http request and returns whether
     * the response had the given status code.
     *
     * @param request  the request to execute.
     * @param response the expected response status code.
     * @return {@code true} when the response has the
     * expected status code, {@code false} otherwise.
     */
    private boolean respondsWith(HttpUriRequest request, int response) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

        return client.execute(request)
                .getStatusLine()
                .getStatusCode() == response;
    }

    /**
     * Executes the given http request and returns the
     * content that is given in response.
     *
     * @param request the request to execute.
     * @return a String containing the content of the
     * return entity.
     */
    private String responseContent(HttpUriRequest request) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

        return FileUtil.readText(client.execute(request)
                .getEntity()
                .getContent());
    }

    @Override
    public boolean selectDatabase(String databaseIdentifier) {
        if (!databaseExists(databaseIdentifier)) {
            return false;
        }

        database = databaseIdentifier;
        databaseUri = provider + "/" + database;
        return true;
    }

    @Override
    public boolean createDatabase(String databaseIdentifier) throws IOException {
        boolean result = respondsWith(RequestBuilder
                .put(provider + "/" + databaseIdentifier)
                .build(), 201);
        updateIndexes();

        return result;
    }

    @Override
    public boolean databaseExists(String databaseIdentifier) {
        return knownDatabases.contains(databaseIdentifier);
    }

    @Override
    public boolean deleteDatabase(String databaseIdentifier) throws IOException {
        if (!databaseExists(databaseIdentifier)) {
            return false;
        }

        boolean result = respondsWith(RequestBuilder
                .delete(provider + "/" + databaseIdentifier)
                .build(), 200);
        updateIndexes();

        return result;
    }

    @Override
    public boolean insert(String jsonContents) throws IOException {
        return respondsWith(RequestBuilder
                .post(databaseUri)
                .addHeader("Content-Type", "application/json")
                .setEntity(new StringEntity(jsonContents))
                .build(), 201);
    }

    @Override
    public String viewResults(String viewIdentifier) throws IOException {
        return responseContent(RequestBuilder
                .get(databaseUri + "/_design/views/" + viewIdentifier)
                .build());
    }

    @Override
    public boolean addView(String name, View view) throws IOException {
        if (design.views.containsKey(name)
                || !(view instanceof CouchDBView)) {
            return false;
        }

        design.views.put(name, ((CouchDBView) view));
        int count = 0;
        while (!flushViews() || count++ >= 10) {
            System.err.println("[" + count + "]\tFailed to insert view... Retrying...");
        }
        updateIndexes();
        return true;
    }

}
