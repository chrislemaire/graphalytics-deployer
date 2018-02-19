package nl.tudelft.atlarge.gdeploy.reports.data.couchdb;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Data class representing a single CouchDB design.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CouchDBDesign {

    /**
     * The ID of the design.
     */
    public String _id = null;

    /**
     * The revision id.
     */
    public String _rev = null;

    /**
     * The language in which the map and reduce
     * functions are written. Javascript by default.
     */
    public String language = "javascript";

    /**
     * The map of CouchDB views in this design mapped
     * by their names.
     */
    public Map<String, CouchDBView> views = new HashMap<>();

    /**
     * The map of CouchDB shows in this design mapped
     * by their names. A CouchDB show is represented
     * by the javascript function.
     */
    public Map<String, String> shows = new HashMap<>();

}
