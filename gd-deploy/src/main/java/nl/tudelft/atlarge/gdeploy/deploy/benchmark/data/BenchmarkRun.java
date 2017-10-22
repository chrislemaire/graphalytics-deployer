package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import lombok.Data;
import lombok.Getter;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonDeserializable;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.sweep.SweepWriter;

import java.util.HashMap;
import java.util.Map;

@Data
public class BenchmarkRun implements JacksonDeserializable {

    private enum SweepType {
        NONE(SweepWriter.class);

        SweepType(Class<? extends SweepWriter> writer) {
            this.writer = writer;
        }

        @Getter
        Class<? extends SweepWriter> writer;
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
                put("%sweep_type%", getSweepType().toString());
                put("%data_sets%", getDataSets());
                put("%algorithms%", getAlgorithms());
            }
        };
    }

}
