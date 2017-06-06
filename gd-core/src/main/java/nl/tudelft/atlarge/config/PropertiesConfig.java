package nl.tudelft.atlarge.config;

import java.io.*;
import java.util.Properties;

/**
 * Configurations managed by Java {@link Properties}
 * may be managed through this {@link Configurable}.
 *
 * Created by Chris Lemaire on 6-6-2017.
 * @author Chris Lemaire
 */
public class PropertiesConfig extends AbstractConfig {

    /**
     * Properties of the configurations.
     */
    private Properties properties;

    /**
     * Calls super constructor with file that relates to the
     * configurations.
     *
     * @param file associated with the configurations.
     */
    public PropertiesConfig(File file) {
        super(file);

        this.properties = new Properties();
    }

    @Override
    public void read() throws IOException {
        try (InputStream inputStream = new FileInputStream(file)) {
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            System.err.println("Given config file '" + file.getPath() + "' does not exist.");
            throw e;
        } catch (IOException e) {
            System.err.println("Something went wrong while reading properties file '" + file.getPath() + "'");
            throw e;
        }
    }

    @Override
    public void configure(String key, String value) {
        properties.setProperty(key, value);
    }

    @Override
    public void writeBack() throws IOException {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            properties.store(outputStream, "Written back by PropertiesConfig.java.");
        } catch (FileNotFoundException e) {
            System.err.println("Given config file '" + file.getPath() + "' does not exist.");
            throw e;
        } catch (IOException e) {
            System.err.println("Something went wrong while writing properties file '" + file.getPath() + "'");
            throw e;
        }
    }

}
