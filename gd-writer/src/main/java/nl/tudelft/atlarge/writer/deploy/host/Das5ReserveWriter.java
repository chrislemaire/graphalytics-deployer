package nl.tudelft.atlarge.writer.deploy.host;

import nl.tudelft.atlarge.writer.benchmark.data.SystemSettings;
import nl.tudelft.atlarge.writer.script.ShellScriptBuilder;

public class Das5ReserveWriter extends HostReserveWriter {

    public Das5ReserveWriter(ShellScriptBuilder builder, SystemSettings settings) {
        super(builder, settings);
    }

    @Override
    public ShellScriptBuilder writeReserve() {
        return null;
    }

    @Override
    public ShellScriptBuilder writeCleanup() {
        return null;
    }

}
