package nl.tudelft.atlarge.writer.install;

import nl.tudelft.atlarge.Global;
import nl.tudelft.atlarge.config.PropertiesConfig;

/**
 * Created by Chris Lemaire on 30-8-2017.
 */
public class ProductArchive {

    private PropertiesConfig archive;

    public ProductArchive(String product) {
        archive = new PropertiesConfig(Global.VERSIONS_DIR_CLASS + product + ".txt");
    }

    public boolean hasVersion(String version) {
        return archive.hasKey(version);
    }

    public String getDownloadCommand(String version) {
        StringBuilder cmdBuilder = new StringBuilder("wget ");
        cmdBuilder.append(archive.get(version));

        return cmdBuilder.toString();
    }

}
