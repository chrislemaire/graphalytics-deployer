package nl.tudelft.atlarge.gdeploy.live.reserve;

import nl.tudelft.atlarge.gdeploy.live.runner.HeadNodeRunner;
import nl.tudelft.atlarge.gdeploy.core.script.ClassResourceScript;
import nl.tudelft.atlarge.gdeploy.core.script.ScriptOptionList;
import nl.tudelft.atlarge.gdeploy.core.script.ScriptTypeEnum;

import java.io.File;

/**
 * Programmatic bind between script files for reserving
 * nodes on the DAS5 cluster and the Reserver java class.
 *
 * Created by Chris Lemaire on 5-6-2017.
 */
public class Das5Reserver extends Reserver {

    private static final ClassResourceScript REQ_CLASS_RESOURCE_SCRIPT = new ClassResourceScript(ScriptTypeEnum.SHELL, new ScriptOptionList(),
            Das5Reserver.class.getResource("/scripts/das5-preserve/request.sh"), new File("/das5-req.sh"));

    private static final ClassResourceScript POLL_CLASS_RESOURCE_SCRIPT = new ClassResourceScript(ScriptTypeEnum.SHELL, new ScriptOptionList(),
            Das5Reserver.class.getResource("/scripts/das5-preserve/request.sh"), new File("/das5-req.sh"));

    private static final ClassResourceScript FIN_CLASS_RESOURCE_SCRIPT = new ClassResourceScript(ScriptTypeEnum.SHELL, new ScriptOptionList(),
            Das5Reserver.class.getResource("/scripts/das5-preserve/request.sh"), new File("/das5-req.sh"));

    /**
     * Creates a new {@link Das5Reserver} from predetermined {@link ClassResourceScript}s.
     */
    private Das5Reserver(HeadNodeRunner runner) {
        super(runner, REQ_CLASS_RESOURCE_SCRIPT, POLL_CLASS_RESOURCE_SCRIPT, FIN_CLASS_RESOURCE_SCRIPT);
    }

}
