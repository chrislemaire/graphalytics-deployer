package nl.tudelft.atlarge.gdeploy.install;

import lombok.Data;
import nl.tudelft.atlarge.gdeploy.core.VariableMappable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
public class ProductData implements VariableMappable {

    private String installDirectory = "~/";

    private String downloadUrl = "";

    private String version = "";

    private boolean clean = false;

    @Override
    public Map<String, String> getVariableMap() {
        return new HashMap<String, String>() {
            {
                put("%install_dir%", installDirectory);
                put("%download_url%", downloadUrl);
                put("%version%", version);
                put("%clean_install%", Objects.toString(clean));
            }
        };
    }

}
