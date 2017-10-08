package nl.tudelft.atlarge.gdeploy.writer;

import nl.tudelft.atlarge.gdeploy.writer.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.writer.install.ArchivedProductInstallScriptWriter;
import nl.tudelft.atlarge.gdeploy.writer.script.RemoteSystem;

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
