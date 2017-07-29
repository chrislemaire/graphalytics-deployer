package nl.tudelft.atlarge.live.runner;

import java.io.IOException;
import java.util.Arrays;

/**
 * Contains static methods to run commands in the runtime either
 * in a blocking or a non-blocking fashion.
 *
 * Created by Chris Lemaire on 5-6-2017.
 * @author Chris Lemaire
 */
public abstract class CommandRunner {
	
	/**
	 * Returns the pre-formatted command that executes on
	 * the native system to execute the given command on
	 * the target system.
	 * 
	 * @param origCmd to format into a command running on
	 * 			the target system.
	 * @return String command that should run origCmd on
	 * 			the target system.
	 */
	private String getCommand(String origCmd) {
	    return getCommandBuilder(origCmd).buildCommand();
	}
	
	/**
	 * Returns the format builder for the command that exe-
	 * cutes on the native system to execute the given command
	 * on the target system.
	 * 
	 * @param origCmd to format in the {@link CommandBuilder}.
	 * @return {@link CommandBuilder} that should build the
	 *          command into the target command.
	 */
	protected abstract CommandBuilder getCommandBuilder(String origCmd);

    /**
     * Runs a command through the shell in a non-blocking fashion.
     *
     * @param origCmd to run in the shell.
     * @return Process given by the runtime environment.
     * @throws IOException when the shell command could not be executed.
     */
    public Process runCommand(String origCmd) throws IOException {
        CommandBuilder command = getCommandBuilder(origCmd);
        try {
            return Runtime.getRuntime().exec(command.buildCommandTokens());
        } catch (IOException e) {
            System.err.println("Shell command '" + command + "' could not be executed.");
            throw e;
        }
    }

    /**
     * Runs a command through the shell in a blocking fashion
     * and returns the exit code given by the command.
     *
     * @param command to run in the shell.
     * @return exit code for command.
     * @throws IOException when the shell command could not be executed.
     * @throws InterruptedException when command execution was interrupted.
     */
    private int runCommandBlockingWithExit(CommandBuilder command) throws IOException, InterruptedException {
        try {
            System.out.println("Running: '" + Arrays.toString(command.buildCommandTokens()) + "'");
            Process p = Runtime.getRuntime().exec(command.buildCommandTokens());
            return p.waitFor();
        } catch (IOException e) {
            System.err.println("Shell command '" + command + "' could not be executed.");
            throw e;
        } catch (InterruptedException e) {
            System.err.println("Shell command '" + command + "' was interrupted.");
            throw e;
        }
    	
    }

    /**
     * Runs a command through the shell in a blocking fashion
     * and returns whether the command was completed successfully.
     * Meaning that the exit code was 0, there were no external
     * interruptions and the command could be executed.
     *
     * @param origCmd to run in the shell.
     * @return <code>true</code> when the command was executed successfully.
     */
    public boolean runCommandBlocking(String origCmd) {
    	CommandBuilder command = getCommandBuilder(origCmd);
        try {
            int exit = runCommandBlockingWithExit(command);
            if (exit != 0) {
                System.err.println("Shell command '" + command + "' exited with code " + exit);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
