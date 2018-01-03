package nl.tudelft.atlarge.gdeploy.deploy.benchmark;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class BenchmarkJsonParser {

    private static ObjectMapper mapper = new ObjectMapper()
            .enable(JsonParser.Feature.ALLOW_COMMENTS);

    private static Benchmark initialize(Benchmark benchmark) {
        benchmark.init();
        return benchmark;
    }

    public static Benchmark fromInternalPath(String internal)
            throws IOException {
        return initialize(mapper.readValue(
                BenchmarkJsonParser.class.getResource(internal),
                Benchmark.class));
    }

    public static Benchmark fromFile(File file)
            throws IOException {
        return initialize(mapper.readValue(file, Benchmark.class));
    }

}
