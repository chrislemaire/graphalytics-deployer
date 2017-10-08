package nl.tudelft.atlarge.gdeploy.writer.benchmark.data;

import lombok.Data;
import lombok.Getter;
import nl.tudelft.atlarge.gdeploy.writer.benchmark.JacksonSerializable;
import nl.tudelft.atlarge.gdeploy.writer.deploy.sweep.ProcessSweepWriter;
import nl.tudelft.atlarge.gdeploy.writer.deploy.sweep.SweepWriter;

@Data
public class BenchmarkRun implements JacksonSerializable {

    private enum SweepType {
        NONE(SweepWriter.class),
        PROCESSES_VS_THREADS(ProcessSweepWriter.class);

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
}
