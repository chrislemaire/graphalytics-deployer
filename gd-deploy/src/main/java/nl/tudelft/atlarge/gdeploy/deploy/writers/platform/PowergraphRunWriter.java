package nl.tudelft.atlarge.gdeploy.deploy.writers.platform;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

public class PowergraphRunWriter extends PlatformRunWriter {

    public PowergraphRunWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);
    }

    @Override
    public ShellScriptBuilder write() {
        return builder;
    }

}
