package nl.tudelft.atlarge.gdeploy.deploy.benchmark;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class BenchmarkJsonParserTest {

    private static String INTERNAL_TEST_EXP = "/experiments/test-experiment.json";

    @Test
    void fromInternalShouldWork() throws IOException {
        Benchmark benchmark = BenchmarkJsonParser.fromInternalPath(INTERNAL_TEST_EXP);

        Assertions.assertThat(benchmark.runs.get(0).algorithms)
                .isEqualTo("BFS");
    }

}
