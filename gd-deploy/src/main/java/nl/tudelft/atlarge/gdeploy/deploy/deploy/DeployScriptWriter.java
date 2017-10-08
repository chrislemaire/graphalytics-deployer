package nl.tudelft.atlarge.gdeploy.deploy.deploy;

import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.host.HostReserveWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.platform.PlatformConfigurationWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.sweep.SweepWriter;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;

public class DeployScriptWriter extends ScriptWriter {

    private Benchmark benchmark;

    private SweepWriter sweepWriter;

    private HostReserveWriter hostReserveWriter;

    private PlatformConfigurationWriter platformConfigurationWriter;

    public DeployScriptWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder);

        this.benchmark = benchmark;
    }

    @Override
    public ShellScriptBuilder write() {
        return null;
    }

}
