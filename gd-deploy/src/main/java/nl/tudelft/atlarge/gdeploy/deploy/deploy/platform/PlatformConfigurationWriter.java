package nl.tudelft.atlarge.gdeploy.deploy.deploy.platform;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.PlatformSettings;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.ScriptWriter;

import java.io.File;

public abstract class PlatformConfigurationWriter extends ScriptWriter {

    static final File TEMP_FILE = new File("./temp");

    PlatformSettings settings;

    public PlatformConfigurationWriter(ShellScriptBuilder builder, PlatformSettings settings) {
        super(builder);

        this.settings = settings;
    }

}
