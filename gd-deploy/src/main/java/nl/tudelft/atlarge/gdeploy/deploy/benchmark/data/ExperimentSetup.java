package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import nl.tudelft.atlarge.gdeploy.core.VariableMappable;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonDeserializable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris Lemaire on 6-9-2017.
 */
public class ExperimentSetup implements JacksonDeserializable, VariableMappable {

    public Reserver reserver = Reserver.NONE;

    public SystemSettings targetSystem;

    public PlatformSettings targetPlatform;

    @Override
    public void init() {
        assert reserver != null;
        assert targetSystem != null;
        assert targetPlatform != null;

        targetSystem.init();
        targetPlatform.init();
    }

    @Override
    public Map<String, String> getVariableMap() {
        return new HashMap<String, String>() {
            {
                put("%reserver%", reserver.name());
                putAll(targetSystem.getVariableMap());
                putAll(targetPlatform.getVariableMap());
            }
        };
    }

}
