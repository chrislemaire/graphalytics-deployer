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

        map.put("%partition%", settings.getNodeType().getPartition());
        map.put("%time%", settings.getTotalReserveTime());
    }

    @Override
    public ShellScriptBuilder writeRequest() {
        return writeUnsafe("/scripts/reserving/das5-request.sh");
    }

    @Override
    public ShellScriptBuilder writeCancel() {
        return writeUnsafe("/scripts/reserving/das5-cancel.sh");
    }

}
