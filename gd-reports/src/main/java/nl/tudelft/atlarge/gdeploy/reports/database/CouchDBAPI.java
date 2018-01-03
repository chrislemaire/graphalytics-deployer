package nl.tudelft.atlarge.gdeploy.reports.database;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * API for interacting with CouchDB.
 */
public class CouchDBAPI implements JsonDatabaseAPI {

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

    private List<String> knownDatabases;

    /**
     * Empty constructor for constructing a new
     * CouchDBAPI with default values.
     */
    public CouchDBAPI() {
        try {
            updateIndexes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new CouchDBAPI from the provider
     * where the CouchDB server is hosted.
     *
     * @param provider host of the CouchDB service.
     */
    public CouchDBAPI(String provider) {
        this.provider = provider;

        try {
            updateIndexes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the indexes kept on information about
     * the couchdb service provider.
     */
    private void updateIndexes() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpUriRequest dbRequest = RequestBuilder
                .get(provider + "/_all_dbs")
                .build();

        CloseableHttpResponse response = client.execute(dbRequest);
        String entity = new Scanner(response.getEntity().getContent())
                .useDelimiter("\\A")
                .next();

        Matcher matcher = Pattern.compile("\"(.*)\"").matcher(entity);
        knownDatabases = new ArrayList<>();
        while (matcher.find()) {
            knownDatabases.add(matcher.group(1));
        }
    }

    @Override
    public boolean selectDatabase(String databaseIdentifier) throws IOException {
        if (!databaseExists(databaseIdentifier)) {
            return false;
        }

        database = databaseIdentifier;
        databaseUri = provider + "/" + database;
        return true;
    }

    @Override
    public boolean createDatabase(String databaseIdentifier) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpUriRequest databaseCreate = RequestBuilder
                .put(provider + "/" + databaseIdentifier)
                .build();

        return client.execute(databaseCreate)
                .getStatusLine()
                .getStatusCode() == 201;
    }

    @Override
    public boolean databaseExists(String databaseIdentifier) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpUriRequest databaseHead = RequestBuilder
                .head(provider + "/" + databaseIdentifier)
                .build();

        return client.execute(databaseHead)
                .getStatusLine()
                .getStatusCode() == 200;
    }

    @Override
    public boolean deleteDatabase(String databaseIdentifier) throws IOException {
        if (!databaseExists(databaseIdentifier)) {
            return false;
        }

        CloseableHttpClient client = HttpClients.createDefault();
        HttpUriRequest deleteRequest = RequestBuilder
                .delete(provider + "/" + databaseIdentifier)
                .build();

        return client.execute(deleteRequest)
                .getStatusLine()
                .getStatusCode() == 200;
    }

    @Override
    public boolean insert(String jsonContents) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpUriRequest insertRequest = RequestBuilder
                .post(databaseUri)
                .addHeader("Content-Type", "application/json")
                .setEntity(new StringEntity(jsonContents))
                .build();

        CloseableHttpResponse response = client.execute(insertRequest);
        return response
                .getStatusLine()
                .getStatusCode() == 201;
    }

    public static void main(String[] args) throws IOException {
        CouchDBAPI couchDBAPI = new CouchDBAPI();


    }

}
