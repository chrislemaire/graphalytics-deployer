package nl.tudelft.atlarge.gdeploy.deploy.deploy.platform;

import nl.tudelft.atlarge.gdeploy.core.config.PropertiesConfig;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.PlatformSettings;
import nl.tudelft.atlarge.gdeploy.core.script.RemoteSystem;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PowergraphConfigurationWriter extends PlatformConfigurationWriter {

    private static final String PLATFORM_TEMPLATE = PowergraphConfigurationWriter.class
            .getResource("/configs/template-platform.properties").getFile()
            .replace("%20", " ")
            .replace('\\', '/');

    public PowergraphConfigurationWriter(ShellScriptBuilder builder, PlatformSettings settings) {
        super(builder, settings);
    }

    @Override
    public ShellScriptBuilder write() {
        try {
            Files.write(TEMP_FILE.toPath(), Files.readAllBytes(Paths.get(PLATFORM_TEMPLATE)));

            PropertiesConfig config = new PropertiesConfig(TEMP_FILE);
            config.read();

            RemoteSystem.NATIVE.resources();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder;
    }

}
