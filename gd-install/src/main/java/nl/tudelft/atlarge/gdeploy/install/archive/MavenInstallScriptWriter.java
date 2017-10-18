package nl.tudelft.atlarge.gdeploy.install.archive;

import nl.tudelft.atlarge.gdeploy.core.script.PythonScriptBuilder;
import nl.tudelft.atlarge.gdeploy.install.ArchivedProductInstallScriptWriter;

/**
 * Created by Chris Lemaire on 5-9-2017.
 */
public class MavenInstallScriptWriter extends ArchivedProductInstallScriptWriter {

    public MavenInstallScriptWriter(PythonScriptBuilder builder) {
        super(builder, "maven");
    }

    @Override
    public PythonScriptBuilder writeAdditionalOperations(String version) {
        builder.appendLine("echo PATH=$PATH:" + builder.getCurrentRemoteSystem().frameworks() + "/maven/" + version + "/bin >> ~/");

        return builder;
    }

}
