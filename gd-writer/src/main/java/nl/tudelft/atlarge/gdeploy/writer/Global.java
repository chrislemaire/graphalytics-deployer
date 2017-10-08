package nl.tudelft.atlarge.gdeploy.writer;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Created by Chris Lemaire on 24-8-2017.
 */
public class Global {

    public static final String USERNAME = "clemaire";

    public static final String SCRIPT_DIRECTORY = "scripts/";
    public static final String PRODUCT_DIRECTORY = "frameworks/";
    public static final String OUTPUT_FILE = "tmpoutput.out";

    public static final Random RANDOM = new Random(System.currentTimeMillis());

    public static final SimpleDateFormat LOG_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");

}
