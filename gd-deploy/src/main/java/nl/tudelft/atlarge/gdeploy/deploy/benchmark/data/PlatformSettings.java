package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import lombok.Data;
import lombok.Getter;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonDeserializable;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.platform.GraphmatRunWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.platform.PlatformRunWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.platform.PowergraphRunWriter;

import java.util.HashMap;
import java.util.Map;

@Data
public class PlatformSettings implements JacksonDeserializable {

    public enum SoftwarePlatforms {
        NONE(PlatformRunWriter.class),
        POWERGRAPH(PowergraphRunWriter.class),
        GRAPHMAT(GraphmatRunWriter.class);

        @Getter
        Class<? extends PlatformRunWriter> writer;

        SoftwarePlatforms(Class<? extends PlatformRunWriter> writer) {
            this.writer = writer;
        }

        public PlatformRunWriter newInstance(ShellScriptBuilder builder, PlatformSettings platformSettings) {
            try {
                return (PlatformRunWriter) writer.getConstructors()[0]
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
                put("%platform%", getPlatform().name());
            }
        };
    }

}
