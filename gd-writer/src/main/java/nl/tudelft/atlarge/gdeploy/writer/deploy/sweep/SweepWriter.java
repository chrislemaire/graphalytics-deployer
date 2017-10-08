package nl.tudelft.atlarge.gdeploy.writer.deploy.sweep;

import nl.tudelft.atlarge.gdeploy.writer.benchmark.data.ExperimentSetup;
import nl.tudelft.atlarge.gdeploy.writer.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.writer.deploy.ScriptWriter;

public abstract class SweepWriter extends ScriptWriter {

    private ExperimentSetup setup;

    public SweepWriter(ShellScriptBuilder builder, ExperimentSetup setup) {
        super(builder);

        this.setup = setup;
    }

}
