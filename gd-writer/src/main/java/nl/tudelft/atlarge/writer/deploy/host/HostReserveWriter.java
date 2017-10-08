package nl.tudelft.atlarge.writer.deploy.host;

import nl.tudelft.atlarge.writer.benchmark.data.SystemSettings;
import nl.tudelft.atlarge.writer.deploy.ScriptWriter;
import nl.tudelft.atlarge.writer.script.ShellScriptBuilder;

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
