package nl.tudelft.atlarge.gdeploy.deploy.deploy.platform;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

public class GraphmatConfigurationWriter extends PlatformConfigurationWriter {

    public GraphmatConfigurationWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);
    }

    @Override
    public ShellScriptBuilder write() {
        return builder;
    }

}
