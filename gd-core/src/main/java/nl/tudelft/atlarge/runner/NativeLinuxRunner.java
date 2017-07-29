package nl.tudelft.atlarge.runner;

/**
 * Runner for a native linux system.
 *
 * @author Chris Lemaire
 */
public class NativeLinuxRunner extends CommandRunner {

    @Override
    protected CommandBuilder getCommandBuilder(String origCmd) {
        return new CommandBuilder().setNativeShellExecutor(CommandBuilder.ShellExecutor.SH).setCmd(origCmd);
    }

}
