package nl.tudelft.atlarge.gdeploy.deploy.deploy.platform;

import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.PlatformSettings;
import nl.tudelft.atlarge.gdeploy.core.script.PythonScriptBuilder;

public class GraphmatConfigurationWriter extends PlatformConfigurationWriter {

    public GraphmatConfigurationWriter(PythonScriptBuilder builder, PlatformSettings settings) {
        super(builder, settings);
    }

    @Override
    public PythonScriptBuilder write() {
        return null;
    }

}
