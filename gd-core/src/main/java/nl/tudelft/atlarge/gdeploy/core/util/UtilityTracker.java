package nl.tudelft.atlarge.gdeploy.core.util;

import javafx.util.Pair;
import nl.tudelft.atlarge.gdeploy.core.script.RemoteSystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class UtilityTracker {

    private Map<Path, String> utilScripts;
    private Map<Path, String> utilResources;

    public UtilityTracker() {
        utilScripts = new HashMap<>();
        utilResources = new HashMap<>();
    }

    public void registerUtilityScript(Path path, String relOutPath) {
        utilScripts.put(path, relOutPath);
    }

    public void registerUtilityResource(Path path, String relOutPath) {
        utilResources.put(path, relOutPath);
    }

    private void transferFile(Pair<Path, Path> pair) {
        try {
            Files.write(pair.getValue(), Files.readAllBytes(pair.getKey()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUtilities() throws IOException {
        String utilDir = RemoteSystem.getNative().utilWrite();

        System.out.printf("Creating utility directories in '%s'\n", utilDir);
        Files.createDirectories(Paths.get(utilDir + "scripts"));
        Files.createDirectories(Paths.get(utilDir + "resources"));

        utilScripts.entrySet().stream()
                .map((e) -> new Pair<>(e.getKey(),
                        Paths.get(utilDir + "scripts/" + e.getValue())))
                .forEach(this::transferFile);
        utilResources.entrySet().stream()
                .map((e) -> new Pair<>(e.getKey(),
                        Paths.get(utilDir + "resources/" + e.getValue())))
                .forEach(this::transferFile);
    }

}
