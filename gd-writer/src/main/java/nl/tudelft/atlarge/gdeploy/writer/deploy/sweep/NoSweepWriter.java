package nl.tudelft.atlarge.gdeploy.writer.deploy.sweep;

import nl.tudelft.atlarge.gdeploy.writer.benchmark.data.ExperimentSetup;
import nl.tudelft.atlarge.gdeploy.writer.script.ShellScriptBuilder;

public class NoSweepWriter extends SweepWriter {

    public NoSweepWriter(ShellScriptBuilder builder, ExperimentSetup setup) {
        super(builder, setup);
    }

    @Override
    public ShellScriptBuilder write() {
        return null;
    }

}
