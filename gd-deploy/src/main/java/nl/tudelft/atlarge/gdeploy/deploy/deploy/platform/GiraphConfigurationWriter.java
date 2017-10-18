package nl.tudelft.atlarge.gdeploy.deploy.deploy.platform;

import nl.tudelft.atlarge.gdeploy.core.script.PythonScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.PlatformSettings;

public class GiraphConfigurationWriter extends PlatformConfigurationWriter {

    public GiraphConfigurationWriter(PythonScriptBuilder builder, PlatformSettings settings) {
        super(builder, settings);
    }

    @Override
    public PythonScriptBuilder write() {
        return null;
    }
}
