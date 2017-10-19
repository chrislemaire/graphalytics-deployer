package nl.tudelft.atlarge.gdeploy.deploy.deploy.reserve;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.SystemSettings;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.ScriptCopyWriter;

import java.io.IOException;

public class Das5NodeReserveWriter extends NodeReserveWriter {

    public Das5NodeReserveWriter(ShellScriptBuilder builder, SystemSettings settings)
            throws IOException {
        super(builder, settings);
    }

    @Override
    ShellScriptBuilder writeNodeReserving() throws IOException {
        ScriptCopyWriter writer = ScriptCopyWriter.fromInternal(builder,
                "/scripts/das5-preserve/request.sh");

        writer.replaceParameter(1, "");

        return writer.write();
    }

    @Override
    public ShellScriptBuilder writeNodeCancel() {
        return null;
    }

}
