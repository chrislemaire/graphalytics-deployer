package nl.tudelft.atlarge.gdeploy.install;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.core.writer.ScriptCopyWriter;

public class InstallScriptWriter extends ScriptCopyWriter {

    protected ProductData data;

    /**
     * Constructs a new InstallScriptWriter from
     * the script builder to which this script writer
     * will write and the data of the product to install.
     *
     * @param builder to write the scripts to.
     * @param data    data describing the object to install.
     */
    public InstallScriptWriter(ShellScriptBuilder builder, ProductData data) {
        super(builder);

        this.data = data;
    }

}
