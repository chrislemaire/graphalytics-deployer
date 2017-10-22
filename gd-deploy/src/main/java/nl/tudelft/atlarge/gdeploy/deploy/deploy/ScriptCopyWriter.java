package nl.tudelft.atlarge.gdeploy.deploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

import java.io.IOException;
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
    void readLines(String internalFile)
            throws IOException {
        URL url = ScriptCopyWriter.class.getResource(internalFile);
        Path filePath = Paths.get(url.getFile()
                .replace("%20", " ")
                .replace('\\', '/'));

        this.lines = Files.readAllLines(filePath);
    }

    /**
     * Replaces all String keys with the String values.
     *
     * @param stringMap map with the values of the benchmark
     *                  mapped to their names in the script.
     */
    private void replaceFromMap(Map<String, String> stringMap) {
        stringMap.forEach((scriptVarName, replacement) ->
                lines.forEach(s -> s = s.replace(scriptVarName, replacement)));
    }

    @Override
    public ShellScriptBuilder write() {
        this.replaceFromMap(benchmark.getVariableMap());

        lines.forEach(line -> builder.appendLine(line));

        return builder;
    }

}
