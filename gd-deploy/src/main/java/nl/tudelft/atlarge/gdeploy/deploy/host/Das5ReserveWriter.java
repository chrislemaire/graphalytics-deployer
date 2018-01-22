package nl.tudelft.atlarge.gdeploy.deploy.host;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

import java.util.Map;

public class Das5ReserveWriter extends HostReserveWriter {

    public Das5ReserveWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);
    }

    @Override
    protected void specificReplacements(Map<String, String> map) {
        super.specificReplacements(map);

        map.put("%partition%", settings.nodeType.partition);
        map.put("%time%", settings.totalReserveTime);
    }

    @Override
    public ShellScriptBuilder writeRequest() {
        super.writeRequest();

        return writeUnsafe("/scripts/reserving/das5-request.sh");
    }

    @Override
    public ShellScriptBuilder writeCancel() {
        super.writeCancel();

        return writeUnsafe("/scripts/reserving/das5-cancel.sh");
    }

}
