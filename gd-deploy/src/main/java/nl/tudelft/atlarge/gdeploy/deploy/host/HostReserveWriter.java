package nl.tudelft.atlarge.gdeploy.deploy.host;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.data.SystemSettings;
import nl.tudelft.atlarge.gdeploy.deploy.BenchmarkCopyWriter;

import java.util.Map;

public abstract class HostReserveWriter extends BenchmarkCopyWriter {

    public static final String SCRIPT_FILE = "./script.sh";

    String command = "ssh ${IPS[0]}";

    SystemSettings settings;

    HostReserveWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);

        this.settings = benchmark.experimentSetup.targetSystem;
    }

    public ShellScriptBuilder writeRequest() {
        return writeUnsafe("/scripts/reserving/prefix-setup.sh");
    }

    public ShellScriptBuilder writeCancel() {
        return builder;
    }

    @Override
    protected void specificReplacements(Map<String, String> map) {
        super.specificReplacements(map);

        map.put("%run_cmd%", command);
        map.put("%script_file%", SCRIPT_FILE);
    }

}
