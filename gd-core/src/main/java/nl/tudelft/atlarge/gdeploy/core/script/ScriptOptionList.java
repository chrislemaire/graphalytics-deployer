package nl.tudelft.atlarge.gdeploy.core.script;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * An extension of the {@link ArrayList} class.
 * Adds a utility methods to simplify option list
 * compilation.
 *
 * Created by Chris Lemaire on 5-6-2017.
 * @author Chris Lemaire
 */
public class ScriptOptionList extends ArrayList<String> {

    /**
     * Creates an empty ScriptOptionList.
     */
    public ScriptOptionList() {
        super();
    }

    /**
     * Creates a ScriptOptionList from a given array of String options.
     *
     * @param options array representing options.
     */
    public ScriptOptionList(String[] options) {
        super(Arrays.asList(options));
    }

    /**
     * Writes the list of options as a String with given separator.
     *
     * @param separator between entries of the option list.
     * @return String representing this ScriptOptionList separated
     *          by separator Strings.
     */
    public String writeWithSeparator(String separator) {
        StringBuilder builder = new StringBuilder();

        String prefix = "";
        for (String option : this) {
            builder.append(prefix).append(option);
            prefix = separator;
        }

        return builder.toString();
    }

}
