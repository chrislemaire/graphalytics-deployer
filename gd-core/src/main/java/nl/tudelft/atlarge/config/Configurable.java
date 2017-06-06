package nl.tudelft.atlarge.config;

/**
 * Configurable defines methods that each config
 * class should implement for config manipulation.
 *
 * These include reading from a config file, setting
 * a configuration and writing back the result.
 *
 * Created by Chris Lemaire on 6-6-2017.
 */
public interface Configurable {

    public void read();

    public void configure(String key, String value);

    public void writeBack();

}
