package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import lombok.Data;
import nl.tudelft.atlarge.gdeploy.core.VariableMappable;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonDeserializable;
import nl.tudelft.atlarge.gdeploy.deploy.sweep.SingleRunWriter;
import nl.tudelft.atlarge.gdeploy.deploy.sweep.SweepType;
import nl.tudelft.atlarge.gdeploy.deploy.sweep.SweepWriter;
import nl.tudelft.atlarge.gdeploy.deploy.sweep.ThreadProcessSweepWriter;
import nl.tudelft.atlarge.gdeploy.deploy.sweep.ThreadSweepWriter;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@Data
public class BenchmarkRun implements JacksonDeserializable, VariableMappable {

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
