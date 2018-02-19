package nl.tudelft.atlarge.gdeploy.reports.recreation;

import nl.tudelft.atlarge.gdeploy.core.util.Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MetadataConstructor {

    protected interface PlatformChecker {
        void check(Map<String, String> replacements) throws MetadataConstructionException;
    }

    private final PlatformChecker graphmatChecker = replacements -> {
        final String procs = findValue("platform.graphmat.num-procs");
        final String threads = findValue("platform.graphmat.num-threads");
        final String machines = findValue("platform.graphmat.num-machines");

        if (procs == null || threads == null || machines == null) {
            throw new MetadataConstructionException("Could not find number of processor/" +
                    "thread/machines option in platform properties");
        }

        if ((!replacements.containsKey("%PROCS_USED%") || !procs.equals(replacements.get("%PROCS_USED%")))
                && (!replacements.containsKey("%THREADS_USED%") || !threads.equals(replacements.get("%THREADS_USED%")))
                && (!replacements.containsKey("%MACHINES_USED%") || !machines.equals(replacements.get("%MACHINES_USED%")))) {
            throw new MetadataConstructionException("Could not match name-based number of" +
                    "processors/threads/machines with platform properties based number.");
        }

        replacements.put("%PROCS_USED%", procs);
        replacements.put("%THREADS_USED%", threads);
        replacements.put("%MACHINES_USED%", machines);
        replacements.put("%AFFINITY%", findValue("platform.graphmat.affinity"));
        replacements.put("%REPETITIONS%", findValue("benchmark.custom.repetitions"));

        replacements.put("%PLATFORM_NAME%", findValue("platform.name"));
        replacements.put("%PLATFORM_HOME%", findValue("platform.graphmat.home"));

        replacements.put("%RUN_DURATION%", findValue("duration"));
    };

    protected enum Platform {
        GRAPHMAT, POWERGRAPH;
    }

    /**
     * Name of the directory that the report was
     * found to be in.
     */
    protected String directoryName;

    /**
     * The platform that the report is from.
     */
    protected Platform platform;

    /**
     * The report json file read as one large
     * String.
     */
    private String reportJson;

    /**
     * Constructs a new MetadataConstructor from a
     * given directory name and report json.
     *
     * @param directoryName name of the report directory.
     * @param reportJson    String contents of the JSON report.
     */
    public MetadataConstructor(String directoryName, String reportJson) {
        this.directoryName = directoryName;
        this.reportJson = reportJson;
    }

    /**
     * Finds a single value of a key-value pair in
     * the report JSON.
     *
     * @param key String key of the kv-pair to find.
     * @return the value of the found kv-pair or {@code null}
     * if none is found.
     */
    private String findValue(String key) {
        Pattern patt = Pattern.compile("\"" + key.replace(".", "[.]") + "\":\\s*\"(.*)\"");
        Matcher matcher = patt.matcher(reportJson);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    /**
     * Creates replacements by the name of the directory
     * in which the current report was found.
     *
     * @param replacements the replacements map to which
     *                     replacement-string-pairs will be
     *                     added.
     * @throws MetadataConstructionException when the format
     *                                       of the directory name
     *                                       is not as expected.
     */
    protected abstract void replaceByDirectory(Map<String, String> replacements)
            throws MetadataConstructionException;

    private void replaceByJsonResults(Map<String, String> replacements)
            throws MetadataConstructionException {
        switch (platform) {
            case GRAPHMAT:
                graphmatChecker.check(replacements);
                break;
            default:
                System.err.println("Unsupported platform: " + platform);
                break;
        }
    }

    public String createMetadata() throws IOException {
        return createMetadata(new HashMap<>());
    }

    public String createMetadata(Map<String, String> replacements)
            throws IOException {
        List<String> metadata = Util.internalContentsList("/reports/metadata.json");
        return createMetadata(replacements, metadata);
    }

    public String createMetadata(Map<String, String> replacements, List<String> metadata)
            throws IOException {
        replaceByDirectory(replacements);
        replaceByJsonResults(replacements);

        replacements.forEach((key, value) ->
                metadata.replaceAll(s -> s.replace(key, value)));

        return String.join("\n", metadata);
    }

}
