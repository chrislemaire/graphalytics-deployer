package nl.tudelft.atlarge.gdeploy.install.git;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.install.InstallScriptWriter;
import nl.tudelft.atlarge.gdeploy.install.data.Install;
import nl.tudelft.atlarge.gdeploy.install.data.Product;

public class GitInstallScriptWriter extends InstallScriptWriter {

    /**
     * Constructs a new InstallScriptWriter from
     * the script builder to which this script writer
     * will write and the data of the product to data.
     *
     * @param builder to write the scripts to.
     * @param data    the data of the full install script.
     * @param product data describing the object to data.
     */
    public GitInstallScriptWriter(ShellScriptBuilder builder, Install data, Product product) {
        super(builder, data, product);

        readLinesUnsafe("/scripts/install/git-install.sh");
    }

}
