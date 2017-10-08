package nl.tudelft.atlarge.writer.deploy.platform;

import nl.tudelft.atlarge.writer.benchmark.data.PlatformSettings;
import nl.tudelft.atlarge.writer.deploy.ScriptWriter;
import nl.tudelft.atlarge.writer.script.ShellScriptBuilder;

public abstract class PlatformConfigurationWriter extends ScriptWriter {

    PlatformSettings settings;

    public PlatformConfigurationWriter(ShellScriptBuilder builder, PlatformSettings settings) {
        super(builder);

        this.settings = settings;
    }

}
