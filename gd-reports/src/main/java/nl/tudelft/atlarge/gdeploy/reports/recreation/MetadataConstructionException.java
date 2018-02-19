package nl.tudelft.atlarge.gdeploy.reports.recreation;

import java.io.IOException;

public class MetadataConstructionException extends IOException {

    public MetadataConstructionException() {
    }

    public MetadataConstructionException(String message) {
        super(message);
    }

    public MetadataConstructionException(String message, Throwable cause) {
        super(message, cause);
    }

    public MetadataConstructionException(Throwable cause) {
        super(cause);
    }

}
