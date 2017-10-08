package nl.tudelft.atlarge.gdeploy.writer.deploy.platform;

import nl.tudelft.atlarge.gdeploy.writer.benchmark.data.PlatformSettings;
import nl.tudelft.atlarge.gdeploy.writer.script.ShellScriptBuilder;

public class PowergraphConfigurationWriter extends PlatformConfigurationWriter {

    public PowergraphConfigurationWriter(ShellScriptBuilder builder, PlatformSettings settings) {
        super(builder, settings);
    }

    @Override
    public ShellScriptBuilder write() {
        return null;
    }

}
