package nl.tudelft.atlarge.gdeploy.deploy.host;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

public class TorqueReserveWriter extends HostReserveWriter {

    public TorqueReserveWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);

        command = "qsub -l select=" + settings.numberOfNodesUsed
                + ":ncpus=" + (272 * Integer.parseInt(settings.numberOfNodesUsed))
                + " -lplace=excl ${SCRIPT_FILE}";
    }

}
