package nl.tudelft.atlarge.writer.deploy.platform;

import nl.tudelft.atlarge.writer.benchmark.data.PlatformSettings;
import nl.tudelft.atlarge.writer.script.ShellScriptBuilder;

public class GiraphConfigurationWriter extends PlatformConfigurationWriter {

    public GiraphConfigurationWriter(ShellScriptBuilder builder, PlatformSettings settings) {
        super(builder, settings);
    }

    @Override
    public ShellScriptBuilder write() {
        return null;
    }
}
