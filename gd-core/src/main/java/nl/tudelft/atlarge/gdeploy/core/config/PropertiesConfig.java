package nl.tudelft.atlarge.gdeploy.core.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Configurations managed by Java {@link Properties}
 * may be managed through this {@link Configurable}.
 *
 * @author Chris Lemaire
 */
public class PropertiesConfig extends AbstractConfig {

    /**
     * Properties of the configurations.
     */
    Properties properties;

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
    protected void readImpl(InputStream inputStream) throws IOException {
        properties.load(inputStream);
    }

    @Override
    public void configure(String key, String value) {
    	assert key != null;
    	assert value != null;
    	
        properties.setProperty(key, value);
    }

    @Override
    public String get(String key) {
        assert key != null;

        return properties.getProperty(key);
    }

    @Override
    protected void writeImpl(File file) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            properties.store(outputStream, "Written back by PropertiesConfig.java.");
        }
    }

    @Override
    public boolean hasKey(String key) {
        return properties.containsKey(key);
    }
}
