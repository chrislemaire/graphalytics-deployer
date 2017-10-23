package nl.tudelft.atlarge.gdeploy.deploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.BenchmarkRun;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.host.HostReserveWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.platform.PlatformRunWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.sweep.SweepWriter;

public class DeployScriptWriter extends ScriptWriter {

    private Benchmark benchmark;

    private BenchmarkParameterWriter parameterWriter;

    private HostReserveWriter hostReserveWriter;

    private PlatformRunWriter platformRunWriter;

    public DeployScriptWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder);

        this.benchmark = benchmark;

        this.parameterWriter = new BenchmarkParameterWriter(builder, benchmark);
        this.hostReserveWriter = benchmark.getExperimentSetup().getTargetSystem()
                .getHost().newInstance(builder, benchmark);
        this.platformRunWriter = benchmark.getExperimentSetup().getTargetPlatform()
                .getPlatform().newInstance(builder, benchmark);
    }

    @Override
    public ShellScriptBuilder write() {
        builder.startBuildingSshRemoteScript(benchmark.getExperimentSetup()
                .getTargetSystem().getHost().getRemote());

        parameterWriter.write();
        hostReserveWriter.write();

        for (BenchmarkRun run : benchmark.getRuns()) {
            SweepWriter sweepWriter = run.getSweepType().newInstance(builder, benchmark);
            assert sweepWriter != null;

            sweepWriter.writeStart();
            platformRunWriter.write();
            sweepWriter.writeEnd();
        }

        return builder;
    }

}
