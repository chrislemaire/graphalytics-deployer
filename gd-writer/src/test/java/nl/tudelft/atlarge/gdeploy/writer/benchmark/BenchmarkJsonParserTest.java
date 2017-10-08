package nl.tudelft.atlarge.gdeploy.writer.benchmark;

import nl.tudelft.atlarge.writer.benchmark.Benchmark;
import nl.tudelft.atlarge.writer.benchmark.BenchmarkJsonParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class BenchmarkJsonParserTest {

    private static String INTERNAL_TEST_EXP = "/test-experiment.json";

    @Test
    void fromInternalShouldWork() throws IOException {
        Benchmark benchmark = BenchmarkJsonParser.fromInternalPath(INTERNAL_TEST_EXP);

        assertThat(benchmark.getRuns().get(0).getAlgorithms())
                .isEqualTo("BFS");
    }

}
