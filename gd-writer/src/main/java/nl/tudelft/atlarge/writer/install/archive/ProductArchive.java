package nl.tudelft.atlarge.writer.install.archive;

import nl.tudelft.atlarge.Global;
import nl.tudelft.atlarge.config.PropertiesConfig;

import java.io.IOException;

/**
 * Created by Chris Lemaire on 30-8-2017.
 */
public class ProductArchive {

    private PropertiesConfig archive;

    public ProductArchive(String product) {
        archive = new PropertiesConfig(Global.VERSIONS_DIR_CLASS + product + ".txt");

        try {
            archive.read();
        } catch (IOException e) {
            System.err.println("Archive file '" + product + ".txt' doesn't exist.");
            e.printStackTrace();
        }
    }

    public boolean hasVersion(String version) {
        return archive.hasKey(version);
    }

    public String getLink(String version) {
        return archive.get(version);
    }

}
