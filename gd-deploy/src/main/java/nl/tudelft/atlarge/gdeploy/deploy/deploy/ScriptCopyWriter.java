package nl.tudelft.atlarge.gdeploy.deploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ScriptCopyWriter extends ScriptWriter {

    private List<String> lines;

    private ScriptCopyWriter(ShellScriptBuilder builder) {
        super(builder);
    }

    public void readLines(String internalFile)
            throws IOException {
        ScriptCopyWriter result = new ScriptCopyWriter(builder);

        URL url = ScriptCopyWriter.class.getResource(internalFile);
        Path filePath = Paths.get(url.getFile()
                .replace("%20", " ")
                .replace('\\', '/'));

        result.lines = Files.readAllLines(filePath);
    }

    public void replaceFromMap(Map<String, String> stringMap) {
        stringMap.forEach((scriptVarName, replacement) ->
                lines.forEach(s -> s = s.replace(scriptVarName, replacement)));
    }

    @Override
    public ShellScriptBuilder write() {
        lines.forEach(line -> builder.appendLine(line));

        return builder;
    }

}
