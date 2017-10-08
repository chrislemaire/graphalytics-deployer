package nl.tudelft.atlarge.writer.benchmark.data;

import lombok.Data;
import nl.tudelft.atlarge.writer.benchmark.JacksonSerializable;

import java.util.Map;

@Data
public class PlatformSettings implements JacksonSerializable {

    private enum SoftwarePlatforms {
        NONE,
        GIRAPH,
        GRAPHX,
        POWERGRAPH,
        GRAPHMAT
    }

    private SoftwarePlatforms platform;

    private Map<String, String> configurations;

    @Override
    public void init() {
        assert platform != SoftwarePlatforms.NONE;
        assert configurations != null;
    }

}
