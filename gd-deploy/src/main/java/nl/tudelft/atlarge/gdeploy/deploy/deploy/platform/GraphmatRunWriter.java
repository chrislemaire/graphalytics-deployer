package nl.tudelft.atlarge.gdeploy.deploy.deploy.platform;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

import java.io.IOException;
import java.net.URISyntaxException;

public class GraphmatRunWriter extends PlatformRunWriter {

    public GraphmatRunWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);

        try {
            this.readLines("/scripts/platforms/graphmat/setup-and-run.sh");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
