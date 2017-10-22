package nl.tudelft.atlarge.gdeploy.deploy.deploy.platform;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.PlatformSettings;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.ScriptCopyWriter;

public abstract class PlatformConfigurationWriter extends ScriptCopyWriter {

    PlatformSettings settings;

    PlatformConfigurationWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);

        this.settings = benchmark.getExperimentSetup().getTargetPlatform();
    }

}
