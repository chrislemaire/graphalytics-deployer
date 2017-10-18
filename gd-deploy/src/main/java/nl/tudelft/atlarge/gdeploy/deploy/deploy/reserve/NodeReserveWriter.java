package nl.tudelft.atlarge.gdeploy.deploy.deploy.reserve;

import nl.tudelft.atlarge.gdeploy.core.script.PythonScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.SystemSettings;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.ScriptWriter;

import java.io.IOException;

public abstract class NodeReserveWriter extends ScriptWriter {

    public static final String mainNode = "MAIN_NODE";
    public static final String secondaryNode = "SECONDARY_NODE";
    public static final String nodeList = "RESERVED_NODES";

    SystemSettings settings;

    public NodeReserveWriter(PythonScriptBuilder builder, SystemSettings settings) {
        super(builder);

        this.settings = settings;
    }

    abstract PythonScriptBuilder writeNodeReserving() throws IOException;

    public abstract PythonScriptBuilder writeNodeCancel();

    @Override
    public PythonScriptBuilder write() {
        writeNodeReserving();

        return builder;
    }

}
