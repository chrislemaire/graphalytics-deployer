package nl.tudelft.atlarge.gdeploy.install;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.core.writer.ScriptCopyWriter;
import nl.tudelft.atlarge.gdeploy.install.data.Install;
import nl.tudelft.atlarge.gdeploy.install.data.Product;

import java.util.Map;

public class InstallScriptWriter extends ScriptCopyWriter {

    /**
     * The data of the full install script.
     */
    protected Install data;

    /**
     * The data of the product object to be installed.
     */
    protected Product product;

    /**
     * Constructs a new InstallScriptWriter from
     * the script builder to which this script writer
     * will write and the data of the product to data.
     *
     * @param builder to write the scripts to.
     * @param data    the data of the full install script.
     * @param product data describing the object to data.
     */
    public InstallScriptWriter(ShellScriptBuilder builder, Install data, Product product) {
        super(builder);

        this.data = data;
        this.product = product;
    }

    @Override
    protected void specificReplacements(Map<String, String> map) {
        map.putAll(data.getVariableMap());
    }
}
