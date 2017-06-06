package nl.tudelft.atlarge.archiver;

import java.io.File;
import java.io.IOException;

/**
 * Main method for updating version archives with Archivers.
 *
 * Created by Chris Lemaire on 6-6-2017.
 */
public class ArchiveUpdater {

    private static final String RESOURCES_DIR = "C:\\Users\\Chris Lemaire\\Documents\\IdeaProjects\\graphalytics-deployer\\gd-core\\src\\main\\resources";

    private static final String HADOOP_ARCHIVE = "https://archive.apache.org/dist/hadoop/core/";
    private static final String HADOOP_FILE = RESOURCES_DIR + "\\versions\\hadoop.txt";

    public static void main(String[] args) throws IOException {
        HadoopArchiver hadoopArchiver = new HadoopArchiver(new File(HADOOP_FILE), HADOOP_ARCHIVE);
        hadoopArchiver.crawl();
        hadoopArchiver.write();
    }

}
