package nl.tudelft.atlarge.runner;

public class HeadNodeRunner extends CommandRunner {

	/**
	 * The ssh ID or alias of the server at
	 * which the given command should be executed
	 * on the head node.
	 */
	private String sshId;
	
	/**
	 * Creates a {@link CommandRunner} from the
	 * ssh ID or alias which one needs to connect
	 * to in order to reach the head node.
	 * 
	 * @param sshId of the head node server.
	 */
	public HeadNodeRunner(String sshId) {
		super();
		
		assert sshId != null;
		
		this.sshId = sshId;
	}
	
	@Override
	protected CommandBuilder getCommandBuilder(String origCmd) {
        CommandBuilder builder = new CommandBuilder();
        builder.sshTo(sshId);
        return builder.setCmd(origCmd);
	}

}
