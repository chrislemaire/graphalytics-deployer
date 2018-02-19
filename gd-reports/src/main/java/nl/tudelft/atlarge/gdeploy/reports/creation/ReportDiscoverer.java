package nl.tudelft.atlarge.gdeploy.reports.creation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to discover new report metadata.json
 * files and categorize them in any directory.
 */
public class ReportDiscoverer {

    /**
     * The directory names that will be excluded from
     * discovery. These directories are typically the short-
     * named directories found inside a report.
     * (These include 'log/', 'json/', 'html/').
     */
    protected static final List<String> EXCLUDED_DIRECTORIES = Arrays.asList(
            "log", "logs", "json", "html", "config", "configs");

    /**
     * A map containing mapping report metadata by the
     * report directories they belong to.
     */
    protected Map<File, String> reports;

    /**
     * Construct the report discoverer.
     */
    public ReportDiscoverer() {
        reports = new HashMap<>();
    }

    /**
     * Helper function to read a single metadata file in full
     * and return the combined String representing the content
     * of the metadata file.
     *
     * @param metadataFile the metadata file to read.
     * @return single String representing the content of the
     * metadata file.
     */
    protected String readMetadataFile(File metadataFile) {
        try {
            return String.join("", Files.readAllLines(metadataFile.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Discovers metadata.json files in the current directory.
     *
     * @param root directory or file to discover.
     */
    public void discover(File root) {
        File[] subFiles = root.listFiles();
        if (subFiles != null) {
            Arrays.stream(subFiles)
                    .filter(f -> !EXCLUDED_DIRECTORIES.contains(f.getName()))
                    .forEach(this::discover);
        } else if ("metadata.json".equals(root.getName())) {
            reports.put(root.getParentFile(), readMetadataFile(root));
        }
    }

    /**
     * Gets the map mapping report metadata by their report
     * root directories.
     *
     * @return Map of metadata by their report directory:
     * {@link #reports}.
     */
    public Map<File, String> getReportMap() {
        return reports;
    }

}
