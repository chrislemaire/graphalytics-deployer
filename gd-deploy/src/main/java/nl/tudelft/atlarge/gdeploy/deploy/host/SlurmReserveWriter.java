package nl.tudelft.atlarge.gdeploy.deploy.host;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

public class SlurmReserveWriter extends HostReserveWriter {

    public SlurmReserveWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);

        command = "sbatch -N $NO_NODES"
                + " -p " + settings.nodeType.partition
                + " -t " + settings.totalReserveTime
                + " " + SCRIPT_FILE;
    }

}
