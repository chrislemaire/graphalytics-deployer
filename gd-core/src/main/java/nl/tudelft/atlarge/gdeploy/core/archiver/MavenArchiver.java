package nl.tudelft.atlarge.gdeploy.core.archiver;

/**
 * Extends {@link ApacheArchiver} with implementations of the
 * {@link #parseVersion(String)} and {@link #isTargetFile(String)}
 * methods.
 *
 * @author Chris Lemaire
 */
public class MavenArchiver extends ApacheArchiver {
    /**
     * Creates a new {@link Archiver} from the file to write to
     * and the external address to crawl.
     *
     * @param file          to write properties to.
     * @param addresses     at which to find software versions.
     */
    MavenArchiver(String file, String[] addresses) {
        super(file, addresses, "");
    }

    @Override
    public String parseVersion(String attrib) {
        return attrib.replaceFirst(".*maven-(.*)\\.tar\\.gz", "$1");
    }

    @Override
    public boolean isTargetFile(String attrib) {
        return (attrib.startsWith("maven-") || attrib.startsWith("apache-maven-")) && attrib.endsWith(".tar.gz");
    }
}
