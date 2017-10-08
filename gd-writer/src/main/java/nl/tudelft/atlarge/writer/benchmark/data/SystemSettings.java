package nl.tudelft.atlarge.writer.benchmark.data;

import lombok.Data;
import nl.tudelft.atlarge.writer.benchmark.JacksonSerializable;

@Data
public class SystemSettings implements JacksonSerializable {

    private enum RemoteHost {
        NONE,
        DAS5VU,
        DAS5TUD,
        INTEL,
        SURF_SARA
    }

    private enum NodeType {
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