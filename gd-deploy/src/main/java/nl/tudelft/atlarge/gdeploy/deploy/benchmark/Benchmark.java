package nl.tudelft.atlarge.gdeploy.deploy.benchmark;

import lombok.Data;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.BenchmarkRun;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.ExperimentSetup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Benchmark implements JacksonDeserializable {

    private String name;

    private ExperimentSetup experimentSetup;

    private List<BenchmarkRun> runs;

    @Override
    public void init() {
        assert experimentSetup != null;
        assert runs != null;

        experimentSetup.init();
        runs.forEach(BenchmarkRun::init);
    }

    @Override
    public Map<String, String> getVariableMap() {
        return new HashMap<String, String>() {
            {
                put("%project_name%", getName());

                putAll(experimentSetup.getVariableMap());
                runs.forEach(run -> putAll(run.getVariableMap()));
            }
        };
    }

}
