package nl.tudelft.atlarge.gdeploy.deploy.host;

import nl.tudelft.atlarge.gdeploy.core.script.RemoteSystem;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

public enum RemoteHost {

    NONE(HostReserveWriter.class, RemoteSystem.NATIVE_WINDOWS),
    DAS5VU(Das5ReserveWriter.class, RemoteSystem.DAS5VU),
    DAS5TUD(Das5ReserveWriter.class, RemoteSystem.DAS5TUD),
    INTEL(HostReserveWriter.class, RemoteSystem.DAS5TUD),
    SURF_SARA(HostReserveWriter.class, RemoteSystem.DAS5TUD);

    public Class<? extends HostReserveWriter> writer;

    public RemoteSystem remote;

    RemoteHost(Class<? extends HostReserveWriter> writer, RemoteSystem remote) {
        this.writer = writer;
        this.remote = remote;
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
