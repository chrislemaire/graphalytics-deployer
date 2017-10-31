package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import lombok.Data;
import nl.tudelft.atlarge.gdeploy.core.VariableMappable;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonDeserializable;
import nl.tudelft.atlarge.gdeploy.deploy.sweep.SingleRunWriter;
import nl.tudelft.atlarge.gdeploy.deploy.sweep.SweepWriter;
import nl.tudelft.atlarge.gdeploy.deploy.sweep.ThreadProcessSweepWriter;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@Data
public class BenchmarkRun implements JacksonDeserializable, VariableMappable {

    /**
     * The type of Sweep applied during this Run.
     * The Sweep is a kind of loop that should happen. The
     * loop changes parameters and then re-executes the
     * Graphalytics benchmark each iteration.
     */
    public enum SweepType {
        NONE(SingleRunWriter.class),
        SINGLE_RUN(SingleRunWriter.class),
        THREAD_PROCESS(ThreadProcessSweepWriter.class);

        SweepType(Class<? extends SweepWriter> writer) {
            this.writer = writer;
        }

        Class<? extends SweepWriter> writer;

        public SweepWriter newInstance(ShellScriptBuilder builder, Benchmark benchmark, BenchmarkRun run) {
            try {
                Constructor<? extends SweepWriter> cons =
                        writer.getDeclaredConstructor(ShellScriptBuilder.class, Benchmark.class, BenchmarkRun.class);
                return cons.newInstance(builder, benchmark, run);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * The type of sweep this run executes.
     */
    private SweepType sweepType;

    /**
     * The data-sets this run executes on.
     */
    private String dataSets;

    /**
     * The algorithms this run executes.
     */
    private String algorithms;

    @Override
    public void init() {
        assert sweepType != null;
        assert dataSets!= null;
        assert algorithms != null;
    }

    @Override
    public Map<String, String> getVariableMap() {
        return new HashMap<String, String>() {
            {
                put("%sweep_type%", getSweepType().name());
                put("%data_sets%", getDataSets());
                put("%algorithms%", getAlgorithms());
            }
        };
    }

}
