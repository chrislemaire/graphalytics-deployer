package nl.tudelft.atlarge.gdeploy.writer.deploy.host;

import nl.tudelft.atlarge.gdeploy.writer.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.writer.benchmark.data.SystemSettings;

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
