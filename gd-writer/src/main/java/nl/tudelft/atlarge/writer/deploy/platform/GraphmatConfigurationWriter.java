package nl.tudelft.atlarge.writer.deploy.platform;

import nl.tudelft.atlarge.writer.benchmark.data.PlatformSettings;
import nl.tudelft.atlarge.writer.script.ShellScriptBuilder;

public class GraphmatConfigurationWriter extends PlatformConfigurationWriter {

    public GraphmatConfigurationWriter(ShellScriptBuilder builder, PlatformSettings settings) {
        super(builder, settings);
    }

    @Override
    public ShellScriptBuilder write() {
        return null;
    }

}
