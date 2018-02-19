package nl.tudelft.atlarge.gdeploy.deploy.host;

import java.util.Map;

public enum NodeType {

    NONE("", 0, 0, null),
    KNL076("knlq", 68, 4, new KnlConfigurationBuilder()
            .clusterMode("QUAD").memoryMode("FLAT").build()),
    KNL077("knlq", 68, 4, new KnlConfigurationBuilder()
            .clusterMode("SNC_4").memoryMode("CACHE").build()),
    DAS5("defq", 16, 2, null),
    INTEL("defq", 68, 4, new KnlConfigurationBuilder()
            .clusterMode("ALL-TO-ALL").memoryMode("FLAT").build()),
    SURF_FLAT("knl_flat", 68, 4, new KnlConfigurationBuilder()
            .clusterMode("ALL-TO-ALL").memoryMode("FLAT").build()),
    SURF_CACHE("knl_cache", 68, 4, new KnlConfigurationBuilder()
            .clusterMode("ALL-TO-ALL").memoryMode("CACHE").build());

    public String partition;

    int numberOfCores;

    int threadsPerCore;

    Map<String, String> configurations;

    NodeType(String partition, int numberOfCores, int threadsPerCore,
            Map<String, String> configurations) {
        this.partition = partition;
        this.numberOfCores = numberOfCores;
        this.threadsPerCore = threadsPerCore;
        this.configurations = configurations;
    }

    public String generateJson(String count, String threadsInUse, String processesInUse) {
        StringBuilder builder = new StringBuilder("{")
                .append("\"name\": \"").append(name()).append("\", ")
                .append("\"numberOfCores\": ").append(numberOfCores).append(", ")
                .append("\"threadsPerCore\": ").append(threadsPerCore).append(", ")
                .append("\"processesInUse\": ").append(processesInUse).append(", ")
                .append("\"threadsInUse\": ").append(threadsInUse).append(", ")
                .append("\"count\": ").append(count).append(", ")
                .append("\"configurations\": [");
        configurations.forEach((k, v) ->
                builder.append("\"").append(k).append("\": \"").append(v).append("\", "));
        builder.replace(builder.length() - 3, builder.length() - 1, "]}");

        return builder.toString();
    }

}
