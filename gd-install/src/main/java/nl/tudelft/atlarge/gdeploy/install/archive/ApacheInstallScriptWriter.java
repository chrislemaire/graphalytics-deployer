package nl.tudelft.atlarge.gdeploy.install.archive;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.core.writer.ScriptCopyWriter;

public class ApacheInstallScriptWriter extends ScriptCopyWriter {

    /**
     * Constructs a new ApacheInstallScriptWriter from
     * the script builder to which this script writer
     * will write.
     *
     * @param builder to write the scripts to.
     */
    public ApacheInstallScriptWriter(ShellScriptBuilder builder) {
        super(builder);
    }

}
