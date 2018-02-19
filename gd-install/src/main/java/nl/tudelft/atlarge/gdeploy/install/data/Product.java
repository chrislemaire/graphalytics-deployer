package nl.tudelft.atlarge.gdeploy.install.data;

import nl.tudelft.atlarge.gdeploy.core.VariableMappable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Product implements VariableMappable {

    public String downloadUrl = "";

    public String product = "";

    public String version = "";

    public boolean clean = false;

    @Override
    public Map<String, String> getVariableMap() {
        return new HashMap<String, String>() {
            {
                put("%product%", product);
                put("%download_url%", downloadUrl);
                put("%version%", version);
                put("%clean_install%", Objects.toString(clean));
            }
        };
    }

}
