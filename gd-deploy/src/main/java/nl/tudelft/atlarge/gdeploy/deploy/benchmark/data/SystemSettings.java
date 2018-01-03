package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import nl.tudelft.atlarge.gdeploy.core.VariableMappable;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonDeserializable;
import nl.tudelft.atlarge.gdeploy.deploy.host.NodeType;
import nl.tudelft.atlarge.gdeploy.deploy.host.RemoteHost;

import java.util.HashMap;
import java.util.Map;

public class SystemSettings implements JacksonDeserializable, VariableMappable {

    public RemoteHost host;

    public NodeType nodeType;

    public String numberOfNodesUsed = "1";

    public String numberOfCoresUsed = "1";

    public String totalReserveTime = "00:00:01";

    @Override
    public void init() {
        assert host != RemoteHost.NONE;
        assert nodeType != NodeType.NONE;
    }

    @Override
    public Map<String, String> getVariableMap() {
        return new HashMap<String, String>() {
            {
                put("%host%", host.name());
                put("%node_type%", nodeType.name());
                put("%no_nodes%", numberOfNodesUsed);
                put("%no_cores%", numberOfCoresUsed);
            }
        };
    }

}
