package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import nl.tudelft.atlarge.gdeploy.core.VariableMappable;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonDeserializable;
import nl.tudelft.atlarge.gdeploy.deploy.platform.SoftwarePlatforms;

import java.util.HashMap;
import java.util.Map;

public class PlatformSettings implements JacksonDeserializable, VariableMappable {

    public SoftwarePlatforms platform;

    public String platformHome;

    @Override
    public void init() {
        assert platform != SoftwarePlatforms.NONE;
    }

    @Override
    public Map<String, String> getVariableMap() {
        return new HashMap<String, String>() {
            {
                put("%platform%", platform.name());
                put("%platform_home%", platformHome);
            }
        };
    }

}
