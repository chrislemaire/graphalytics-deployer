package nl.tudelft.atlarge.writer;

import nl.tudelft.atlarge.writer.script.ShellScriptBuilder;

import java.io.IOException;

/**
 * Created by Chris Lemaire on 25-8-2017.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        ShellScriptBuilder helloWorldBuilder = new ShellScriptBuilder("hello_world");

        helloWorldBuilder.appendLine("echo This is before");
        helloWorldBuilder.startBuildingSshRemoteScript("bastion");
        helloWorldBuilder.appendLine("echo hello world");
        helloWorldBuilder.stopBuildingSshRemoteScript();
        helloWorldBuilder.appendLine("echo This is after");

        helloWorldBuilder.finish();
    }

}
