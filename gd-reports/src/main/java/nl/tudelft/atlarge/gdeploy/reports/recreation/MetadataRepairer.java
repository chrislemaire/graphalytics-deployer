package nl.tudelft.atlarge.gdeploy.reports.recreation;

import nl.tudelft.atlarge.gdeploy.core.util.Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetadataRepairer {

    private ReportDiscoverer discoverer;

    /**
     * Constructs a new MetadataRepairer and indexes
     * the reports in the given directory.
     *
     * @param rootDirectory the directory in which
     *                      all reports are located.
     */
    public MetadataRepairer(File rootDirectory) {
        assert rootDirectory != null;

        this.discoverer = new ReportDiscoverer();
        this.discoverer.discover(rootDirectory);
    }

    /**
     * Repairs the currently discovered reports.
     */
    public void repair(Map<String, String> replacements) {
        discoverer.getReportMap().forEach((file, metadata) -> {
            System.out.println("Repairing '" + file.getName() + "'");
            Map<String, String> localReplacements = new HashMap<>(replacements);

            List<String> meta = (metadata == null) ? null : Arrays.asList(metadata.split("\n"));
            String directoryName = file.getName();
            String reportJson;

            try {
                BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                localReplacements.put("%RUN_START%", String.valueOf(attr.creationTime()));
            } catch (IOException e) {
                System.err.println("Could not produce creation time attribute.");
            }

            try {
                reportJson = Util.fileContents(new File(file.getAbsolutePath() + "/json/results.json"));
            } catch (IOException e) {
                System.err.println("Could not find results.json file for directory '" + directoryName + "'");
                return;
            }

            try {
                MetadataConstructor constructor = new EddMetadataConstructor(directoryName, reportJson);
                if (meta == null) {
                    constructor.createMetadata(localReplacements);
                } else {
                    constructor.createMetadata(localReplacements, meta);
                }

            } catch (MetadataConstructionException e) {
                System.err.println("Metadata construction failed. Continuing...");
                System.err.println("Message: '" + e.getMessage() + "'");
            } catch (IOException e) {
                System.err.println("An exception was raised when reading or writing to file:");
                e.printStackTrace();
            }
        });
    }

}
