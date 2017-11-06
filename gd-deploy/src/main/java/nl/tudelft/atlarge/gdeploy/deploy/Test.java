package nl.tudelft.atlarge.gdeploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.script.RemoteSystem;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.BenchmarkJsonParser;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        Benchmark benchmark = BenchmarkJsonParser.fromInternalPath("/experiments/test-powergraph.json");

        ShellScriptBuilder builder = new ShellScriptBuilder(
                benchmark.getName() + "-" + benchmark.getIdentifier());

        DeployScriptWriter writer = new DeployScriptWriter(builder, benchmark);

        builder.startBuildingSshRemoteScript(RemoteSystem.BASTION);

        writer.write();
        builder.finish();
    }

}
