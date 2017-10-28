package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import lombok.Data;
import lombok.Getter;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonDeserializable;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.platform.GraphmatRunWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.platform.PlatformRunWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.platform.PowergraphRunWriter;

import java.lang.reflect.Constructor;
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

        public PlatformRunWriter newInstance(ShellScriptBuilder builder, Benchmark benchmark) {
            try {
                Constructor<? extends PlatformRunWriter> cons =
                        writer.getDeclaredConstructor(ShellScriptBuilder.class, Benchmark.class);
                return cons.newInstance(builder, benchmark);
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
