package nl.tudelft.atlarge.reserve;

import nl.tudelft.atlarge.script.RuntimeCommandRunner;
import nl.tudelft.atlarge.script.Script;

/**
 * Reserver meant to reserve nodes in the cluster to
 * deploy the graph processing software platform on.
 *
 * Created by Chris Lemaire on 5-6-2017.
 */
public class Reserver {

    /**
     * The script that requests a reservation to be
     * completed.
     */
    protected Script reqScript;

    /**
     * The script that waits until a reservation is
     * granted.
     */
    protected Script pollScript;

    /**
     * The script that stores the reservation ticket
     * after the reservation is granted.
     */
    protected Script finScript;

    /**
     * Creates a new {@link Reserver} from script files.
     *
     * @param reqScript ScriptType for executing requester.
     * @param pollScript ScriptType for executing poller.
     * @param finScript ScriptType for executing finisher.
     */
    public Reserver(Script reqScript, Script pollScript, Script finScript) {
        assert reqScript != null;
        assert pollScript != null;
        assert finScript != null;

        this.reqScript = reqScript;
        this.pollScript = pollScript;
        this.finScript = finScript;
    }

    /**
     * Reserves the nodes needed for operation.
     * This method should be implemented by specific Reservers.
     * This is to let the programmer determine platform specific
     * checks or options and pass them on to the scripts.
     *
     * @return <code>true</code> when reserving was successful.
     */
    public boolean reserve() {
        if (!RuntimeCommandRunner.runCommandBlocking(
                reqScript.getExecutionCommand())) {
            return false;
        }

        if (!RuntimeCommandRunner.runCommandBlocking(
                pollScript.getExecutionCommand())) {
            return false;
        }

        if (!RuntimeCommandRunner.runCommandBlocking(
                finScript.getExecutionCommand())) {
            return false;
        }

        return true;
    }

}
