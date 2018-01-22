package nl.tudelft.atlarge.gdeploy.deploy.host;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

import java.util.Map;

public class SlurmReserveWriter extends HostReserveWriter {

    public SlurmReserveWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);

        commandPrefix = "sbatch -N $NO_NODES"
                + " -p " + settings.nodeType.partition
                + " -t " + settings.totalReserveTime;
    }

}
