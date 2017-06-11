package nl.tudelft.atlarge.runner;

/**
 * Builder for command Strings. May be used
 * to add chains of ssh commands.
 * 
 * @author Chris Lemaire
 */
public class CommandBuilder {

	/**
	 * Builder for the full command String.
	 */
	private StringBuilder builder;
	
	/**
	 * Number of ssh commands currently performed
	 * in a chain.
	 */
	private int noSshs;
	
	/**
	 * The current command that will be appended
	 * to the command String upon flushing.
	 */
	private String currCmd;
	
	/**
	 * Creates a new CommandBuilder and initializes
	 * the fields that need initializing.
	 */
	public CommandBuilder() {
		builder = new StringBuilder();
		noSshs = 0;
		currCmd = null;
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
     * Flushes the current command and ssh keys
     * together to create a single part of a command.
     * 
     * @return {@link CommandBuilder} to continue building.
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

		return builder.toString();
	}
	
}
