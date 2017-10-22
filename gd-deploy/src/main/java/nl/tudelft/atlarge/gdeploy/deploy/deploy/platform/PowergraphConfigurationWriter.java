package nl.tudelft.atlarge.gdeploy.deploy.deploy.platform;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

public class PowergraphConfigurationWriter extends PlatformConfigurationWriter {

    private static final String PLATFORM_TEMPLATE = PowergraphConfigurationWriter.class
            .getResource("/configs/template-platform.properties").getFile()
            .replace("%20", " ")
            .replace('\\', '/');

    public PowergraphConfigurationWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);
    }

    @Override
    public ShellScriptBuilder write() {
        return builder;
    }

}
