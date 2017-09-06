package nl.tudelft.atlarge.writer.install;

import nl.tudelft.atlarge.writer.install.archive.ProductArchive;
import nl.tudelft.atlarge.writer.packages.ProductPackage;
import nl.tudelft.atlarge.writer.script.ShellScriptBuilder;

/**
 * Created by Chris Lemaire on 29-8-2017.
 */
public class ArchivedProductInstallScriptWriter extends InstallScriptWriter {

    private ProductArchive productArchive;

    public ArchivedProductInstallScriptWriter(ShellScriptBuilder builder, String product) {
        super(builder, product);

        this.productArchive = new ProductArchive(product);
        this.product = product;
    }

    public ShellScriptBuilder writeAdditionalOperations(String version) {
        return builder;
    }

    public ShellScriptBuilder write(String version) {
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
