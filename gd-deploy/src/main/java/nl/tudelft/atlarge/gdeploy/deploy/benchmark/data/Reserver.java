package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.host.Das5ReserveWriter;
import nl.tudelft.atlarge.gdeploy.deploy.host.HostReserveWriter;
import nl.tudelft.atlarge.gdeploy.deploy.host.SlurmReserveWriter;
import nl.tudelft.atlarge.gdeploy.deploy.host.TorqueReserveWriter;

public enum Reserver {

    NONE(HostReserveWriter.class),
    PRESERVE(Das5ReserveWriter.class),
    TORQUE(TorqueReserveWriter.class),
    SLURM(SlurmReserveWriter.class);

    public Class<? extends HostReserveWriter> writer;

    Reserver(Class<? extends HostReserveWriter> writer) {
        this.writer = writer;
    }

    public HostReserveWriter newInstance(ShellScriptBuilder builder, Benchmark benchmark) {
        try {
            return (HostReserveWriter) writer.getConstructors()[0]
                    .newInstance(builder, benchmark);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
