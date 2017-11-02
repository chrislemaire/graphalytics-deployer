package nl.tudelft.atlarge.gdeploy.install.git;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.install.data.Install;
import nl.tudelft.atlarge.gdeploy.install.data.Product;

import java.util.Map;

public class PowergraphInstallScriptWriter extends GitInstallScriptWriter {

    /**
     * Constructs a new InstallScriptWriter from
     * the script builder to which this script writer
     * will write and the data of the product to data.
     *
     * @param builder to write the scripts to.
     * @param data    the data of the full install script.
     * @param product data describing the object to data.
     */
    public PowergraphInstallScriptWriter(ShellScriptBuilder builder, Install data, Product product) {
        super(builder, data, product);
    }

    @Override
    protected void specificReplacements(Map<String, String> map) {
        super.specificReplacements(map);

        map.put("%powergraph_dir%", data.getInstallDirectory() + "/powergraph");
    }

}
