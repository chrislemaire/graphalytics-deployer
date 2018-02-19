package nl.tudelft.atlarge.gdeploy.reports.util;

import java.io.InputStream;
import java.util.Scanner;

public class FileUtil {

    /**
     * Reads a single resource file at the given resource
     * classpath and returns its text as a single String.
     *
     * @param resource class path of the resource to read.
     * @return String representing the entire text in
     * the given resource file.
     */
    public static String readTextFromResource(String resource) {
        return readText(FileUtil.class.getResourceAsStream(resource));
    }

    /**
     * Reads an entire text from the given input stream
     * as a String.
     *
     * @param inputStream input to read text from.
     * @return String representing the entire text in
     * the given stream.
     */
    public static String readText(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        return (scanner.hasNext()) ? scanner.next() : "";
    }

}
