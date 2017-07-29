package nl.tudelft.atlarge.live.reserve;

import nl.tudelft.atlarge.live.runner.CommandRunner;
import nl.tudelft.atlarge.script.ClassResourceScript;

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
    private ClassResourceScript reqClassResourceScript;

    /**
     * The script that waits until a reservation is
     * granted.
     */
    private ClassResourceScript pollClassResourceScript;

    /**
     * The script that stores the reservation ticket
     * after the reservation is granted.
     */
    private ClassResourceScript finClassResourceScript;

    /**
     * Creates a new {@link Reserver} from script files.
     *
     * @param reqClassResourceScript ScriptType for executing requester.
     * @param pollClassResourceScript ScriptType for executing poller.
     * @param finClassResourceScript ScriptType for executing finisher.
     */
    public Reserver(CommandRunner runner, ClassResourceScript reqClassResourceScript, ClassResourceScript pollClassResourceScript, ClassResourceScript finClassResourceScript) {
    	assert runner != null;
        assert reqClassResourceScript != null;
        assert pollClassResourceScript != null;
        assert finClassResourceScript != null;

        this.runner = runner;
        this.reqClassResourceScript = reqClassResourceScript;
        this.pollClassResourceScript = pollClassResourceScript;
        this.finClassResourceScript = finClassResourceScript;
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
                reqClassResourceScript.getExecutionCommand())) {
            return false;
        }

        if (!runner.runCommandBlocking(
                pollClassResourceScript.getExecutionCommand())) {
            return false;
        }

        if (!runner.runCommandBlocking(
                finClassResourceScript.getExecutionCommand())) {
            return false;
        }

        return true;
    }

}
