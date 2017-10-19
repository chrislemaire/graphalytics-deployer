package nl.tudelft.atlarge.gdeploy.deploy.deploy.host;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.SystemSettings;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.ScriptWriter;

public abstract class HostReserveWriter extends ScriptWriter {

    SystemSettings settings;

    public HostReserveWriter(ShellScriptBuilder builder, SystemSettings settings) {
        super(builder);

        this.settings = settings;
    }

    @Override
    public ShellScriptBuilder write() {
        return writeReserve();
    }

    public abstract ShellScriptBuilder writeReserve();

    public abstract ShellScriptBuilder writeCleanup();

}
