package nl.tudelft.atlarge.script;

import java.io.IOException;

/**
 * Contains static methods to run commands in the runtime either
 * in a blocking or a non-blocking fashion.
 *
 * Created by Chris Lemaire on 5-6-2017.
 * @author Chris Lemaire
 */
public class RuntimeCommandRunner {

    /**
     * Runs a command through the shell in a non-blocking fashion.
     *
     * @param command to run in the shell.
     * @return Process given by the runtime environment.
     * @throws IOException when the shell command could not be executed.
     */
    public static Process runCommand(String command) throws IOException {
        try {
            return Runtime.getRuntime().exec(command);
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
    public static int runCommandBlockingWithExit(String command) throws IOException, InterruptedException {
        try {
            Process p = Runtime.getRuntime().exec(command);
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
     * @param command to run in the shell.
     * @return <code>true</code> when the command was executed successfully.
     */
    public static boolean runCommandBlocking(String command) {
        try {
            int exit = runCommandBlockingWithExit(command);
            if (exit != 0) {
                System.err.println("Shell command '" + command + "' exited with code " + exit);
                return false;
            }
        } catch (IOException|InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
