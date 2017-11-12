package nl.tudelft.atlarge.gdeploy.deploy.sweep;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.BenchmarkRun;

import java.util.Map;

public class ThreadSweepWriter extends SweepWriter {

    private static final String THREADS = "1 2 4 8 16 32 64 128 256";

    /**
     * Constructs a new SweepWriter from its parameters.
     *
     * @param builder   The script builder to act on. It is assumed
     *                  this builder is currently on the front-end
     *                  of the target system.
     * @param benchmark The benchmark that defines this sweep.
     * @param run       The run this sweep is instantiated from.
     */
    ThreadSweepWriter(ShellScriptBuilder builder, Benchmark benchmark, BenchmarkRun run) {
        super(builder, benchmark, run);
    }

    @Override
    public ShellScriptBuilder writeStartSpecifics() {
        return this.writeUnsafe("/scripts/sweeps/thread-setup.sh");
    }

    @Override
    protected void specificReplacements(Map<String, String> map) {
        super.specificReplacements(map);

        map.put("%sweep_threads%", THREADS);
    }

}
