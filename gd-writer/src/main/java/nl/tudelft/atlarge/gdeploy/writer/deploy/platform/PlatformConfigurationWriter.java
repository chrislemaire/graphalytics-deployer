package nl.tudelft.atlarge.gdeploy.writer.deploy.platform;

import nl.tudelft.atlarge.gdeploy.writer.benchmark.data.PlatformSettings;
import nl.tudelft.atlarge.gdeploy.writer.deploy.ScriptWriter;
import nl.tudelft.atlarge.gdeploy.writer.script.ShellScriptBuilder;

import java.io.File;

public abstract class PlatformConfigurationWriter extends ScriptWriter {

    static final File TEMP_FILE = new File("./temp");

    PlatformSettings settings;

    public PlatformConfigurationWriter(ShellScriptBuilder builder, PlatformSettings settings) {
        super(builder);

        this.settings = settings;
    }

}
