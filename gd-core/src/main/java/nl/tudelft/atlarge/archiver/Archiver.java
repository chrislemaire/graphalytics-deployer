package nl.tudelft.atlarge.archiver;

import nl.tudelft.atlarge.config.PropertiesConfig;

import java.io.File;
import java.io.IOException;

/**
 * Abstract Archiver class to define crawling
 * and writing behaviour for child Archivers.
 *
 * Created by Chris Lemaire on 6-6-2017.
 */
public abstract class Archiver {

    /**
     * File server which to crawl through.
     */
    private String address;

    /**
     * Properties config with which to write to file.
     */
    PropertiesConfig config;

    /**
     * Creates a new Archiver from the file to write to
     * and the external address to crawl.
     *
     * @param file to write properties to.
     * @param address at which to find software versions.
     */
    Archiver(File file, String address) {
        this.config = new PropertiesConfig(file);
        this.address = address;
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
     * Crawls using the internally kept {@link #address} URL String.
     */
    void crawl() {
        crawl(address);
    }

}
