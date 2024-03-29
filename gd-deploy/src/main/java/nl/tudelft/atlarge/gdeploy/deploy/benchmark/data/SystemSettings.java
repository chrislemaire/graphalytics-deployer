package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import lombok.Data;
import lombok.Getter;
import nl.tudelft.atlarge.gdeploy.core.script.RemoteSystem;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonDeserializable;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.host.Das5ReserveWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.host.HostReserveWriter;

import java.util.HashMap;
import java.util.Map;

@Data
public class SystemSettings implements JacksonDeserializable {

    public enum RemoteHost {
        NONE(HostReserveWriter.class, RemoteSystem.NATIVE_WINDOWS),
        DAS5VU(Das5ReserveWriter.class, RemoteSystem.DAS5VU),
        DAS5TUD(Das5ReserveWriter.class, RemoteSystem.DAS5TUD),
        INTEL(HostReserveWriter.class, RemoteSystem.DAS5TUD),
        SURF_SARA(HostReserveWriter.class, RemoteSystem.DAS5TUD);

        @Getter
        private Class<? extends HostReserveWriter> writer;

        @Getter
        private RemoteSystem remote;

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

    public enum NodeType {
        NONE(""),
        KNL("-q knlq"),
        DAS5("");

        @Getter
        String queueArgs;

        NodeType(String queueArgs) {
            this.queueArgs = queueArgs;
        }
    }

    private RemoteHost host;

    private NodeType nodeType;

    private String numberOfNodesUsed = "1";

    private String numberOfCpusUsed = "1";

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
                put("%no_cpu%", numberOfCpusUsed);
            }
        };
    }

}
