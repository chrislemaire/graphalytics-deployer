package nl.tudelft.atlarge.gdeploy.live.runner;

/**
 * Builder for command Strings. May be used
 * to add chains of ssh commands.
 * 
 * @author Chris Lemaire
 */
public class CommandBuilder {

	enum ShellExecutor {
		SH("sh", "-c");

		private String exec, options;

		ShellExecutor(String exec, String options) {
			this.exec = exec;
			this.options = options;
		}
	}

	/**
	 * Builder for the full command String.
	 */
	private StringBuilder builder;
	
	/**
	 * Number of ssh commands currently performed
	 * in a chain.
	 */
	private int noSshs = 0;
	
	/**
	 * The current command that will be appended
	 * to the command String upon flushing.
	 */
	private String currCmd = null;

	/**
	 * The executor that handles the command at
	 * the native system.
	 */
	private ShellExecutor nativeExec = ShellExecutor.SH;

	/**
	 * The file the stdout output should be written
	 * to.
	 */
	private String outputFile = null;
	
	/**
	 * Creates a new CommandBuilder and initializes
	 * the fields that need initializing.
	 */
	public CommandBuilder() {
		builder = new StringBuilder();
	}
	
	/**
	 * Sshs to a given address.
	 * 
	 * @param address to ssh to.
	 * @return {@link CommandBuilder} to continue building.
	 */
	public CommandBuilder sshTo(String address) {
		builder.append("ssh ").append(address).append("\"");
		noSshs++;
		
		return this;
	}

	public CommandBuilder setNativeShellExecutor(ShellExecutor exec) {
	    this.nativeExec = exec;

	    return this;
    }
	
	/**
	 * Sets the command for the current builder.
	 * 
	 * @param command to be set.
	 * @return {@link CommandBuilder} to continue building.
	 */
	public CommandBuilder setCmd(String command) {
		currCmd = command;
		
		return this;
	}

	/**
	 * Sets the output file for stdout.
	 *
	 * @param filename to be set.
	 * @return {@link CommandBuilder} to continue building.
	 */
	public CommandBuilder outputToFile(String filename) {
		outputFile = filename;

		return this;
	}
    
    /**
     * Flushes the current command and ssh keys
     * together to create a single part of a command.
     */
    private void flush() {
        if (currCmd != null) {
            builder.append(currCmd);
        }
        
        for (int i = 0; i < noSshs; i++) {
            builder.append('"');
        }
        
        noSshs = 0;
        currCmd = null;
    }
	
	/**
	 * Builds the command by appending the final
	 * annotation marks.
	 * 
	 * @return built command String.
	 */
	public String buildCommand() {
		flush();

		return nativeExec.exec + " " + nativeExec.options + " " + builder.toString();
	}

	/**
	 * Builds the command by flushing and thereafter
	 * prepends the shell executor to execute the command.
	 *
	 * @return String array with shell executor and the command.
	 */
	public String[] buildCommandTokens() {
		flush();

		return new String[]{nativeExec.exec, nativeExec.options, builder.toString()};
	}
	
}
