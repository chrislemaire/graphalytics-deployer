package nl.tudelft.atlarge.gdeploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.core.writer.ScriptWriter;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.BenchmarkRun;
import nl.tudelft.atlarge.gdeploy.deploy.host.HostReserveWriter;
import nl.tudelft.atlarge.gdeploy.deploy.platform.PlatformRunWriter;
import nl.tudelft.atlarge.gdeploy.deploy.sweep.SweepWriter;

public class DeployScriptWriter extends ScriptWriter {

    private Benchmark benchmark;

    private BenchmarkParameterWriter parameterWriter;

    private HostReserveWriter hostReserveWriter;

    private PlatformRunWriter platformRunWriter;

    public DeployScriptWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder);

        this.benchmark = benchmark;

        this.parameterWriter = new BenchmarkParameterWriter(builder, benchmark);
        this.hostReserveWriter = benchmark.experimentSetup
                .reserver.newInstance(builder, benchmark);
        this.platformRunWriter = benchmark.experimentSetup.targetPlatform
                .platform.newInstance(builder, benchmark);
    }

    @Override
    public ShellScriptBuilder write() {
        builder.startBuildingSshRemoteScript(benchmark.experimentSetup
                .targetSystem.host.remote);

        parameterWriter.write();
        hostReserveWriter.writeRequest();

        for (BenchmarkRun run : benchmark.runs) {
            SweepWriter sweepWriter = run.sweepType.newInstance(builder, benchmark, run);
            assert sweepWriter != null;

            sweepWriter.writeStart();
            platformRunWriter.writeSpecifics();
            sweepWriter.writeEnd();
        }

        hostReserveWriter.writeCancel();

        return builder;
    }

}
