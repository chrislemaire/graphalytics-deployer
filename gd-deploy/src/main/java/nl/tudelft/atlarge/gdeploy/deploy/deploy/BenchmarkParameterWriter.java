package nl.tudelft.atlarge.gdeploy.deploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

import java.io.IOException;
import java.net.URISyntaxException;

public class BenchmarkParameterWriter extends ScriptCopyWriter {

    public BenchmarkParameterWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);

        try {
            this.readLines("/scripts/parameter-set.sh");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
