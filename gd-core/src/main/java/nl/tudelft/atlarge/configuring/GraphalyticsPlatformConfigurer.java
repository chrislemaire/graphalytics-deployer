package nl.tudelft.atlarge.configuring;

import nl.tudelft.atlarge.Global;
import nl.tudelft.atlarge.config.PropertiesConfig;
import nl.tudelft.atlarge.runner.HeadNodeRunner;

import java.io.IOException;

/**
 * Created by Chris Lemaire on 14-7-2017.
 */
public abstract class GraphalyticsPlatformConfigurer {

    private PropertiesConfig platform;

    private PropertiesConfig benchmark;

    private PropertiesConfig pricing;
    private PropertiesConfig environment;


    private HeadNodeRunner entryPointRunner;

    private String pathToPlatformConfig;


    public GraphalyticsPlatformConfigurer(HeadNodeRunner entryPointRunner, String pathToPlatformConfig) {
        this.entryPointRunner = entryPointRunner;
        this.pathToPlatformConfig = pathToPlatformConfig;
    }


    public void setPlatformPath(String path) {
        pathToPlatformConfig = path + "/config";
    }

    /**
     * Loads the three properties files after
     * copying the entire config directory from
     * the remote host.
     * @throws IOException when something went
     *      wrong with either secure copying or
     *      reading the properties locally.
     */
    public void loadConfigs() throws IOException {
        Global.NATIVE_RUNNER.runCommandBlocking(
                "scp -rv " + entryPointRunner.getSshId() + ":" + pathToPlatformConfig + " ./");

        platform = new PropertiesConfig("./platform.properties");
        platform.read();

        benchmark = new PropertiesConfig("./platform.benchmark");
        benchmark.read();

        pricing = new PropertiesConfig("./platform.pricing");
        pricing.read();

        environment = new PropertiesConfig("./platform.environment");
        environment.read();
    }

    /**
     * Configures the loaded {@link PropertiesConfig)s
     * for the specific platform.
     */
    public abstract void configure();

    /**
     * Writes back the configurations for graphalytics
     * to the remote host.
     * @throws IOException when something goes wrong
     *      during the writing of the properties files
     *      locally.
     */
    public void writeBackConfigs() throws IOException {
        platform.writeBack();
        benchmark.writeBack();
        pricing.writeBack();
        environment.writeBack();

        // Copy back the configuration files.

        // Remove local configuration files.
    }

    public void configureEnvironmentProperties() {

    }

}
