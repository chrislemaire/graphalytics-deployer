package nl.tudelft.atlarge.gdeploy.writer.deploy;

import nl.tudelft.atlarge.gdeploy.writer.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.writer.deploy.host.HostReserveWriter;
import nl.tudelft.atlarge.gdeploy.writer.deploy.platform.PlatformConfigurationWriter;
import nl.tudelft.atlarge.gdeploy.writer.deploy.sweep.SweepWriter;
import nl.tudelft.atlarge.gdeploy.writer.script.ShellScriptBuilder;

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
