package nl.tudelft.atlarge.gdeploy.deploy.sweep;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.BenchmarkRun;
import nl.tudelft.atlarge.gdeploy.deploy.BenchmarkCopyWriter;

import java.util.Map;

public abstract class SweepWriter extends BenchmarkCopyWriter {

    /**
     * The benchmark run this sweep is executing.
     */
    protected BenchmarkRun run;

    /**
     * Constructs a new SweepWriter from its parameters.
     *
     * @param builder   The script builder to act on. It is assumed
     *                  this builder is currently on the front-end
     *                  of the target system.
     * @param benchmark The benchmark that defines this sweep.
     * @param run       The run this sweep is instantiated from.
     */
    SweepWriter(ShellScriptBuilder builder, Benchmark benchmark, BenchmarkRun run) {
        super(builder, benchmark);

        this.run = run;
    }

    /**
     * Writes the start of the sweep. This may be just the
     * setup of the sweep-prefix, or it may be the start of
     * a loop included the setup of its parameters.
     *
     * @return the ShellScriptBuilder to continue building.
     */
    protected abstract ShellScriptBuilder writeStartSpecifics();

    @Override
    protected void specificReplacements(Map<String, String> map) {
        super.specificReplacements(map);

        map.put("%data_sets%", run.getDataSets());
        map.put("%algorithms%", run.getAlgorithms());
    }

    public ShellScriptBuilder writeStart() {
        writeUnsafe("/scripts/sweeps/setup-run-params.sh");
        writeStartSpecifics();
        return writeUnsafe("/scripts/sweeps/benchmark-setup.sh");
    }

    /**
     * Writes the end of the sweep. This may be the end of a
     * loop, it may be just to finish the report and it may also
     * be nothing.
     *
     * @return the ShellScriptBuilder to continue building.
     */
    public ShellScriptBuilder writeEnd() {
        return writeUnsafe("/scripts/sweeps/end.sh");
    }

}
