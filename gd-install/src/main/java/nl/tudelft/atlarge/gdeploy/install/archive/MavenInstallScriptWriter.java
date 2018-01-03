package nl.tudelft.atlarge.gdeploy.install.archive;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.install.data.Install;
import nl.tudelft.atlarge.gdeploy.install.data.Product;

import java.util.Map;

public class MavenInstallScriptWriter extends ApacheInstallScriptWriter {

    /**
     * Constructs a new ApacheInstallScriptWriter from
     * the script builder to which this script writer
     * will write and the data of the object to data.
     *
     * @param builder to write the scripts to.
     * @param data    the data of the full install script.
     * @param product data describing the object to data.
     */
    public MavenInstallScriptWriter(ShellScriptBuilder builder, Install data, Product product) {
        super(builder, data, product);
    }

    @Override
    protected void specificReplacements(Map<String, String> map) {
        super.specificReplacements(map);

        map.put("%maven_path%", data.installDirectory + '/'
                + product.product + '/' + product.version);
    }

    @Override
    public ShellScriptBuilder write() {
        super.write();
        return writeUnsafe("/scripts/install/maven-install.sh");
    }

}
