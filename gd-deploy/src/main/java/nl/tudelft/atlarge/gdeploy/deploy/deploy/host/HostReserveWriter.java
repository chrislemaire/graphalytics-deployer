package nl.tudelft.atlarge.gdeploy.deploy.deploy.host;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.SystemSettings;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.ScriptCopyWriter;

public abstract class HostReserveWriter extends ScriptCopyWriter {

    SystemSettings settings;

    HostReserveWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);

        this.settings = benchmark.getExperimentSetup().getTargetSystem();
    }

}
