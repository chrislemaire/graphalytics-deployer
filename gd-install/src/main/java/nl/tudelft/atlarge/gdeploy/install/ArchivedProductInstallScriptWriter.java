package nl.tudelft.atlarge.gdeploy.install;

import nl.tudelft.atlarge.gdeploy.core.script.PythonScriptBuilder;
import nl.tudelft.atlarge.gdeploy.install.archive.ProductArchive;
import nl.tudelft.atlarge.gdeploy.install.packages.ProductPackage;

/**
 * Created by Chris Lemaire on 29-8-2017.
 */
public class ArchivedProductInstallScriptWriter extends InstallScriptWriter {

    private ProductArchive productArchive;

    public ArchivedProductInstallScriptWriter(PythonScriptBuilder builder, String product) {
        super(builder, product);

        this.productArchive = new ProductArchive(product);
        this.product = product;
    }

    public PythonScriptBuilder writeAdditionalOperations(String version) {
        return builder;
    }

    public PythonScriptBuilder write(String version) {
        if (!productArchive.hasVersion(version)) {
            System.err.println("Unable to write downloader for version `" + version + "`.");
            System.err.println("This version does not exist.");
        }

        builder.appendLine("# START OF ARCHIVED PRODUCT INSTALL SCRIPT #");
        writeInitializer(version)

                .appendLine("if [ -z \"$(ls -A ./)\" ]; then");

        ProductPackage.TAR_GZ.downloadAndUnpack(builder, version, productArchive.getLink(version));

        writeAdditionalOperations(version);

        builder.appendLine("else")
                .appendLineWithOutput("echo Product already installed: " + product + " v" + version)
                .appendLine("fi")
                .appendLine("# END OF ARCHIVE DOWNLOADING SCRIPT #\n\n");

        return builder;
    }

}
