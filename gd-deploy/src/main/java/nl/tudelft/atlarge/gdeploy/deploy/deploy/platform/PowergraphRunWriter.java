package nl.tudelft.atlarge.gdeploy.deploy.deploy.platform;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

public class PowergraphRunWriter extends PlatformRunWriter {

    private static final String PLATFORM_TEMPLATE = PowergraphRunWriter.class
            .getResource("/configs/template-platform.properties").getFile()
            .replace("%20", " ")
            .replace('\\', '/');

    public PowergraphRunWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);
    }

    @Override
    public ShellScriptBuilder write() {
        return builder;
    }

}
