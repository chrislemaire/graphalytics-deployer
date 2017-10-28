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

/**
 * Main writer of this package. This ScriptWriter
 * is able to read the contents of a script into memory
 * and operate on them. This class can replace variables
 * in the 'template' script with actual parameters.
 * These variables can be recognized by the '%' characters
 * surround them. For example: '%variable%' is a script
 * variable that should be replaced by this ScriptWriter.
 */
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
     * An unsafe write method that can only be used by
     * subclasses of this class. This method reads the
     * lines of the given file and immediately writes
     * these lines to the {@link ShellScriptBuilder}.
     * When an exception is encountered, it is printed
     * and thereafter an IllegalArgumentException is thrown.
     *
     * @param internalFile the file containing the template
     *                     script to be written to the builder.
     */
    protected ShellScriptBuilder writeUnsafe(String internalFile) {
        try {
            this.readLines(internalFile);
            return write();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "Internal file '" + internalFile + "' is not valid.");
        }
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
     *
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
