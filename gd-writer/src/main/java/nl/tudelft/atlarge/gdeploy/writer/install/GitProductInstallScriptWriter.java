package nl.tudelft.atlarge.gdeploy.writer.install;

import nl.tudelft.atlarge.gdeploy.writer.script.ShellScriptBuilder;

/**
 * Created by Chris Lemaire on 5-9-2017.
 */
public class GitProductInstallScriptWriter extends InstallScriptWriter {

    public GitProductInstallScriptWriter(ShellScriptBuilder builder, String product) {
        super(builder, product);
    }

    // Example link:
    // https://github.com/chrislemaire/graphalytics-platforms-powergraph/commit/f434222c281ae1d8946e6de16485e020950a65c6
    // Relevant part: f434222c281ae1d8946e6de16485e020950a65c6
    // This is the hash of the commit.

    // Valid example of a `link` field entry: https://github.com/chrislemaire/graphalytics-platforms-powergraph
    // Valid example of a `version` variable entry: f434222c281ae1d8946e6de16485e020950a65c6
    //                                          or  f434222

    @Override
    public ShellScriptBuilder write(String version) {
        builder.appendLine("# START OF GIT PRODUCT INSTALL SCRIPT #");
        writeInitializer(version)

                .appendLine("git clone " + product)
                .appendLine("git checkout " + version)

                .appendLine("INSTALL_DIR=$(basename (find ./ -type d | head -n 1))")
                .appendLine("mv -rf ./$INSTALL_DIR ../../" + version);

        return builder;
    }
}
