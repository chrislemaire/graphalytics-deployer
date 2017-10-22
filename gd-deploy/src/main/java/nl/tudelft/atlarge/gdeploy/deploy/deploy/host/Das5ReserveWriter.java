package nl.tudelft.atlarge.gdeploy.deploy.deploy.host;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class Das5ReserveWriter extends HostReserveWriter {

    public Das5ReserveWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder, benchmark);

        try {
            this.readLines("/scripts/das5-request.sh");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void specificReplacements(Map<String, String> map) {
        super.specificReplacements(map);

        map.put("%preserve_args%",
                "-# " + settings.getNumberOfNodesUsed() + ' '
                + settings.getNodeType().getQueueArgs()
                + " -t " + settings.getTotalReserveTime());
    }

    @Override
    public ShellScriptBuilder write() {
        this.builder.startBuildingSshRemoteScript(settings.getHost().getRemote());

        return super.write();
    }
}
