package nl.tudelft.atlarge.gdeploy.install.archive;

import nl.tudelft.atlarge.gdeploy.core.Global;
import nl.tudelft.atlarge.gdeploy.core.Util;
import nl.tudelft.atlarge.gdeploy.core.config.PropertiesConfig;

import java.io.IOException;

/**
 * Created by Chris Lemaire on 30-8-2017.
 */
public class ProductArchive {

    private PropertiesConfig archive;

    public ProductArchive(String product) {
        archive = new PropertiesConfig(Util.fileFromInternal(
                Global.VERSIONS_DIR_CLASS + product + ".txt"));

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
