package nl.tudelft.atlarge.gdeploy.writer.deploy;

import nl.tudelft.atlarge.gdeploy.writer.script.ShellScriptBuilder;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ScriptCopyWriter extends ScriptWriter {

    private List<String> lines;

    private ScriptCopyWriter(ShellScriptBuilder builder) {
        super(builder);
    }

    public static ScriptCopyWriter fromInternal(ShellScriptBuilder builder, String internal)
            throws IOException {
        ScriptCopyWriter result = new ScriptCopyWriter(builder);

        URL url = ScriptCopyWriter.class.getResource(internal);
        Path filePath = Paths.get(url.getFile()
                .replace("%20", " ")
                .replace('\\', '/'));

        result.lines = Files.readAllLines(filePath);

        return result;
    }

    @Override
    public ShellScriptBuilder write() {
        lines.forEach(line -> builder.appendLine(line));

        return builder;
    }

}
