package nl.tudelft.atlarge.gdeploy.deploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.script.PythonScriptBuilder;

/**
 * Created by Chris Lemaire on 6-9-2017.
 */
public abstract class ScriptWriter {

    protected PythonScriptBuilder builder;

    public ScriptWriter(PythonScriptBuilder builder) {
        this.builder = builder;
    }

    public abstract PythonScriptBuilder write();

}
