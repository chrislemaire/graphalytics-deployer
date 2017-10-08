package nl.tudelft.atlarge.writer.deploy.sweep;

import nl.tudelft.atlarge.writer.benchmark.data.ExperimentSetup;
import nl.tudelft.atlarge.writer.deploy.ScriptWriter;
import nl.tudelft.atlarge.writer.script.ShellScriptBuilder;

public abstract class SweepWriter extends ScriptWriter {

    private ExperimentSetup setup;

    public SweepWriter(ShellScriptBuilder builder, ExperimentSetup setup) {
        super(builder);

        this.setup = setup;
    }

}
