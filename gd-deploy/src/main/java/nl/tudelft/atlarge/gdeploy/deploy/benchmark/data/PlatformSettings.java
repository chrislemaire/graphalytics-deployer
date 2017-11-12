package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import lombok.Data;
import lombok.Getter;
import nl.tudelft.atlarge.gdeploy.core.VariableMappable;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonDeserializable;
import nl.tudelft.atlarge.gdeploy.deploy.platform.GraphmatRunWriter;
import nl.tudelft.atlarge.gdeploy.deploy.platform.PlatformRunWriter;
import nl.tudelft.atlarge.gdeploy.deploy.platform.PowergraphRunWriter;
import nl.tudelft.atlarge.gdeploy.deploy.platform.SoftwarePlatforms;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

@Data
public class PlatformSettings implements JacksonDeserializable, VariableMappable {

    private SoftwarePlatforms platform;

    private String platformHome;

    @Override
    public void init() {
        assert platform != SoftwarePlatforms.NONE;
    }

    @Override
    public Map<String, String> getVariableMap() {
        return new HashMap<String, String>() {
            {
                put("%platform%", getPlatform().name());
                put("%platform_home%", getPlatformHome());
            }
        };
    }

}
