package nl.tudelft.atlarge.gdeploy.deploy.sweep;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.BenchmarkRun;

import java.io.IOException;
import java.net.URISyntaxException;

public class SingleRunWriter extends SweepWriter {

    public SingleRunWriter(ShellScriptBuilder builder, Benchmark benchmark,
            BenchmarkRun run) {
        super(builder, benchmark, run);
    }

    @Override
    public ShellScriptBuilder writeStartSpecifics() {
        try {
            this.readLines("/scripts/sweeps/single-run-setup.sh");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return write();
    }

    @Override
    public ShellScriptBuilder writeEnd() {
        try {
            this.readLines("/scripts/sweeps/end-no-loop.sh");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return write();
    }
}
