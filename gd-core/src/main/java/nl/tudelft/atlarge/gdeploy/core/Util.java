package nl.tudelft.atlarge.gdeploy.core;

import java.io.File;

public class Util {

    public static File fileFromInternal(String internal) {
        return new File(Util.class.getResource(internal).getFile()
                .replace("%20", " ")
                .replace('\\', '/'));
    }

}
