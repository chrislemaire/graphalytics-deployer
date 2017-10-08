package nl.tudelft.atlarge.gdeploy.deploy.deploy.host;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.SystemSettings;

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
