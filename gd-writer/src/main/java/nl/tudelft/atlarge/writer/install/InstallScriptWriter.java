package nl.tudelft.atlarge.writer.install;

import nl.tudelft.atlarge.writer.script.ShellScriptBuilder;

/**
 * Created by Chris Lemaire on 5-9-2017.
 */
public abstract class InstallScriptWriter {

    protected ShellScriptBuilder builder;

    InstallScriptWriter(ShellScriptBuilder builder) {
        this.builder = builder;
    }

    public abstract void write(String version);

}
