package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import lombok.Data;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonDeserializable;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.sweep.SingleRunWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.sweep.SweepWriter;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@Data
public class BenchmarkRun implements JacksonDeserializable {

    public enum SweepType {
        NONE(SingleRunWriter.class),
        SINGLE_RUN(SingleRunWriter.class);

        SweepType(Class<? extends SweepWriter> writer) {
            this.writer = writer;
        }

        Class<? extends SweepWriter> writer;

        public SweepWriter newInstance(ShellScriptBuilder builder, Benchmark benchmark) {
            try {
                Constructor<? extends SweepWriter> cons =
                        writer.getDeclaredConstructor(ShellScriptBuilder.class, Benchmark.class);
                return cons.newInstance(builder, benchmark);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private SweepType sweepType;

    private String dataSets;

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
