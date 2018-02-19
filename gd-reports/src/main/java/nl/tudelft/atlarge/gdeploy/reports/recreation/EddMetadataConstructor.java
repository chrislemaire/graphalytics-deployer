package nl.tudelft.atlarge.gdeploy.reports.recreation;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EddMetadataConstructor extends MetadataConstructor {

    private static final Pattern TPM_PATTERN = Pattern.compile(".*threads-vs-processes-(\\d+)-(\\d+)_-(\\d+).*");

    /**
     * Constructs a new MetadataConstructor from a
     * given directory name and report json.
     *
     * @param directoryName name of the report directory.
     * @param reportJson    String contents of the JSON report.
     */
    public EddMetadataConstructor(String directoryName, String reportJson) {
        super(directoryName, reportJson);
    }

    @Override
    protected void replaceByDirectory(Map<String, String> replacements) {
        Matcher tpMatcher = TPM_PATTERN.matcher(directoryName);
        if (tpMatcher.find()) {
            replacements.put("%THREADS_USED%", String.valueOf(tpMatcher.group(1)));
            replacements.put("%PROCS_USED%", String.valueOf(tpMatcher.group(2)));
            replacements.put("%MACHINES_USED%", String.valueOf(tpMatcher.group(3)));
        } else {
            System.err.println("Couldn't parse thread-process number");
        }

        String[] split = directoryName.split("[-_]");
        String identifier = split[1];

        char platformC = identifier.charAt(0);
        switch (platformC) {
            case 'G':
                platform = Platform.GRAPHMAT;
                break;
            case 'P':
                platform = Platform.POWERGRAPH;
                break;
            default:
                System.err.println("Unrecognized type: " + platformC);
                return;
        }

        char affinity = identifier.charAt(1);
        switch (affinity) {
            case 'C':
                replacements.put("%AFFINITY%", "compact");
                break;
            case 'S':
                replacements.put("%AFFINITY%", "scatter");
                break;
            default:
                System.err.println("Unrecognized affinity type: " + affinity);
                return;
        }

        char size = identifier.charAt(2);
        switch (size) {
            case 'M':
                replacements.put("%DATASETS%", "\"graph500-22\", \"datagen-8_0-fb\"");
                break;
            case 'L':
                replacements.put("%DATASETS%", "\"graph500-25\", \"datagen-8_5-fb\"");
                break;
            default:
                System.err.println("Unrecognized size: " + size);
                return;
        }

        char node = identifier.charAt(3);
        switch (node) {
            case 'S':
                replacements.put("%CLUSTER_MODE%", "SNC-4");
                replacements.put("%MEMORY_MODE%", "CACHE");
                break;
            case 'A':
                replacements.put("%CLUSTER_MODE%", "ALL-TO-ALL");
                replacements.put("%MEMORY_MODE%", "FLAT");
                break;
            default:
                System.err.println("Unrecognized node type: " + node);
                return;
        }

        replacements.put("%PROJ_NAME%", split[0] + split[1]);
        replacements.put("%PROJ_ID%", split[2]);
        replacements.put("%RUN_ID%", split[split.length - 1]);
    }

}
