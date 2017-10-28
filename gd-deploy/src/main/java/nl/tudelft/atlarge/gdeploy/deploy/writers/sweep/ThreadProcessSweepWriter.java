package nl.tudelft.atlarge.gdeploy.deploy.writers.sweep;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.BenchmarkRun;

import java.util.Map;

public class ThreadProcessSweepWriter extends SweepWriter {

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

        map.put("%data_sets%", run.getDataSets());
        map.put("%algorithms%", run.getAlgorithms());
    }

}
