package nl.tudelft.atlarge.gdeploy.deploy.deploy.host;

import nl.tudelft.atlarge.gdeploy.core.script.PythonScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.SystemSettings;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.ScriptWriter;

public abstract class HostReserveWriter extends ScriptWriter {

    SystemSettings settings;

    public HostReserveWriter(PythonScriptBuilder builder, SystemSettings settings) {
        super(builder);

        this.settings = settings;
    }

    @Override
    public PythonScriptBuilder write() {
        return writeReserve();
    }

    public abstract PythonScriptBuilder writeReserve();

    public abstract PythonScriptBuilder writeCleanup();

}
