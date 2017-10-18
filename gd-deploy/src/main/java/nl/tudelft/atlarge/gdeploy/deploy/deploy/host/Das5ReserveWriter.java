package nl.tudelft.atlarge.gdeploy.deploy.deploy.host;

import nl.tudelft.atlarge.gdeploy.core.script.PythonScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.SystemSettings;

public class Das5ReserveWriter extends HostReserveWriter {

    public Das5ReserveWriter(PythonScriptBuilder builder, SystemSettings settings) {
        super(builder, settings);
    }

    @Override
    public PythonScriptBuilder writeReserve() {
        return null;
    }

    @Override
    public PythonScriptBuilder writeCleanup() {
        return null;
    }

}
