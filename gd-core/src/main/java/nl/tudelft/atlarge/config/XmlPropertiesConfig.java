package nl.tudelft.atlarge.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class XmlPropertiesConfig extends PropertiesConfig {

    /**
     * Create an XML properties config from
     * its associated properties file.
     * 
     * @param file with associated properties.
     */
    public XmlPropertiesConfig(File file) {
        super(file);
    }

    @Override
    public void readImpl() throws IOException {
        try (InputStream inputStream = new FileInputStream(file)) {
            properties.loadFromXML(inputStream);
        }
    }

    @Override
    public void writeImpl(File file) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            properties.storeToXML(outputStream, "Written back by PropertiesConfig.java.");
        }
    }

}
