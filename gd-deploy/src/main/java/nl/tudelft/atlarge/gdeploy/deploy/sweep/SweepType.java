package nl.tudelft.atlarge.gdeploy.deploy.sweep;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.BenchmarkRun;

import java.lang.reflect.Constructor;

/**
 * The type of Sweep applied during this Run.
 * The Sweep is a kind of loop that should happen. The
 * loop changes parameters and then re-executes the
 * Graphalytics benchmark each iteration.
 */
public enum SweepType {
    NONE(SingleRunWriter.class),
    SINGLE_RUN(SingleRunWriter.class),
    THREAD_PROCESS(ThreadProcessSweepWriter.class),
    THREAD(ThreadSweepWriter.class);

    SweepType(Class<? extends SweepWriter> writer) {
        this.writer = writer;
    }

    Class<? extends SweepWriter> writer;

    public SweepWriter newInstance(ShellScriptBuilder builder, Benchmark benchmark, BenchmarkRun run) {
        try {
            Constructor<? extends SweepWriter> cons = writer.getDeclaredConstructor(
                            ShellScriptBuilder.class, Benchmark.class, BenchmarkRun.class);
            return cons.newInstance(builder, benchmark, run);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
