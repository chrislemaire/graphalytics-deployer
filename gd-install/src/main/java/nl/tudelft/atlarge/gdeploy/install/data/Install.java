package nl.tudelft.atlarge.gdeploy.install.data;

import nl.tudelft.atlarge.gdeploy.core.VariableMappable;
import nl.tudelft.atlarge.gdeploy.core.script.RemoteSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data class representing the product object
 * that is to be installed by a specific data
 * script.
 */
public class Install implements VariableMappable {

    public String installDirectory = RemoteSystem.getNative().frameworks();

    public List<Product> productList;

    @Override
    public Map<String, String> getVariableMap() {
        return new HashMap<String, String>() {
            {
                put("%install_dir%", installDirectory);

                productList.forEach(product -> putAll(product.getVariableMap()));
            }
        };
    }

}
