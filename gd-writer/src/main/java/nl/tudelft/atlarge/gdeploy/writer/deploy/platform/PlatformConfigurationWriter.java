package nl.tudelft.atlarge.gdeploy.writer.deploy.platform;

import nl.tudelft.atlarge.gdeploy.writer.benchmark.data.PlatformSettings;
import nl.tudelft.atlarge.gdeploy.writer.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.writer.deploy.ScriptWriter;

public abstract class PlatformConfigurationWriter extends ScriptWriter {

    PlatformSettings settings;

    public PlatformConfigurationWriter(ShellScriptBuilder builder, PlatformSettings settings) {
        super(builder);

        this.settings = settings;
    }

}
