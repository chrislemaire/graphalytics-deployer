package nl.tudelft.atlarge.gdeploy.reports.recreation;

import java.io.File;
import java.util.Arrays;

public class ReportDiscoverer extends nl.tudelft.atlarge.gdeploy.reports.creation.ReportDiscoverer {

    private boolean containsDirectory(File[] subFiles, String dirName) {
        assert dirName != null;

        return Arrays.stream(subFiles).anyMatch(f -> f.isDirectory() && dirName.equals(f.getName()));
    }

    private boolean containsFile(File[] subFiles, String fileName) {
        assert fileName != null;

        return Arrays.stream(subFiles).anyMatch(f -> f.isFile() && fileName.equals(f.getName()));
    }

    @Override
    public void discover(File root) {
        File[] subFiles = root.listFiles();
        if (subFiles != null && containsDirectory(subFiles, "json")
                && containsDirectory(subFiles, "log") && containsDirectory(subFiles, "html")
                && !containsFile(subFiles, "metadata.json")) {
            reports.put(root, null);
        } else if (subFiles != null) {
            Arrays.stream(subFiles)
                    .filter(f -> !EXCLUDED_DIRECTORIES.contains(f.getName()))
                    .forEach(this::discover);
        }
    }

}
