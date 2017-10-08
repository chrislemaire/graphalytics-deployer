package nl.tudelft.atlarge.gdeploy.writer.deploy.platform;

import nl.tudelft.atlarge.gdeploy.writer.benchmark.data.PlatformSettings;
import nl.tudelft.atlarge.gdeploy.writer.script.ShellScriptBuilder;

public class GiraphConfigurationWriter extends PlatformConfigurationWriter {

    public GiraphConfigurationWriter(ShellScriptBuilder builder, PlatformSettings settings) {
        super(builder, settings);
    }

    @Override
    public ShellScriptBuilder write() {
        return null;
    }
}
