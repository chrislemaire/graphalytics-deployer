package nl.tudelft.atlarge.writer.benchmark.data;

import lombok.Data;
import nl.tudelft.atlarge.writer.benchmark.JacksonSerializable;

@Data
public class BenchmarkRun implements JacksonSerializable {

    private enum SweepType {
        NONE,
        PROCESSES_VS_THREADS
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
}
