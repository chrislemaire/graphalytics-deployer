package nl.tudelft.atlarge.gdeploy.reports.creation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class ReportFiler {

    /**
     * The ReportDiscoverer that will find the reports
     * in the root directory of this report collection.
     */
    private ReportDiscoverer discoverer;

    /**
     * Constructs a new ReportFiler and indexes
     * the reports in the given directory.
     *
     * @param rootDirectory the directory in which
     *                      all reports are located.
     */
    public ReportFiler(File rootDirectory) {
        assert rootDirectory != null;

        this.discoverer = new ReportDiscoverer();
        this.discoverer.discover(rootDirectory);
    }

    /**
     * Constructs a new ReportFiler by initializing
     * the ReportDiscoverer.
     */
    public ReportFiler() {
        this.discoverer = new ReportDiscoverer();
    }

    /**
     * Adds a directory by discovering all reports in
     * that directory.
     *
     * @param directory the directory to add/discover.
     */
    public void addDirectory(File directory) {
        assert discoverer != null;

        discoverer.discover(directory);
    }

    /**
     * Reads the JSON results report in the discovered
     * report directory.
     *
     * @param reportRoot the root of a single report directory.
     * @return String of contents read from the results JSON.
     */
    private String readJsonResults(File reportRoot) {
        Path targetJson;
        try {
            Path jsonDir = Paths.get(reportRoot.getPath(), "json");
            targetJson = jsonDir.resolve("results.json");
        } catch (InvalidPathException e) {
            System.err.println("Couldn't find ");
            return "{}";
        }

        try {
            return String.join("", Files.readAllLines(targetJson));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String combineResults(File reportRoot, String metadata) {
        // Generate String data for in the resulting collection json.
        String jsonResults = readJsonResults(reportRoot);
        String reportDirectory = reportRoot.getAbsolutePath();

        // Break the json metadata open before the last closing bracket.
        String result = metadata.substring(0, metadata.lastIndexOf('}') - 1);

        // Add results.json and the report directory to the result string.
        return result + ",\"results\": \"" + jsonResults + "\","
                + "\"localDirectory\": \"" + reportDirectory + "\"}";
    }

    public abstract void fileReports();

}
