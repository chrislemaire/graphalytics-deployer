package nl.tudelft.atlarge.writer.packages;

import nl.tudelft.atlarge.writer.script.ShellScriptBuilder;

public enum ProductPackage {

    TAR_GZ(".tar.gz", "tar -xzvf ./$TEMP_FILE");
    
    private static final String TEMP_FILE = "TMP_INSTALL_FILE";

    private static final String TEMP_DIR = "./tmp_install";
    
    
    private String extension;
    
    private String unpackCommand;
    
    private String downloadCommand = "wget $link";
    
    ProductPackage(String extension, String unpackCommand, String downloadCommand) {
        this(extension, unpackCommand);
        
        this.downloadCommand = downloadCommand;
    }

    ProductPackage(String extension, String unpackCommand) {
        this.extension = extension;
        this.unpackCommand = unpackCommand.replaceAll("%$TEMP_FILE", TEMP_FILE);
    }

    public ShellScriptBuilder downloadAndUnpack(ShellScriptBuilder builder, String version, String link) {
        builder.appendLine("mkdir ./" + TEMP_DIR)
                .appendLine("cd ./" + TEMP_DIR)
                .appendLineWithOutput(downloadCommand.replaceAll("%$link", link))
                .appendLine(TEMP_FILE + "=$(basename (find ./ -type f | head -n 1))")
                .appendLineWithOutput(unpackCommand)
                .appendLine("rm -f ./$" + TEMP_FILE)
                .appendLine("INSTALL_DIR=$(basename (find ./ -type d | head -n 1))")
                .appendLine("mv -rf ./$INSTALL_DIR ../../" + version)
                .appendLine("cd ..")
                .appendLine("rm -rf ./" + TEMP_DIR);
        
        return builder;
    }
    
}
