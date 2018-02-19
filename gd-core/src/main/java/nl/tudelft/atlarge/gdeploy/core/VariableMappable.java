package nl.tudelft.atlarge.gdeploy.core;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface VariableMappable {

    /**
     * Creates the variable mapping for this object.
     * A variable mapping maps certain strings to other
     * strings as a way to replace these during script
     * compile time.
     *
     * @return Map of key-strings mapping to their values.
     */
    Map<String, String> getVariableMap();

    /**
     * Maps a variable mapping to the same variable mapping
     * transforming the keys using the given transform function.
     *
     * @param transform   the prefix to add to each key.
     * @param variableMap the variable mapping to operate on.
     * @return the same variable mapping with transformed keys.
     */
    default Map<String, String> transformKeys(
            Function<String, String> transform, Map<String, String> variableMap) {
        return variableMap.entrySet().stream()
                .collect(Collectors.toMap(e -> transform.apply(e.getKey()), Map.Entry::getValue));
    }

}
