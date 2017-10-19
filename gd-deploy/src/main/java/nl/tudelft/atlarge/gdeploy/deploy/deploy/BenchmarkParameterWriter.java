package nl.tudelft.atlarge.gdeploy.deploy.deploy;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.ExperimentSetup;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.PlatformSettings;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.SystemSettings;

public class BenchmarkParameterWriter extends ScriptWriter {

    private Benchmark benchmark;

    public BenchmarkParameterWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder);

        this.benchmark = benchmark;
    }

    private ShellScriptBuilder setVariable(String name, String value) {
        return builder.appendLine(name.toUpperCase() + '=' + value);
    }

    @Override
    public ShellScriptBuilder write() {
        ExperimentSetup experimentSetup = benchmark.getExperimentSetup();
        SystemSettings targetSystem = experimentSetup.getTargetSystem();
        PlatformSettings targetPlatform = experimentSetup.getTargetPlatform();

        setVariable("host", targetSystem.getHost().toString());
        setVariable("node_type", targetSystem.getNodeType().toString());
        setVariable("no_nodes", targetSystem.getNumberOfNodesUsed());
        setVariable("no_cpus", targetSystem.getNumberOfCpusUsed());

        setVariable("platform", targetPlatform.getPlatform().toString());

        return builder;
    }

}
