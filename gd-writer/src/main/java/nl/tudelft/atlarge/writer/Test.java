package nl.tudelft.atlarge.writer;

import nl.tudelft.atlarge.writer.install.ArchivedProductInstallScriptWriter;
import nl.tudelft.atlarge.writer.script.RemoteSystem;
import nl.tudelft.atlarge.writer.script.ShellScriptBuilder;

import java.io.IOException;

/**
 * Created by Chris Lemaire on 25-8-2017.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        ShellScriptBuilder installScriptWriter = new ShellScriptBuilder("install_maven_relay");

        installScriptWriter.startBuildingSshRemoteScript(RemoteSystem.BASTION);
        installScriptWriter.startBuildingSshRemoteScript(RemoteSystem.DAS5VU);

        ArchivedProductInstallScriptWriter writer = new ArchivedProductInstallScriptWriter(installScriptWriter, "maven");
        writer.write("3.3.1-bin");

        installScriptWriter.finish();
    }

}
