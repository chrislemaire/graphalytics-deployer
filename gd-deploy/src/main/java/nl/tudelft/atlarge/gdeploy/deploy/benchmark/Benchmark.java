package nl.tudelft.atlarge.gdeploy.deploy.benchmark;

import lombok.Data;
import nl.tudelft.atlarge.gdeploy.core.VariableMappable;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.BenchmarkRun;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.ExperimentSetup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Data
public class Benchmark implements JacksonDeserializable, VariableMappable {

    public static final int ID_BOUND = 100000000;

    private int identifier;

    private String name;

    private ExperimentSetup experimentSetup;

    private List<BenchmarkRun> runs;

    @Override
    public void init() {
        assert experimentSetup != null;
        assert runs != null;

        experimentSetup.init();
        runs.forEach(BenchmarkRun::init);

        identifier = new Random().nextInt(ID_BOUND);
    }

    @Override
    public Map<String, String> getVariableMap() {
        return new HashMap<String, String>() {
            {
                put("%project_id%", String.valueOf(getIdentifier()));
                put("%project_name%", getName());

                putAll(experimentSetup.getVariableMap());
                runs.forEach(run -> putAll(run.getVariableMap()));
            }
        };
    }

}
