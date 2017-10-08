package nl.tudelft.atlarge.writer.deploy.sweep;

import nl.tudelft.atlarge.writer.benchmark.data.ExperimentSetup;
import nl.tudelft.atlarge.writer.script.ShellScriptBuilder;

public class ProcessSweepWriter extends SweepWriter {

    public ProcessSweepWriter(ShellScriptBuilder builder, ExperimentSetup setup) {
        super(builder, setup);
    }

    @Override
    public ShellScriptBuilder write() {
        return null;
    }

}
