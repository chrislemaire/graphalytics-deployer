package nl.tudelft.atlarge.gdeploy.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {

    public static Path pathFromInternal(String internal) throws URISyntaxException {
        return Paths.get(Util.class.getResource(internal).toURI());
    }

    public static File fileFromInternal(String internal) throws URISyntaxException {
        return pathFromInternal(internal).toFile();
    }

    public static List<String> contentsList(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        return lines;
    }

    public static List<String> internalContentsList(String internal) throws IOException {
        InputStream in = Util.class.getResourceAsStream(internal);
        return contentsList(in);
    }

    public static String internalContents(String internal) throws IOException {
        return String.join("\n", internalContentsList(internal));
    }

    public static String fileContents(File file) throws IOException {
        return String.join("\n", contentsList(new FileInputStream(file)));
    }

}
