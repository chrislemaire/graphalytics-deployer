package nl.tudelft.atlarge.writer.deploy;

import nl.tudelft.atlarge.writer.script.ShellScriptBuilder;

import java.nio.file.Path;

/**
 * Created by Chris Lemaire on 6-9-2017.
 */
public abstract class DeployScriptWriter {

    private ShellScriptBuilder builder;

    private Path configurationFile;

    public DeployScriptWriter(ShellScriptBuilder builder) {
        this.builder = builder;
    }

    public abstract ShellScriptBuilder write();

}
