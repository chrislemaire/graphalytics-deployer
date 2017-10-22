package nl.tudelft.atlarge.gdeploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.script.RemoteSystem;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.BenchmarkJsonParser;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.ScriptWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.host.Das5ReserveWriter;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        ShellScriptBuilder builder = new ShellScriptBuilder("hello-world");
        builder.startBuildingSshRemoteScript(RemoteSystem.BASTION);

        Benchmark benchmark = BenchmarkJsonParser.fromInternalPath("/experiments/test-experiment.json");
        ScriptWriter writer = new Das5ReserveWriter(builder, benchmark);
        writer.write();

        builder.appendLine("~/scripts/cancel.sh");

        builder.stopBuildingSshRemoteScript();
        builder.stopBuildingSshRemoteScript();
        builder.finish();
    }

}
