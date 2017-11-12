package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import lombok.Data;
import lombok.Getter;
import nl.tudelft.atlarge.gdeploy.core.VariableMappable;
import nl.tudelft.atlarge.gdeploy.core.script.RemoteSystem;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonDeserializable;
import nl.tudelft.atlarge.gdeploy.deploy.host.Das5ReserveWriter;
import nl.tudelft.atlarge.gdeploy.deploy.host.HostReserveWriter;
import nl.tudelft.atlarge.gdeploy.deploy.host.NodeType;
import nl.tudelft.atlarge.gdeploy.deploy.host.RemoteHost;

import java.util.HashMap;
import java.util.Map;

@Data
public class SystemSettings implements JacksonDeserializable, VariableMappable {

    private RemoteHost host;

    private NodeType nodeType;

    private String numberOfNodesUsed = "1";

    private String numberOfCoresUsed = "1";

    private String totalReserveTime = "00:00:01";

    @Override
    public void init() {
        assert host != RemoteHost.NONE;
        assert nodeType != NodeType.NONE;
    }

    @Override
    public Map<String, String> getVariableMap() {
        return new HashMap<String, String>() {
            {
                put("%host%", getHost().name());
                put("%node_type%", getNodeType().name());
                put("%no_nodes%", numberOfNodesUsed);
                put("%no_cores%", numberOfCoresUsed);
            }
        };
    }

}
