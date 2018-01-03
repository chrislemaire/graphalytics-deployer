package nl.tudelft.atlarge.gdeploy.deploy.benchmark;

import nl.tudelft.atlarge.gdeploy.core.VariableMappable;
import nl.tudelft.atlarge.gdeploy.core.script.RemoteSystem;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.BenchmarkRun;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.ExperimentSetup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Benchmark implements JacksonDeserializable, VariableMappable {

    public static final int ID_BOUND = 100000000;

    public int identifier;

    public String name;

    public ExperimentSetup experimentSetup;

    public List<BenchmarkRun> runs;

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
                put("%project_id%", String.valueOf(identifier));
                put("%project_name%", name);
                put("%deployer_root%", RemoteSystem.getNative().deployer());

                putAll(experimentSetup.getVariableMap());
                runs.forEach(run -> putAll(run.getVariableMap()));
            }
        };
    }

}
