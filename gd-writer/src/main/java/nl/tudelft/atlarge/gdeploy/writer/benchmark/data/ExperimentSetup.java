package nl.tudelft.atlarge.gdeploy.writer.benchmark.data;

import lombok.Data;
import nl.tudelft.atlarge.gdeploy.writer.benchmark.JacksonSerializable;

/**
 * Created by Chris Lemaire on 6-9-2017.
 */
@Data
public class ExperimentSetup implements JacksonSerializable {

    private SystemSettings targetSystem;

    private PlatformSettings targetPlatform;

    @Override
    public void init() {
        assert targetSystem != null;
        assert targetPlatform != null;

        targetSystem.init();
        targetPlatform.init();
    }

}
