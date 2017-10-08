package nl.tudelft.atlarge.gdeploy.core.archiver;

import nl.tudelft.atlarge.gdeploy.core.Global;
import nl.tudelft.atlarge.gdeploy.core.Util;

import java.io.File;
import java.io.IOException;

/**
 * Main method for updating version archives with Archivers.
 *
 * @author Chris Lemaire
 */
public class ArchiveUpdater {

    private static final String HADOOP_ARCHIVE = "https://archive.apache.org/dist/hadoop/core/";
    private static final String HADOOP_APACHE_HIPPO_NL = "http://apache.hippo.nl/hadoop/common/";
    private static final File HADOOP_FILE = Util.fileFromInternal(Global.VERSIONS_DIR_CLASS + "hadoop.txt");

    private static final String ZOOKEEPER_ARCHIVE = "https://archive.apache.org/dist/zookeeper/";
    private static final String ZOOKEEPER_APACHE_HIPPO_NL = "http://apache.hippo.nl/zookeeper/";
    private static final File ZOOKEEPER_FILE = Util.fileFromInternal(Global.VERSIONS_DIR_CLASS + "zookeeper.txt");

    private static final String MAVEN1_ARCHIVE = "https://archive.apache.org/dist/maven/maven-1/";
    private static final String MAVEN2_ARCHIVE = "https://archive.apache.org/dist/maven/maven-2/";
    private static final String MAVEN3_ARCHIVE = "https://archive.apache.org/dist/maven/maven-3/";
    private static final String MAVEN3_APACHE_HIPPO_NL = "http://apache.hippo.nl/maven/maven-3/";
    private static final File MAVEN_FILE = Util.fileFromInternal(Global.VERSIONS_DIR_CLASS + "maven.txt");

    public static void main(String[] args) throws IOException {
        ApacheArchiver hadoopArchiver =
                new ApacheArchiver(
                        HADOOP_FILE,
                        new String[] {HADOOP_ARCHIVE, HADOOP_APACHE_HIPPO_NL},
                        "hadoop");
        hadoopArchiver.crawl();
        hadoopArchiver.write();

        ApacheArchiver zookeeperArchiver =
                new ApacheArchiver(
                        ZOOKEEPER_FILE,
                        new String[] {ZOOKEEPER_ARCHIVE, ZOOKEEPER_APACHE_HIPPO_NL},
                        "zookeeper");
        zookeeperArchiver.crawl();
        zookeeperArchiver.write();

        ApacheArchiver mavenArchiver =
                new MavenArchiver(
                        MAVEN_FILE,
                        new String[] {MAVEN1_ARCHIVE, MAVEN2_ARCHIVE, MAVEN3_ARCHIVE, MAVEN3_APACHE_HIPPO_NL});
        mavenArchiver.crawl();
        mavenArchiver.write();
    }

}
