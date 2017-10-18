package nl.tudelft.atlarge.gdeploy.deploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.script.PythonScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.host.HostReserveWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.platform.PlatformConfigurationWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.sweep.SweepWriter;

public class DeployScriptWriter extends ScriptWriter {

    private Benchmark benchmark;

    private SweepWriter sweepWriter;

    private HostReserveWriter hostReserveWriter;

    private PlatformConfigurationWriter platformConfigurationWriter;

    public DeployScriptWriter(PythonScriptBuilder builder, Benchmark benchmark) {
        super(builder);

        this.benchmark = benchmark;
    }

    @Override
    public PythonScriptBuilder write() {
        return null;
    }

}
