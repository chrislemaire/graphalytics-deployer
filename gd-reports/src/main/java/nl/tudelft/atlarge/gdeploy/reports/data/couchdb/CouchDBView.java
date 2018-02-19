package nl.tudelft.atlarge.gdeploy.reports.data.couchdb;

import com.fasterxml.jackson.annotation.JsonInclude;
import nl.tudelft.atlarge.gdeploy.reports.data.View;

/**
 * Data class representing a single CouchDB
 * view.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CouchDBView implements View {

    /**
     * The map function as a String. By
     * default this map function just emits
     * the document id as key.
     */
    public String map = "function(doc) { emit(doc._id, null) }";

    /**
     * The reduce function as a String.
     * By default this is null. If it is
     * never set, there will be no reduce
     * function used in the resulting view.
     */
    public String reduce = null;

}
