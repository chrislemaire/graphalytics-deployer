package nl.tudelft.atlarge.gdeploy.core.util;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Util {

    public static Path pathFromInternal(String internal) throws URISyntaxException {
        return Paths.get(Util.class.getResource(internal).toURI());
    }

    public static File fileFromInternal(String internal) throws URISyntaxException {
        return pathFromInternal(internal).toFile();
    }

}
