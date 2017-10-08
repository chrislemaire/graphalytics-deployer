package nl.tudelft.atlarge.gdeploy.deploy.deploy.sweep;

import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.ExperimentSetup;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.ScriptWriter;

public abstract class SweepWriter extends ScriptWriter {

    private ExperimentSetup setup;

    public SweepWriter(ShellScriptBuilder builder, ExperimentSetup setup) {
        super(builder);

        this.setup = setup;
    }

}
