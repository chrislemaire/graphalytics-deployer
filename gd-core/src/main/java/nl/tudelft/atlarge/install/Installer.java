package nl.tudelft.atlarge.install;

import nl.tudelft.atlarge.runner.CommandRunner;

import java.io.IOException;

/**
 * Meant to install software packages on a given system.
 *
 * @author Chris Lemaire
 */
public abstract class Installer {

    /**
     * {@link CommandRunner} for specifying the target
     * system and running commands on the system.
     */
    protected CommandRunner runner;

    /**
     * The product or software package to install on
     * the target system.
     */
    protected String product;

    /**
     * Creates an {@link Installer} object by its fields.
     *
     * @param runner to run commands with.
     * @param product to install on the target system.
     */
    public Installer(CommandRunner runner, String product) {
        assert runner != null;
        assert product != null;

        this.runner = runner;
        this.product = product;
    }

    /**
     * Installs a specific version of the software on the
     * target system in the specified install directory.
     *
     * The install directory is the directory in which all
     * software packages are located and listed.
     *
     * @param version of the product to install.
     * @param installDir to install the version into.
     * @return <code>true</code> when install was successful.
     */
    public abstract boolean install(String version, String installDir) throws IOException;

}
