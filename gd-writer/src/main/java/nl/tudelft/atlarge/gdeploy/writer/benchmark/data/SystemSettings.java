package nl.tudelft.atlarge.gdeploy.writer.benchmark.data;

import lombok.Data;
import lombok.Getter;
import nl.tudelft.atlarge.gdeploy.writer.benchmark.JacksonSerializable;
import nl.tudelft.atlarge.gdeploy.writer.deploy.host.Das5ReserveWriter;
import nl.tudelft.atlarge.gdeploy.writer.deploy.host.HostReserveWriter;

@Data
public class SystemSettings implements JacksonSerializable {

    public enum RemoteHost {
        NONE(HostReserveWriter.class),
        DAS5VU(Das5ReserveWriter.class),
        DAS5TUD(Das5ReserveWriter.class),
        INTEL(HostReserveWriter.class),
        SURF_SARA(HostReserveWriter.class);

        RemoteHost(Class<? extends HostReserveWriter> writer) {
            this.writer = writer;
        }

        @Getter
        Class<? extends HostReserveWriter> writer;
    }

    public enum NodeType {
        NONE,
        KNL,
        DAS5
    }

    private RemoteHost host;

    private NodeType nodeType;

    private String numberOfNodesUsed = "1";

    private String numberOfCpusUsed = "1";

    @Override
    public void init() {
        assert host != RemoteHost.NONE;
        assert nodeType != NodeType.NONE;
    }
}
