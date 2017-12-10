package nl.tudelft.atlarge.gdeploy.deploy.host;

import java.util.HashMap;
import java.util.Map;

public class KnlConfigurationBuilder {

    private static final String CLUSTER_MODE_NAME = "cluster-mode";
    private static final String MEMORY_MODE_NAME = "memory-mode";

    private Map<String, String> configurations;

    public KnlConfigurationBuilder() {
        configurations = new HashMap<>();

        configurations.put(CLUSTER_MODE_NAME, "QUAD");
        configurations.put(MEMORY_MODE_NAME, "FLAT");
    }

    public KnlConfigurationBuilder put(String config, String value) {
        this.configurations.put(config, value);

        return this;
    }

    public KnlConfigurationBuilder clusterMode(String value) {
        return this.put(CLUSTER_MODE_NAME, value);
    }

    public KnlConfigurationBuilder memoryMode(String value) {
        return this.put(MEMORY_MODE_NAME, value);
    }

    public Map<String, String> build() {
        return this.configurations;
    }

}
