package nl.tudelft.atlarge.gdeploy.reports.database;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CouchDBAPITest {

    @Test
    void testWithRunningLocalCouchDatabase() throws IOException {
        CouchDBAPI api = new CouchDBAPI();

        api.deleteDatabase("ndska");

        assertFalse(api.databaseExists("jndksad"));
        assertFalse(api.databaseExists("ndska"));
        assertTrue(api.createDatabase("ndska"));
        assertTrue(api.databaseExists("ndska"));
        assertTrue(api.selectDatabase("ndska"));
        assertTrue(api.insert("{\"empty\": \"true\"}"));

        api.deleteDatabase("ndska");
    }

}
