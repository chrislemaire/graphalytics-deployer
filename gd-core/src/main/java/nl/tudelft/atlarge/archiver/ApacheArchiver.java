package nl.tudelft.atlarge.archiver;

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
 * Created by Chris Lemaire on 6-6-2017.
 */
public abstract class ApacheArchiver extends Archiver {

    /**
     * Creates a new {@link Archiver} from the file to write to
     * and the external address to crawl.
     *
     * @param file    to write properties to.
     * @param address at which to find software versions.
     */
    ApacheArchiver(File file, String address) {
        super(file, address);
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

        if (path.endsWith(".tar.gz")) {
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
    public abstract String parseVersion(String attrib);

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
