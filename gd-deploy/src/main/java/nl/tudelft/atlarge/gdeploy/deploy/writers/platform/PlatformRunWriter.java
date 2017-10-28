package nl.tudelft.atlarge.gdeploy.deploy.writers.platform;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.PlatformSettings;
import nl.tudelft.atlarge.gdeploy.deploy.writers.ScriptCopyWriter;

public abstract class PlatformRunWriter extends ScriptCopyWriter {

    PlatformSettings settings;

    public PlatformRunWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);

        this.settings = benchmark.getExperimentSetup().getTargetPlatform();
    }

}
