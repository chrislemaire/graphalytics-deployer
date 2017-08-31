package nl.tudelft.atlarge.writer.script;

import nl.tudelft.atlarge.writer.Global;

/**
 * Created by Chris Lemaire on 30-8-2017.
 */
public enum RemoteSystem {

    // Must exist since Scripts get generated on the Native system first.
    NATIVE_("localhost", "/home/Chris/graphalytics-deployer/"),

    // For testing on Windows.
    NATIVE("localhost", "C:\\Users\\Chris Lemaire\\AppData\\Local\\lxss\\home\\Chris\\graphalytics-deployer\\"),

    BASTION("bastion", "/home/nfs/" + Global.USERNAME + "/graphalytics-deployer/"),
    DAS5VU("das5vu", "/home/" + Global.USERNAME + "/graphalytics-deployer/"),
    DAS5TUD("das5tud", "/home/" + Global.USERNAME + "/graphalytics-deployer/");

    String sshAlias;
    String preferredDeployerDirectory;

    RemoteSystem(String sshAlias, String preferredDeployerDirectory) {
        this.sshAlias = sshAlias;
        this.preferredDeployerDirectory = preferredDeployerDirectory;
    }

    public String frameworks() {
        return preferredDeployerDirectory + Global.PRODUCT_DIRECTORY;
    }

    public String outputFile() {
        return preferredDeployerDirectory + Global.OUTPUT_FILE;
    }

    public String scripts() {
        return preferredDeployerDirectory + Global.SCRIPT_DIRECTORY ;
    }

}
