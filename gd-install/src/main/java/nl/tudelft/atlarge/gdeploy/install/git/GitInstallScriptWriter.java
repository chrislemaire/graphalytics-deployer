package nl.tudelft.atlarge.gdeploy.install.git;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.install.InstallScriptWriter;
import nl.tudelft.atlarge.gdeploy.install.ProductData;

public class GitInstallScriptWriter extends InstallScriptWriter {

    /**
     * Constructs a new InstallScriptWriter from
     * the script builder to which this script writer
     * will write and the data of the product to install.
     *
     * @param builder to write the scripts to.
     * @param data    data describing the object to install.
     */
    public GitInstallScriptWriter(ShellScriptBuilder builder, ProductData data) {
        super(builder, data);

        readLinesUnsafe("/scripts/install/git-install.sh");
    }

}
