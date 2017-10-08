package nl.tudelft.atlarge.gdeploy.core.archiver;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Crawls Apache fileservers to find different versions
 * of a certain type of software.
 *
 * @author Chris Lemaire
 */
public class ApacheArchiver extends Archiver {

    /**
     * The prefix target versions must have.
     */
    private String productPrefix;

    /**
     * Creates a new {@link Archiver} from the file to write to
     * and the external address to crawl.
     *
     * @param file    to write properties to.
     * @param addresses at which to find software versions.
     */
    ApacheArchiver(File file, String[] addresses, String productPrefix) {
        super(file, addresses);

        this.productPrefix = productPrefix;
    }

    /**
     * Crawls through after creating the url to crawl to
     * from the given {@link Element} and given url.
     *
     * @param url from which we come.
     * @param anchorNode which we follow.
     */
    private void crawlFromAnchor(String url, Node anchorNode) {
        assert url != null;
        assert anchorNode != null;

        String path = url + anchorNode.attr("href");
        crawl(path);
    }

    /**
     * Adds a configuration for a certain given {@link Element}
     * using the given url.
     *
     * @param url from which we came.
     * @param anchorNode which one must follow to get the file.
     */
    private void addFromAnchor(String url, Node anchorNode) {
        assert url != null;
        assert anchorNode != null;

        String path = url + anchorNode.attr("href");

        if (isTargetFile(anchorNode.attr("href"))) {
            String version = parseVersion(anchorNode.attr("href"));
            config.configure(version, path);
        }
    }

    /**
     * Parses the version from the given attribute name String.
     * Should, in principle, only remove the prefix (f.e. 'hadoop-')
     * and the suffix from the name.
     *
     * @param attrib full name of the attribute to parse version from.
     * @return parsed version String.
     */
    public String parseVersion(String attrib) {
        return attrib.replaceFirst(productPrefix + "-(.*)\\.tar\\.gz", "$1");
    }

    /**
     * Checks whether a file with name attrib conforms to the standard
     * set for software package names of the requested type.
     *
     * @param attrib name of software package.
     * @return <code>true</code> when attrib is a valid name.
     */
    public boolean isTargetFile(String attrib) {
        return attrib.startsWith(productPrefix + "-") && attrib.endsWith(".tar.gz");
    }

    @Override
    public void crawl(String url) {
        System.out.println("Creep and crawling down '" + url + "'");

        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Elements images = doc.select("img[alt=\"[DIR]\"]");
        for (Element curr : images) {
            crawlFromAnchor(url, curr.nextSibling().nextSibling());
        }

        images = doc.select("img[alt=\"[   ]\"]");
        for (Element curr : images) {
            addFromAnchor(url, curr.nextSibling().nextSibling());
        }
    }

}
