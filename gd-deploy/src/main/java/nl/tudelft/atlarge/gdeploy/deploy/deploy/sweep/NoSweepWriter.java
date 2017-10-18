package nl.tudelft.atlarge.gdeploy.deploy.deploy.sweep;

import nl.tudelft.atlarge.gdeploy.core.script.PythonScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.ExperimentSetup;

public class NoSweepWriter extends SweepWriter {

    public NoSweepWriter(PythonScriptBuilder builder, ExperimentSetup setup) {
        super(builder, setup);
    }

    @Override
    public PythonScriptBuilder write() {
        return null;
    }

}
