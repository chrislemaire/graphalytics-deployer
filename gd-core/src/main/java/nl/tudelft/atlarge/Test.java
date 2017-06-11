package nl.tudelft.atlarge;

import nl.tudelft.atlarge.install.LinuxInstaller;
import nl.tudelft.atlarge.runner.NativeLinuxRunner;

import java.io.IOException;

/**
 * @author Chris Lemaire
 */
public class Test {

    public static void main(String[] args) throws IOException {
        NativeLinuxRunner runner = new NativeLinuxRunner();
        LinuxInstaller installer = new LinuxInstaller(runner, "hadoop");

        try {
            installer.install("2.6.2", "$HOME/frameworks/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
