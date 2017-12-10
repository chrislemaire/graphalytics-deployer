package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import lombok.Data;
import nl.tudelft.atlarge.gdeploy.core.VariableMappable;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonDeserializable;
import nl.tudelft.atlarge.gdeploy.deploy.sweep.SweepType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class BenchmarkRun implements JacksonDeserializable, VariableMappable {

    /**
     * The type of sweep this run executes.
     */
    private SweepType sweepType;

    /**
     * The data-sets this run executes on.
     */
    private String dataSets;

    /**
     * The list of Strings representing the names of the
     * data-sets to run Graphalytics for.
     */
    private List<String> dataSetsList = new ArrayList<>();

    /**
     * The algorithms this run executes.
     */
    private String algorithms;

    /**
     * The list of Strings representing the names of the
     * algorithms to run Graphalytics with.
     */
    private List<String> algorithmsList = new ArrayList<>();

    /**
     * Tokenize a comma/space separated string into trimmed
     * tokens and return a list of these tokens.
     *
     * @param commaOrSpaceSeparatedString a string separated
     *                                    by either commas or
     *                                    spaces.
     * @return the list of tokens obtained by splitting the
     * input string.
     */
    private List<String> tokenize(String commaOrSpaceSeparatedString) {
        List<String> tokens = new ArrayList<>();

        Arrays.stream(commaOrSpaceSeparatedString.split(","))
                .map(String::trim)
                .map(t -> t.replaceAll("\\s+", ","))
                .forEach(t -> tokens.addAll(Arrays.asList(t.split(","))));

        return tokens;
    }

    @Override
    public void init() {
        assert sweepType != null;
        assert dataSets != null;
        assert algorithms != null;

        algorithmsList = tokenize(algorithms);
        dataSetsList = tokenize(dataSets);
    }

    @Override
    public Map<String, String> getVariableMap() {
        return new HashMap<String, String>() {
            {
                put("%sweep_type%", getSweepType().name());
                put("%data_sets%", String.join(",", dataSetsList));
                put("%algorithms%", String.join(",", algorithmsList));
                put("%data_sets_json%", "\\\"" + String.join("\\\", \\\"", dataSetsList) + "\\\"");
                put("%algorithms_json%", "\\\"" + String.join("\\\", \\\"", algorithms) + "\\\"");
            }
        };
    }

}
