package nl.tudelft.atlarge.gdeploy.writer.benchmark.data;

import lombok.Data;
import lombok.Getter;
import nl.tudelft.atlarge.gdeploy.writer.benchmark.JacksonSerializable;
import nl.tudelft.atlarge.gdeploy.writer.deploy.platform.GiraphConfigurationWriter;
import nl.tudelft.atlarge.gdeploy.writer.deploy.platform.GraphmatConfigurationWriter;
import nl.tudelft.atlarge.gdeploy.writer.deploy.platform.PlatformConfigurationWriter;
import nl.tudelft.atlarge.gdeploy.writer.deploy.platform.PowergraphConfigurationWriter;
import nl.tudelft.atlarge.gdeploy.writer.script.ShellScriptBuilder;

import java.util.Map;

@Data
public class PlatformSettings implements JacksonSerializable {

    private enum SoftwarePlatforms {
        NONE(PlatformConfigurationWriter.class),
        GIRAPH(GiraphConfigurationWriter.class),
        GRAPHX(GiraphConfigurationWriter.class),
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

    private Map<String, String> configurations;

    @Override
    public void init() {
        assert platform != SoftwarePlatforms.NONE;
        assert configurations != null;
    }

}