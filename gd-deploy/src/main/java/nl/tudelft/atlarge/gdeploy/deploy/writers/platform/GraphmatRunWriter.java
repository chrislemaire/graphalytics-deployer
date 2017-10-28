package nl.tudelft.atlarge.gdeploy.deploy.writers.platform;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

import java.io.IOException;
import java.net.URISyntaxException;

public class GraphmatRunWriter extends PlatformRunWriter {

    public GraphmatRunWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);
    }

    @Override
    public ShellScriptBuilder write() {
        return writeUnsafe("/scripts/platforms/graphmat/setup-and-run.sh");
    }

}
