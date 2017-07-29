package nl.tudelft.atlarge.live.reserve;

import nl.tudelft.atlarge.live.runner.CommandRunner;
import nl.tudelft.atlarge.script.Script;

/**
 * Reserver meant to reserve nodes in the cluster to
 * deploy the graph processing software platform on.
 *
 * Created by Chris Lemaire on 5-6-2017.
 */
public class Reserver {
	
	/**
	 * The runner that should run commands for this
	 * {@link Reserver}.
	 */
	private CommandRunner runner;

    /**
     * The script that requests a reservation to be
     * completed.
     */
    private Script reqScript;

    /**
     * The script that waits until a reservation is
     * granted.
     */
    private Script pollScript;

    /**
     * The script that stores the reservation ticket
     * after the reservation is granted.
     */
    private Script finScript;

    /**
     * Creates a new {@link Reserver} from script files.
     *
     * @param reqScript ScriptType for executing requester.
     * @param pollScript ScriptType for executing poller.
     * @param finScript ScriptType for executing finisher.
     */
    public Reserver(CommandRunner runner, Script reqScript, Script pollScript, Script finScript) {
    	assert runner != null;
        assert reqScript != null;
        assert pollScript != null;
        assert finScript != null;

        this.runner = runner;
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
        if (!runner.runCommandBlocking(
                reqScript.getExecutionCommand())) {
            return false;
        }

        if (!runner.runCommandBlocking(
                pollScript.getExecutionCommand())) {
            return false;
        }

        if (!runner.runCommandBlocking(
                finScript.getExecutionCommand())) {
            return false;
        }

        return true;
    }

}
