package nl.tudelft.atlarge.gdeploy.deploy.deploy.platform;

import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.PlatformSettings;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;

public class GiraphConfigurationWriter extends PlatformConfigurationWriter {

    public GiraphConfigurationWriter(ShellScriptBuilder builder, PlatformSettings settings) {
        super(builder, settings);
    }

    @Override
    public ShellScriptBuilder write() {
        return null;
    }
}
