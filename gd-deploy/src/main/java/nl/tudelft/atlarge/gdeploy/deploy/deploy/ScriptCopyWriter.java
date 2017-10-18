package nl.tudelft.atlarge.gdeploy.deploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.script.PythonScriptBuilder;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ScriptCopyWriter extends ScriptWriter {

    private List<String> lines;

    private ScriptCopyWriter(PythonScriptBuilder builder) {
        super(builder);
    }

    public static ScriptCopyWriter fromInternal(PythonScriptBuilder builder, String internal)
            throws IOException {
        ScriptCopyWriter result = new ScriptCopyWriter(builder);

        URL url = ScriptCopyWriter.class.getResource(internal);
        Path filePath = Paths.get(url.getFile()
                .replace("%20", " ")
                .replace('\\', '/'));

        result.lines = Files.readAllLines(filePath);

        return result;
    }

    public void replaceParameter(int parameter, String replacement) {
        lines.forEach(l -> l = l.replace("$" + parameter, replacement));
    }

    @Override
    public PythonScriptBuilder write() {
        lines.forEach(line -> builder.appendLine(line));

        return builder;
    }

}
