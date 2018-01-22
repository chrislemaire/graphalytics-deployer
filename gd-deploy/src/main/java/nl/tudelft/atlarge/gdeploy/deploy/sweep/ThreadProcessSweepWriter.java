package nl.tudelft.atlarge.gdeploy.deploy.sweep;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.BenchmarkRun;

import java.util.Map;

public class ThreadProcessSweepWriter extends SweepWriter {

    private static final String THREADS = "1 2 4 8 16 32 64 128 256";
    private static final String PROCESSES = "256 128 64 32 16 8 4 2 1";

    public ThreadProcessSweepWriter(ShellScriptBuilder builder, Benchmark benchmark,
            BenchmarkRun run) {
        super(builder, benchmark, run);
    }

    @Override
    public ShellScriptBuilder writeStartSpecifics() {
        return this.writeUnsafe("/scripts/sweeps/thread-process-setup.sh");
    }

    @Override
    protected void specificReplacements(Map<String, String> map) {
        super.specificReplacements(map);

        //map.put("%sweep_threads%", THREADS);
        //map.put("%sweep_procs%", PROCESSES);
    }

}
