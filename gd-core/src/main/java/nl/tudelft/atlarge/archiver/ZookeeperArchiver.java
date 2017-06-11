package nl.tudelft.atlarge.archiver;

import java.io.File;

/**
 * Extends the {@link ApacheArchiver} with an implementation
 * for the zookeeper archive servers.
 *
 * Created by Chris Lemaire on 7-6-2017.
 */
public class ZookeeperArchiver extends ApacheArchiver {

    /**
     * Creates a new {@link Archiver} from the file to write to
     * and the external address to crawl.
     *
     * @param file    to write properties to.
     * @param address at which to find software versions.
     */
    ZookeeperArchiver(File file, String address) {
        super(file, address);
    }

    @Override
    public String parseVersion(String attrib) {
        return attrib.replaceFirst("zookeeper-(.*)\\.tar\\.gz", "$1");
    }

    @Override
    public boolean isTargetFile(String attrib) {
        return attrib.startsWith("zookeeper-") && attrib.endsWith(".tar.gz");
    }

}
