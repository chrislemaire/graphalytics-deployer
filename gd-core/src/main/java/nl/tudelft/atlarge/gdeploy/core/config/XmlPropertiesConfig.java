package nl.tudelft.atlarge.gdeploy.core.config;

import java.io.*;

public class XmlPropertiesConfig extends PropertiesConfig {

    /**
     * Create an XML properties config from
     * its associated properties file.
     * 
     * @param file with associated properties.
     */
    public XmlPropertiesConfig(String file) {
        super(file);
    }

    @Override
    protected void readImpl() throws IOException {
        try (InputStream inputStream = file.openStream()) {
            properties.loadFromXML(inputStream);
        }
    }

    @Override
    protected void writeImpl(File file) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            properties.storeToXML(outputStream, "Written back by PropertiesConfig.java.");
        }
    }

}
