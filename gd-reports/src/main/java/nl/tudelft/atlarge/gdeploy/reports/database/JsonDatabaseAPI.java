package nl.tudelft.atlarge.gdeploy.reports.database;

import nl.tudelft.atlarge.gdeploy.reports.data.View;

import java.io.IOException;

/**
 * Interface for inserting json content into
 * any arbitrary document database.
 */
public interface JsonDatabaseAPI {

    /**
     * Updates the indexes kept on information about
     * the json service provider.
     */
    void updateIndexes() throws IOException;

    /**
     * Selects the database identified by the given
     * identifier as the working set.
     *
     * @param databaseIdentifier identifier of the
     *                           database to select.
     * @return whether the selection was successful.
     * I.e. whether the database existed.
     */
    boolean selectDatabase(String databaseIdentifier) throws IOException;

    /**
     * Creates a database with the given identifier
     * if one did not exist yet.
     *
     * @param databaseIdentifier identifier of the
     *                           database to create.
     * @return {@code true} when the database was
     * successfully created, {@code false} when it
     * already existed or something went wrong.
     */
    boolean createDatabase(String databaseIdentifier) throws IOException;

    /**
     * Finds whether a database with the given
     * identifier already exists.
     *
     * @param databaseIdentifier identifier of the
     *                           database to check.
     * @return {@code true} when the database is
     * present, {@code false} otherwise.
     */
    boolean databaseExists(String databaseIdentifier) throws IOException;

    /**
     * Deletes the database with given identified
     * if one exists.
     *
     * @param databaseIdentifier identifier of the
     *                           database to delete.
     * @return {@code true} when a database with the
     * given identifier existed and is correctly deleted,
     * {@code false} otherwise.
     */
    boolean deleteDatabase(String databaseIdentifier) throws IOException;

    /**
     * Inserts a single JSON object into the currently
     * selected database.
     *
     * @param jsonContents the JSON object to insert
     *                     into the database.
     * @return {@code true} when the insertion was executed
     * successfully, {@code false} otherwise.
     */
    boolean insert(String jsonContents) throws IOException;

    /**
     * Queries the database for the given view and
     * returns the results from the view if any exist.
     *
     * @param viewIdentifier query to be executed.
     * @return the result of the query in JSON format.
     */
    String viewResults(String viewIdentifier) throws IOException;

    /**
     * Adds a single view to the list of views that is
     * kept by the database.
     *
     * @param name The name of the View, otherwise known
     *             as the view-identifier.
     * @param view View object representing the view to
     *             add.
     * @return {@code true} when the addition of the view
     * was successful, {@code false} otherwise.
     */
    boolean addView(String name, View view) throws IOException;

}
