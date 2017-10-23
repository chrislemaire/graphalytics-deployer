package nl.tudelft.atlarge.gdeploy.deploy.deploy.sweep;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.ExperimentSetup;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.ScriptCopyWriter;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class SweepWriter extends ScriptCopyWriter {

    private ExperimentSetup setup;

    SweepWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);

        this.setup = benchmark.getExperimentSetup();
    }

    public abstract ShellScriptBuilder writeStart();

    public ShellScriptBuilder writeEnd() {
        try {
            this.readLines("/scripts/sweeps/end.sh");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return write();
    }

}
