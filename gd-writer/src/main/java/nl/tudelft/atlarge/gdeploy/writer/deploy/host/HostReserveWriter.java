package nl.tudelft.atlarge.gdeploy.writer.deploy.host;

import nl.tudelft.atlarge.gdeploy.writer.benchmark.data.SystemSettings;
import nl.tudelft.atlarge.gdeploy.writer.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.writer.deploy.ScriptWriter;

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
