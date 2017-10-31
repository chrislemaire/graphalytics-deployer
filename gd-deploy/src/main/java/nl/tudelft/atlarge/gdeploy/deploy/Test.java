package nl.tudelft.atlarge.gdeploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.script.RemoteSystem;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.BenchmarkJsonParser;
import nl.tudelft.atlarge.gdeploy.deploy.writers.DeployScriptWriter;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
//        ShellScriptBuilder builder = new ShellScriptBuilder("hello-world");
//        builder.startBuildingSshRemoteScript(RemoteSystem.BASTION);
//
//        Benchmark benchmark = BenchmarkJsonParser.fromInternalPath("/experiments/test-experiment.json");
//
//        new BenchmarkParameterWriter(builder, benchmark).write();
//        new Das5ReserveWriter(builder, benchmark).write();
//
//        new SingleRunWriter(builder, benchmark).writeStartSpecifics();
//        new SingleRunWriter(builder, benchmark).writeEnd();
//
//        builder.appendLine("~/scripts/cancel.sh");
//
//        builder.stopBuildingSshRemoteScript();
//        builder.stopBuildingSshRemoteScript();
//        builder.finish();

        ShellScriptBuilder builder = new ShellScriptBuilder("thread-process-graphmat");
        Benchmark benchmark = BenchmarkJsonParser.fromInternalPath("/experiments/test-experiment.json");

        DeployScriptWriter writer = new DeployScriptWriter(builder, benchmark);

        builder.startBuildingSshRemoteScript(RemoteSystem.BASTION);

        writer.write();
        builder.finish();
    }

}
