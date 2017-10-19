package nl.tudelft.atlarge.gdeploy.deploy.deploy.sweep;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.ExperimentSetup;

public class ProcessSweepWriter extends SweepWriter {

    public ProcessSweepWriter(ShellScriptBuilder builder, ExperimentSetup setup) {
        super(builder, setup);
    }

    @Override
    public ShellScriptBuilder write() {
        return null;
    }

}