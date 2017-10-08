package nl.tudelft.atlarge.gdeploy.writer.deploy;

import nl.tudelft.atlarge.gdeploy.writer.script.ShellScriptBuilder;

/**
 * Created by Chris Lemaire on 6-9-2017.
 */
public abstract class ScriptWriter {

    private ShellScriptBuilder builder;

    public ScriptWriter(ShellScriptBuilder builder) {
        this.builder = builder;
    }

    public abstract ShellScriptBuilder write();

}
