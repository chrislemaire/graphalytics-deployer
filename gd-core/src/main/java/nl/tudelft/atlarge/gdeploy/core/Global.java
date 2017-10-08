package nl.tudelft.atlarge.gdeploy.core;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * List of environment variables.
 *
 * @author Chris Lemaire
 */
public class Global {

    public static final String VERSIONS_DIR_CLASS = "/versions/";

    public static final String USERNAME = "clemaire";

    public static final String SCRIPT_DIRECTORY = "gdeploy-scripts/";
    public static final String OUTPUT_RESOURCES = SCRIPT_DIRECTORY + "resources/";
    public static final String PRODUCT_DIRECTORY = "frameworks/";
    public static final String OUTPUT_FILE = "tmpoutput.out";

    public static final Random RANDOM = new Random(System.currentTimeMillis());

    public static final SimpleDateFormat LOG_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    public static String CURRENT_PROJECT = "NA";

}
