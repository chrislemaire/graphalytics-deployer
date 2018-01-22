package nl.tudelft.atlarge.gdeploy.deploy.benchmark.data;

import nl.tudelft.atlarge.gdeploy.core.VariableMappable;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.JacksonDeserializable;
import nl.tudelft.atlarge.gdeploy.deploy.sweep.SweepType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenchmarkRun implements JacksonDeserializable, VariableMappable {

    /**
     * The type of sweep this run executes.
     */
    public SweepType sweepType;

    /**
     * The parameters that may be used for the sweep to
     * setup.
     */
    public Map<String, String> sweepParameters = new HashMap<>();

    /**
     * The data-sets this run executes on.
     */
    public String dataSets;

    /**
     * The list of Strings representing the names of the
     * data-sets to run Graphalytics for.
     */
    public List<String> dataSetsList = new ArrayList<>();

    /**
     * The algorithms this run executes.
     */
    public String algorithms;

    /**
     * The list of Strings representing the names of the
     * algorithms to run Graphalytics with.
     */
    public List<String> algorithmsList = new ArrayList<>();

    /**
     * The number of repetitions for each of the runs in
     * the benchmark.
     */
    public int repetitions = 1;

    /**
     * The KMP_AFFINITY setting with which mpi will be used.
     */
    public String affinity = "scatter";

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
        assert sweepParameters != null;

        algorithmsList = tokenize(algorithms);
        dataSetsList = tokenize(dataSets);
    }

    @Override
    public Map<String, String> getVariableMap() {
        return new HashMap<String, String>() {
            {
                put("%sweep_type%", sweepType.name());
                put("%data_sets%", String.join(",", dataSetsList));
                put("%algorithms%", String.join(",", algorithmsList));
                put("%data_sets_json%", "\\\"" + String.join("\\\", \\\"", dataSetsList) + "\\\"");
                put("%algorithms_json%", "\\\"" + String.join("\\\", \\\"", algorithmsList) + "\\\"");
                put("%repetitions%", String.valueOf(repetitions));
                put("%affinity%", affinity);
                putAll(transformKeys(k -> "%sweep_param_" + k + "%", sweepParameters));
            }
        };
    }

}
