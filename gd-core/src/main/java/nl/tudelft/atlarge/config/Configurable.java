package nl.tudelft.atlarge.config;

import java.io.IOException;

/**
 * Configurable defines methods that each config
 * class should implement for config manipulation.
 *
 * These include reading from a config file, setting
 * a configuration and writing back the result.
 *
 * Created by Chris Lemaire on 6-6-2017.
 * @author Chris Lemaire
 */
public interface Configurable {

    /**
     * Reads a configuration file into the Configurable.
     *
     * @throws IOException when the something went wrong during
     *          the reading of the configuration file.
     */
    public void read() throws IOException;

    /**
     * Configures a configuration to be of a certain value.
     *
     * @param key of the configuration.
     * @param value to set for the configuration.
     */
    public void configure(String key, String value);

    public void writeBack() throws IOException;

}
