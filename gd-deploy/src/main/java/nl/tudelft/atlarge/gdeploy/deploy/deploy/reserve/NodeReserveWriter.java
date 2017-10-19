package nl.tudelft.atlarge.gdeploy.deploy.deploy.reserve;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.SystemSettings;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.ScriptWriter;

import java.io.IOException;

public abstract class NodeReserveWriter extends ScriptWriter {

    public static final String mainNode = "MAIN_NODE";
    public static final String secondaryNode = "SECONDARY_NODE";
    public static final String nodeList = "RESERVED_NODES";

    SystemSettings settings;

    public NodeReserveWriter(ShellScriptBuilder builder, SystemSettings settings) {
        super(builder);

        this.settings = settings;
    }

    abstract ShellScriptBuilder writeNodeReserving() throws IOException;

    public abstract ShellScriptBuilder writeNodeCancel();

    @Override
    public ShellScriptBuilder write() {
        writeNodeReserving();

        return builder;
    }

}
