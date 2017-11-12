package nl.tudelft.atlarge.gdeploy.deploy.host;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum NodeType {

    NONE("", 0, 0, null),
    KNL076("knlq", 64, 4, new HashMap<String, String>() {{
        put("memoryMode", "FLAT");
        put("clusterMode", "QUAD");
    }}),
    DAS5("all", 16, 2, null);

    @Getter
    String partition;

    int nCores;

    int threadsPerCore;

    Map<String, String> configurations;

    NodeType(String partition, int nCores, int threadsPerCore, Map<String, String> configurations) {
        this.partition = partition;
        this.nCores = nCores;
        this.threadsPerCore = threadsPerCore;
        this.configurations = configurations;
    }

}
