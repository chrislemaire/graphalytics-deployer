package nl.tudelft.atlarge.writer.install;

import nl.tudelft.atlarge.writer.script.ShellScriptBuilder;

/**
 * Created by Chris Lemaire on 5-9-2017.
 */
public class GitProductInstallScriptWriter extends InstallScriptWriter {

    private String link;

    public GitProductInstallScriptWriter(ShellScriptBuilder builder, String link) {
        super(builder);

        this.link = link;
    }

}
