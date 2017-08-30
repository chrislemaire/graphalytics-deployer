package nl.tudelft.atlarge.writer.script;

import nl.tudelft.atlarge.writer.Global;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Chris Lemaire on 24-8-2017.
 */
public class ShellScript {

    private final String name;

    private final String path;

    private final String fullPath;

    private final RemoteSystem remote;

    private final List<RemoteSystem> relayRemotes;

    ShellScript(String name, String path, RemoteSystem remote, LinkedList<RemoteSystem> relayRemotes) {
        this.name = name;
        this.path = path;
        this.fullPath = '~' + Global.SCRIPT_DIRECTORY + path + '/' + name;
        this.remote = remote;

        this.relayRemotes = new ArrayList<RemoteSystem>(relayRemotes);
    }

    /**
     * Creates the command that executes this Script from
     * the given host server.
     *
     * @param hostRemote from which to call this Script.
     * @return command calling this Script from the host
     *          remote.
     */
    String createRemoteCallCommand(String hostRemote) {
        if (!relayRemotes.contains(hostRemote)) {
            throw new IllegalArgumentException("No known ssh relay path from '" + hostRemote
                    + "' to '" + remote);
        }

        if (hostRemote.equals(remote)) {
            return fullPath;
        }

        StringBuilder commandBuilder = new StringBuilder();
        for (int i = relayRemotes.lastIndexOf(hostRemote) + 1; i < relayRemotes.size(); i++) {
            commandBuilder.append("ssh ").append(relayRemotes.get(i)).append(' ');
        }
        commandBuilder.append('\'').append(fullPath).append('\'');

        return commandBuilder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof ShellScript)) {
            return false;
        }

        ShellScript that = (ShellScript) other;

        return name.equals(that.name)
                && fullPath.equals(that.fullPath);
    }

}
