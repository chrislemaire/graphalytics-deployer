package nl.tudelft.atlarge.gdeploy.core.archiver;

import nl.tudelft.atlarge.gdeploy.core.config.PropertiesConfig;

import java.io.IOException;

/**
 * Abstract Archiver class to define crawling
 * and writing behaviour for child Archivers.
 *
 * @author Chris Lemaire
 */
public abstract class Archiver {

    /**
     * File servers which to crawl through.
     */
    private String[] addresses;

    /**
     * Properties config with which to write to file.
     */
    PropertiesConfig config;

    /**
     * Creates a new Archiver from the file to write to
     * and the external addresses to crawl.
     *
     * @param file to write properties to.
     * @param addresses at which to find software versions.
     */
    Archiver(String file, String[] addresses) {
        this.config = new PropertiesConfig(file);
        this.addresses = addresses;
    }

    /**
     * Writes the properties file with different versions
     * and URLs to them.
     *
     * @throws IOException when something went wrong while writing.
     */
    void write() throws IOException {
        config.writeBack();
    }

    /**
     * Crawls through URL to find all versions and URLs to
     * the downloads for these versions.
     *
     * @param url the {@link Archiver} is currently working on.
     */
    public abstract void crawl(String url);

    /**
     * Crawls using the internally kept {@link #addresses} URL String.
     */
    void crawl() {
        for (String address : addresses) {
            crawl(address);
        }
    }

}
