package nl.tudelft.atlarge.config;

import java.io.File;
import java.io.IOException;

/**
 * Configurable defines methods that each config
 * class should implement for config manipulation.
 *
 * These include reading from a config file, setting
 * a configuration and writing back the result.
 *
 * @author Chris Lemaire
 */
public interface Configurable {

    /**
     * Reads a configuration file into the Configurable.
     *
     * @throws IOException when something went wrong during
     *          the reading of the configuration file.
     */
    void read() throws IOException;

    /**
     * Configures a configuration to be of a certain value.
     *
     * @param key of the configuration.
     * @param value to set for the configuration.
     */
    void configure(String key, String value);

    /**
     * Returns the requested value for the given key.
     *
     * @param key String to match as key.
     * @return value associated with given key.
     */
    String get(String key);

    /**
     * Writes back the configurations to the associated
     * config file.
     * 
     * @throws IOException when something went wrong during
     *          the writing to the configuration file.
     */
    void writeBack() throws IOException;
    
    /**
     * Writes the configurations to the given file.
     * 
     * @param file to write configs to.
     * @throws IOException when something went wrong during
     *          the writing to the configuration file.
     */
    void write(File file) throws IOException;

    /**
     * Returns whether the configurations contain the
     * given key.
     *
     * @param key to check with configurations.
     * @return true when the configurations contains
     *          the key.
     */
    boolean hasKey(String key);

}
