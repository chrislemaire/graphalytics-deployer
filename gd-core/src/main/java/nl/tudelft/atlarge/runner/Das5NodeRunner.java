package nl.tudelft.atlarge.runner;

public class Das5NodeRunner extends HeadNodeRunner {

    /**
     * The ID of the node to run the command
     * on.
     */
    private String nodeId;
    
    /**
     * Creates a Runner object from the given
     * headnode server and the internally defined nodeId
     * for DAS5 servers.
     * 
     * @param sshId server ID.
     * @param nodeId internal node ID.
     */
	public Das5NodeRunner(String sshId, String nodeId) {
		super(sshId);
		
		this.nodeId = nodeId;
	}

    @Override
    protected CommandBuilder getCommandBuilder(String origCmd) {
        return super.getCommandBuilder(origCmd).sshTo(nodeId);
    }

}
