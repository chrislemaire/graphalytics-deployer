package nl.tudelft.atlarge.gdeploy.deploy.host;

import nl.tudelft.atlarge.gdeploy.core.script.RemoteSystem;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

public enum RemoteHost {

    NONE(RemoteSystem.NATIVE_WINDOWS),
    DAS5VU(RemoteSystem.DAS5VU),
    DAS5TUD(RemoteSystem.DAS5TUD),
    INTEL(RemoteSystem.DAS5TUD),
    SURF_SARA(RemoteSystem.DAS5TUD);

    public RemoteSystem remote;

    RemoteHost(RemoteSystem remote) {
        this.remote = remote;
    }

}
