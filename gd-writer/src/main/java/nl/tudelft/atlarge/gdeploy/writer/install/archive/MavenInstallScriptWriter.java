package nl.tudelft.atlarge.gdeploy.writer.install.archive;

import nl.tudelft.atlarge.gdeploy.writer.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.writer.install.ArchivedProductInstallScriptWriter;

/**
 * Created by Chris Lemaire on 5-9-2017.
 */
public class MavenInstallScriptWriter extends ArchivedProductInstallScriptWriter {

    public MavenInstallScriptWriter(ShellScriptBuilder builder) {
        super(builder, "maven");
    }

    @Override
    public ShellScriptBuilder writeAdditionalOperations(String version) {
        builder.appendLine("echo PATH=$PATH:" + builder.getCurrentRemoteSystem().frameworks() + "/maven/" + version + "/bin >> ~/");

        return builder;
    }

}
