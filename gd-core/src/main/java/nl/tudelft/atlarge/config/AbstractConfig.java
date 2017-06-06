package nl.tudelft.atlarge.config;

import java.io.File;

/**
 * Abstract config class with super constructor to
 * let {@link Configurable}s be initialized by their related
 * File.
 *
 * Created by Chris Lemaire on 6-6-2017.
 * @author Chris Lemaire
 */
public abstract class AbstractConfig implements Configurable {

    /**
     * File the configurations relate to.
     */
    protected File file;

    /**
     * Super constructor for config classes to initialize
     * {@link Configurable} using the file it relates to.
     *
     * @param file the configurations relate to.
     */
    public AbstractConfig(File file) {
        this.file = file;
    }

}
