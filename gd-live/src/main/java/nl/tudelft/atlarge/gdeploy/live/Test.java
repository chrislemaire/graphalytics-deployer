package nl.tudelft.atlarge.gdeploy.live;

import nl.tudelft.atlarge.gdeploy.live.install.LinuxInstaller;
import nl.tudelft.atlarge.gdeploy.live.runner.NativeLinuxRunner;

import java.io.IOException;

/**
 * @author Chris Lemaire
 */
public class Test {

    public static void main(String[] args) throws IOException {
        NativeLinuxRunner runner = new NativeLinuxRunner();
        LinuxInstaller installer = new LinuxInstaller(runner, "hadoop");

        try {
            installer.install("2.6.2", "/home/Chris/frameworks/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
