package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import lombok.Data;
import lombok.Getter;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonSerializable;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.host.Das5ReserveWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.host.HostReserveWriter;
import nl.tudelft.atlarge.gdeploy.deploy.deploy.platform.PlatformConfigurationWriter;
import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;

@Data
public class SystemSettings implements JacksonSerializable {

    public enum RemoteHost {
        NONE(HostReserveWriter.class),
        DAS5VU(Das5ReserveWriter.class),
        DAS5TUD(Das5ReserveWriter.class),
        INTEL(HostReserveWriter.class),
        SURF_SARA(HostReserveWriter.class);

        @Getter
        Class<? extends HostReserveWriter> writer;

        RemoteHost(Class<? extends HostReserveWriter> writer) {
            this.writer = writer;
        }

        public PlatformConfigurationWriter newInstance(ShellScriptBuilder builder, HostReserveWriter platformSettings) {
            try {
                return (PlatformConfigurationWriter) writer.getConstructors()[0]
                        .newInstance(builder, platformSettings);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
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
