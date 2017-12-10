package nl.tudelft.atlarge.gdeploy.deploy.host;

import lombok.Getter;

import java.util.Map;

public enum NodeType {

    NONE("", 0, 0, null),
    KNL076("knlq", 64, 4, new KnlConfigurationBuilder()
            .clusterMode("QUAD").memoryMode("FLAT").build()),
    KNL077("knlq", 64, 4, new KnlConfigurationBuilder()
            .clusterMode("SNC_4").memoryMode("CACHE").build()),
    DAS5("all", 16, 2, null);

    @Getter
    String partition;

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

    public String generateJson(int count, int threadsInUse, int processesInUse) {
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
