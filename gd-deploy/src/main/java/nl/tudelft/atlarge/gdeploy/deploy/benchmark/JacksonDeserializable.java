package nl.tudelft.atlarge.gdeploy.deploy.benchmark;

import java.util.Map;

public interface JacksonDeserializable {

    void init();

    Map<String, String> getVariableMap();

}
