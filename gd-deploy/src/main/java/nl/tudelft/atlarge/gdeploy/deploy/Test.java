package nl.tudelft.atlarge.gdeploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.util.Util;
import nl.tudelft.atlarge.gdeploy.core.script.RemoteSystem;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.core.util.UtilityTracker;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.BenchmarkJsonParser;

import java.io.IOException;
import java.net.URISyntaxException;

public class Test {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Benchmark benchmark = BenchmarkJsonParser.fromInternalPath("/experiments/test-powergraph.json");

        ShellScriptBuilder builder = new ShellScriptBuilder(
                benchmark.name + "-" + benchmark.identifier);

        DeployScriptWriter writer = new DeployScriptWriter(builder, benchmark);

        builder.startBuildingSshRemoteScript(RemoteSystem.BASTION);

        writer.write();
        builder.finish();

        UtilityTracker utilityTracker = new UtilityTracker();
        utilityTracker.registerUtilityResource(Util.pathFromInternal("/reports/metadata.json"), "metadata.json");
        utilityTracker.createUtilities();
    }

}
