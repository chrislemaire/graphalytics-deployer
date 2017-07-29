package nl.tudelft.atlarge.live.runner;

import java.util.Stack;

/**
 * Created by Chris Lemaire on 21-7-2017.
 */
public class SshChainRunner extends CommandRunner {

    private Stack<String> aliases;

    public void chain(String alias) {
        aliases.push(alias);
    }

    public void push(String alias) {
        aliases.push(alias);
    }

    public String pop() {
        return aliases.pop();
    }

    public String peek() {
        return aliases.peek();
    }

    @Override
    protected CommandBuilder getCommandBuilder(String origCmd) {
        CommandBuilder builder = new CommandBuilder();
        for (String alias : aliases) {
            builder.sshTo(alias);
        }
        return builder;
    }



}
