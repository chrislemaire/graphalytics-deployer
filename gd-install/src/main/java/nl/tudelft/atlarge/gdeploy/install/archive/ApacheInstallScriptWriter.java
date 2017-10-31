package nl.tudelft.atlarge.gdeploy.install.archive;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.install.InstallScriptWriter;
import nl.tudelft.atlarge.gdeploy.install.ProductData;

public class ApacheInstallScriptWriter extends InstallScriptWriter {

    /**
     * Constructs a new ApacheInstallScriptWriter from
     * the script builder to which this script writer
     * will write and the data of the object to install.
     *
     * @param builder to write the scripts to.
     * @param data    data describing the object to install.
     */
    public ApacheInstallScriptWriter(ShellScriptBuilder builder, ProductData data) {
        super(builder, data);

        readLinesUnsafe("/scripts/install/apache-install.sh");
    }

}
