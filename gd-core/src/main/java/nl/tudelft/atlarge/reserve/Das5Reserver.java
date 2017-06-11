package nl.tudelft.atlarge.reserve;

import nl.tudelft.atlarge.runner.HeadNodeRunner;
import nl.tudelft.atlarge.script.Script;
import nl.tudelft.atlarge.script.ScriptOptionList;
import nl.tudelft.atlarge.script.ScriptTypeEnum;

import java.io.File;

/**
 * Programmatic bind between script files for reserving
 * nodes on the DAS5 cluster and the Reserver java class.
 *
 * Created by Chris Lemaire on 5-6-2017.
 */
public class Das5Reserver extends Reserver {

    private static final Script REQ_SCRIPT = new Script(ScriptTypeEnum.SHELL, new ScriptOptionList(),
            Das5Reserver.class.getResource("/scripts/das5-preserve/request.sh"), new File("/das5-req.sh"));

    private static final Script POLL_SCRIPT = new Script(ScriptTypeEnum.SHELL, new ScriptOptionList(),
            Das5Reserver.class.getResource("/scripts/das5-preserve/request.sh"), new File("/das5-req.sh"));

    private static final Script FIN_SCRIPT = new Script(ScriptTypeEnum.SHELL, new ScriptOptionList(),
            Das5Reserver.class.getResource("/scripts/das5-preserve/request.sh"), new File("/das5-req.sh"));

    /**
     * Creates a new {@link Das5Reserver} from predetermined {@link Script}s.
     */
    private Das5Reserver(HeadNodeRunner runner) {
        super(runner, REQ_SCRIPT, POLL_SCRIPT, FIN_SCRIPT);
    }

}
