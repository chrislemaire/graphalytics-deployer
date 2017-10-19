package nl.tudelft.atlarge.gdeploy.deploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;

/**
 * Created by Chris Lemaire on 6-9-2017.
 */
public abstract class ScriptWriter {

    protected ShellScriptBuilder builder;

    public ScriptWriter(ShellScriptBuilder builder) {
        this.builder = builder;
    }

    public abstract ShellScriptBuilder write();

}
