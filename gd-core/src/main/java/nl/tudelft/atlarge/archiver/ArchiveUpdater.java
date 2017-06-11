package nl.tudelft.atlarge.archiver;

import java.io.File;
import java.io.IOException;

/**
 * Main method for updating version archives with Archivers.
 *
 * @author Chris Lemaire
 */
public class ArchiveUpdater {

    private static final String RESOURCES_DIR = "C:\\Users\\Chris Lemaire\\Documents\\IdeaProjects\\graphalytics-deployer\\gd-core\\src\\main\\resources";

    private static final String HADOOP_ARCHIVE = "https://archive.apache.org/dist/hadoop/core/";
    private static final String HADOOP_APACHE_HIPPO_NL = "http://apache.hippo.nl/hadoop/common/";
    private static final String HADOOP_FILE = RESOURCES_DIR + "\\versions\\hadoop.txt";

    private static final String ZOOKEEPER_ARCHIVE = "https://archive.apache.org/dist/zookeeper/";
    private static final String ZOOKEEPER_APACHE_HIPPO_NL = "http://apache.hippo.nl/zookeeper/";
    private static final String ZOOKEEPER_FILE = RESOURCES_DIR + "\\versions\\zookeeper.txt";

    private static final String MAVEN1_ARCHIVE = "https://archive.apache.org/dist/maven/maven-1/";
    private static final String MAVEN2_ARCHIVE = "https://archive.apache.org/dist/maven/maven-2/";
    private static final String MAVEN3_ARCHIVE = "https://archive.apache.org/dist/maven/maven-3/";
    private static final String MAVEN3_APACHE_HIPPO_NL = "http://apache.hippo.nl/maven/maven-3/";
    private static final String MAVEN_FILE = RESOURCES_DIR + "\\versions\\maven.txt";

    public static void main(String[] args) throws IOException {
        ApacheArchiver hadoopArchiver =
                new ApacheArchiver(
                        new File(HADOOP_FILE),
                        new String[] {HADOOP_ARCHIVE, HADOOP_APACHE_HIPPO_NL},
                        "hadoop");
        hadoopArchiver.crawl();
        hadoopArchiver.write();

        ApacheArchiver zookeeperArchiver =
                new ApacheArchiver(
                        new File(ZOOKEEPER_FILE),
                        new String[] {ZOOKEEPER_ARCHIVE, ZOOKEEPER_APACHE_HIPPO_NL},
                        "zookeeper");
        zookeeperArchiver.crawl();
        zookeeperArchiver.write();

        ApacheArchiver mavenArchiver =
                new MavenArchiver(
                        new File(MAVEN_FILE),
                        new String[] {MAVEN1_ARCHIVE, MAVEN2_ARCHIVE, MAVEN3_ARCHIVE, MAVEN3_APACHE_HIPPO_NL});
        mavenArchiver.crawl();
        mavenArchiver.write();
    }

}
