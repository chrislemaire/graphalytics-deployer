package nl.tudelft.atlarge.gdeploy.install.data;

import lombok.Data;
import nl.tudelft.atlarge.gdeploy.core.VariableMappable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
public class Product implements VariableMappable {

    private String downloadUrl = "";

    private String product = "";

    private String version = "";

    private boolean clean = false;

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
