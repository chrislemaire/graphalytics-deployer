package nl.tudelft.atlarge.gdeploy.core;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class Util {

    public static File fileFromInternal(String internal) throws URISyntaxException {
        return Paths.get(Util.class.getResource(internal).toURI()).toFile();
    }

}
