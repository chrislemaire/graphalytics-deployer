package nl.tudelft.atlarge.gdeploy.writer.benchmark;

import lombok.Data;
import nl.tudelft.atlarge.gdeploy.writer.benchmark.data.BenchmarkRun;
import nl.tudelft.atlarge.gdeploy.writer.benchmark.data.ExperimentSetup;

import java.util.List;

@Data
public class Benchmark implements JacksonSerializable {

    private ExperimentSetup experimentSetup;

    private List<BenchmarkRun> runs;

    @Override
    public void init() {
        assert experimentSetup != null;
        assert runs != null;

        experimentSetup.init();
        runs.forEach(BenchmarkRun::init);
    }
}
