package nl.tudelft.atlarge.gdeploy.core.script;

import lombok.Getter;
import nl.tudelft.atlarge.gdeploy.core.Global;

/**
 * Created by Chris Lemaire on 30-8-2017.
 */
public enum RemoteSystem {

    // Must exist since Scripts get generated on the Native system first.
    NATIVE_LINUX("localhost", "/home/clemaire/graphalytics-deployer/"),

    // For running from Windows.
    NATIVE_WINDOWS("localhost", "C:/Users/Chris Lemaire/Documents/Temporary/graphalytics-deployer/"),

    // Remote systems.
    BASTION("bastion", "/home/nfs/" + Global.USERNAME + "/graphalytics-deployer/"),
    DAS5VU("das5vu", "/home/" + Global.USERNAME + "/graphalytics-deployer/"),
    DAS5TUD("das5tud", "/home/" + Global.USERNAME + "/graphalytics-deployer/");

    @Getter
    private String sshAlias;

    private String deployerDirectory;

    RemoteSystem(String sshAlias, String deployerDirectory) {
        this.sshAlias = sshAlias;
        this.deployerDirectory = deployerDirectory;
    }

    public static RemoteSystem getNative() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            return NATIVE_WINDOWS;
        } else {
            return NATIVE_LINUX;
        }
    }

    public String frameworks() {
        return deployer() + Global.PRODUCT_DIRECTORY;
    }

    public String outputFile() {
        return deployer() + Global.OUTPUT_FILE;
    }

    public String scripts() {
        return deployer() + Global.SCRIPT_DIRECTORY;
    }

    public String resources() {
        return deployer() + Global.OUTPUT_RESOURCES + '/';
    }

    public String utilWrite() {
        return deployerDirectory + Global.UTILITY_DIR;
    }

    /**
     * Returns the script writing directory to which the script files
     * will be written during the writing phase. This path may be
     * a Windows path as writing is done on the current system, not
     * the execution system.
     *
     * @return String path to the writing directory from the native
     * system including Windows systems.
     */
    public String scriptWrite() {
        return deployerDirectory + Global.SCRIPT_DIRECTORY;
    }

    /**
     * Returns the deployer directory on the native system.
     * This differs for Windows as on Windows, the outputted
     * files are first copied to the linux subsystem, so the
     * native deployer path for this will be local.
     *
     * @return String path to the script directory on the native
     * (Linux) system.
     */
    public String deployer() {
        if (this == NATIVE_WINDOWS) {
            return NATIVE_LINUX.deployerDirectory;
        }
        return deployerDirectory;
    }

}
