package nl.tudelft.atlarge.gdeploy.deploy.platform;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.PlatformSettings;
import nl.tudelft.atlarge.gdeploy.deploy.BenchmarkCopyWriter;

public abstract class PlatformRunWriter extends BenchmarkCopyWriter {

    PlatformSettings settings;

    public PlatformRunWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);

        this.settings = benchmark.experimentSetup.targetPlatform;
    }

    public ShellScriptBuilder writeSpecifics() {
        super.write();

        return writeUnsafe("/scripts/misc/write-report-metadata.sh");
    }

}
