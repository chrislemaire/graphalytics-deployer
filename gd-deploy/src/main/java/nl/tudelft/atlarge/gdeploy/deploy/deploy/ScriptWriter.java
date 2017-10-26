package nl.tudelft.atlarge.gdeploy.deploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;

/**
 * The generic abstract script writer class. This
 * class is a template class for all ScriptWriters.
 * It describes the interface by which we use a ScriptWriter:
 * A ScriptWriter is constructed with a {@link ShellScriptBuilder}
 * and the {@link nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark}
 * as its parameters. Next, the ScriptWriter will only write to
 * the {@link ShellScriptBuilder} as output when the {@link #write()}
 * method is called.
 *
 * Created by Chris Lemaire on 6-9-2017.
 */
public abstract class ScriptWriter {

    protected ShellScriptBuilder builder;

    public ScriptWriter(ShellScriptBuilder builder) {
        this.builder = builder;
    }

    public abstract ShellScriptBuilder write();

}
