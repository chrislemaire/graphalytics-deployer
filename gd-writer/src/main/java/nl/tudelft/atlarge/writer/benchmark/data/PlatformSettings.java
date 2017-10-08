package nl.tudelft.atlarge.writer.benchmark.data;

import lombok.Data;
import lombok.Getter;
import nl.tudelft.atlarge.writer.benchmark.JacksonSerializable;
import nl.tudelft.atlarge.writer.deploy.platform.GiraphConfigurationWriter;
import nl.tudelft.atlarge.writer.deploy.platform.GraphmatConfigurationWriter;
import nl.tudelft.atlarge.writer.deploy.platform.PlatformConfigurationWriter;
import nl.tudelft.atlarge.writer.deploy.platform.PowergraphConfigurationWriter;

import java.util.Map;

@Data
public class PlatformSettings implements JacksonSerializable {

    private enum SoftwarePlatforms {
        NONE(PlatformConfigurationWriter.class),
        GIRAPH(GiraphConfigurationWriter.class),
        GRAPHX(GiraphConfigurationWriter.class),
        POWERGRAPH(PowergraphConfigurationWriter.class),
        GRAPHMAT(GraphmatConfigurationWriter.class);

        SoftwarePlatforms(Class<? extends PlatformConfigurationWriter> writer) {
            this.writer = writer;
        }

        @Getter
        Class<? extends PlatformConfigurationWriter> writer;
    }

    private SoftwarePlatforms platform;

    private Map<String, String> configurations;

    @Override
    public void init() {
        assert platform != SoftwarePlatforms.NONE;
        assert configurations != null;
    }

}
