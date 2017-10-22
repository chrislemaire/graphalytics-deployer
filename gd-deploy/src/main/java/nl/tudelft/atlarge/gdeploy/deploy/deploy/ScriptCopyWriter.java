package nl.tudelft.atlarge.gdeploy.deploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ScriptCopyWriter extends ScriptWriter {

    protected Benchmark benchmark;

    private List<String> lines;

    public ScriptCopyWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder);

        this.benchmark = benchmark;
    }

    /**
     * Reads the lines from an internally located file.
     *
     * @param internalFile String path to the internal file.
     * @throws IOException when the file doesn't exist or
     *                     something goes wrong while reading it.
     */
    public void readLines(String internalFile)
            throws IOException, URISyntaxException {
        URL url = getClass().getResource(internalFile);

        Path filePath = Paths.get(url.toURI());

        this.lines = Files.readAllLines(filePath);
    }

    /**
     * Replaces all String keys with the String values.
     *
     * @param stringMap map with the values of the benchmark
     *                  mapped to their names in the script.
     */
    private void replaceFromMap(Map<String, String> stringMap) {
        specificReplacements(stringMap);

        stringMap.forEach((key, value) ->
                lines.replaceAll(s -> s.replace(key, value)));
    }

    /**
     * Enhances the replacement map with extension specific
     * variables overrides.
     * @param map replacement map that will be enhanced during
     *            in this method.
     */
    protected void specificReplacements(Map<String, String> map) {
    }

    @Override
    public ShellScriptBuilder write() {
        this.replaceFromMap(benchmark.getVariableMap());

        lines.forEach(line -> builder.appendLine(line));

        return builder;
    }

}
