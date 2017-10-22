package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import lombok.Data;
import lombok.Getter;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonDeserializable;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.platform.GraphmatConfigurationWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.platform.PlatformConfigurationWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.platform.PowergraphConfigurationWriter;

import java.util.HashMap;
import java.util.Map;

@Data
public class PlatformSettings implements JacksonDeserializable {

    public enum SoftwarePlatforms {
        NONE(PlatformConfigurationWriter.class),
        POWERGRAPH(PowergraphConfigurationWriter.class),
        GRAPHMAT(GraphmatConfigurationWriter.class);

        @Getter
        Class<? extends PlatformConfigurationWriter> writer;

        SoftwarePlatforms(Class<? extends PlatformConfigurationWriter> writer) {
            this.writer = writer;
        }

        public PlatformConfigurationWriter newInstance(ShellScriptBuilder builder, PlatformSettings platformSettings) {
            try {
                return (PlatformConfigurationWriter) writer.getConstructors()[0]
                        .newInstance(builder, platformSettings);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private SoftwarePlatforms platform;

    @Override
    public void init() {
        assert platform != SoftwarePlatforms.NONE;
    }

    @Override
    public Map<String, String> getVariableMap() {
        return new HashMap<String, String>() {
            {
                put("%platform%", getPlatform().toString());
            }
        };
    }

}
