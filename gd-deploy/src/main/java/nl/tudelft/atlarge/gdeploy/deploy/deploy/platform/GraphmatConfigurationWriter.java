package nl.tudelft.atlarge.gdeploy.deploy.deploy.platform;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.PlatformSettings;

public class GraphmatConfigurationWriter extends PlatformConfigurationWriter {

    public GraphmatConfigurationWriter(ShellScriptBuilder builder, PlatformSettings settings) {
        super(builder, settings);
    }

    @Override
    public ShellScriptBuilder write() {
        return null;
    }

}
