package nl.tudelft.atlarge.writer.install.archive;

import nl.tudelft.atlarge.writer.install.ArchivedProductInstallScriptWriter;
import nl.tudelft.atlarge.writer.script.ShellScriptBuilder;

/**
 * Created by Chris Lemaire on 5-9-2017.
 */
public class MavenInstallScriptWriter extends ArchivedProductInstallScriptWriter {

    public MavenInstallScriptWriter(ShellScriptBuilder builder) {
        super(builder, "maven");
    }

    @Override
    public void writeAdditionalOperations(String version) {
        builder.appendLine("echo PATH=$PATH:" + builder.getCurrentRemoteSystem().frameworks() + "/maven/" + version + "/bin >> ~/");
    }

}
