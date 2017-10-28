package nl.tudelft.atlarge.gdeploy.deploy.writers.sweep;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.BenchmarkRun;
import nl.tudelft.atlarge.gdeploy.deploy.writers.ScriptCopyWriter;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class SweepWriter extends ScriptCopyWriter {

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

    public ShellScriptBuilder writeStart() {
        writeUnsafe("/scripts/sweeps/setup-run-params.sh");
        return writeStartSpecifics();
    }

    /**
     * Writes the end of the sweep. This may be the end of a
     * loop, it may be just to finish the report and it may also
     * be nothing.
     *
     * @return the ShellScriptBuilder to continue building.
     */
    public ShellScriptBuilder writeEnd() {
        try {
            this.readLines("/scripts/sweeps/end.sh");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return write();
    }

}
