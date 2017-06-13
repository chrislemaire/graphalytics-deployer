package nl.tudelft.atlarge.install;

import nl.tudelft.atlarge.Global;
import nl.tudelft.atlarge.config.PropertiesConfig;
import nl.tudelft.atlarge.runner.CommandRunner;

import java.io.IOException;

/**
 * Unpacks a software package on the target
 * Linux system.
 *
 * Created by Chris Lemaire on 11-6-2017.
 */
public class LinuxInstaller extends Installer {

    /**
     * Different versions of the product in a {@link PropertiesConfig}.
     */
    private PropertiesConfig versions;

    /**
     * Creates a {@link LinuxInstaller} with super constructor.
     *
     * @param runner to run commands with.
     * @param product to install.
     */
    public LinuxInstaller(CommandRunner runner, String product) throws IOException {
        super(runner, product);

        versions = new PropertiesConfig(Global.VERSIONS_DIR_CLASS + product + ".txt");
        versions.read();
    }

    @Override
    public boolean install(String version, String softwareDir) throws IOException {
        // Generate general paths.
        String downloadLink = versions.get(version);
        String versionDir = softwareDir + product + "/" + version + "/";
        String tempFile = versionDir + "temp-download.temp";

        // Create mkDir command to make directories up to version directory.
        String mkDirCmd = "mkdir -p \"" + versionDir + "\"";

        // Create wget, echo and full commands to download the packaged software.
        String wgetCmd = "wget " + downloadLink + " -P " + versionDir;
        String echoCmd = "echo \"'" + product + "-" + version + "' has already been installed.\"";
        String downloadCmd = "[ \"$(ls -A " + versionDir + ")\" ] && " + echoCmd + " || " + wgetCmd;

        // Classify the package and generate the unpacking command.
        PackType packType = PackType.classify(downloadLink);
        if (packType == null) {
            throw new IOException("File to unpack was not of a defined type: '" + downloadLink + "'");
        }
        String unpackCmd = packType.getUnpackCommand(tempFile, versionDir);

        // Clean up by removing the temporary file.
        String cleanUpCmd = "rm -f " + tempFile;

        // Run commands in order in a blocking fashion.
        return runner.runCommandBlocking(mkDirCmd)
            && runner.runCommandBlocking(downloadCmd)
            && runner.runCommandBlocking(unpackCmd)
            && runner.runCommandBlocking(cleanUpCmd);
    }

}
