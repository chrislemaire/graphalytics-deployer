package nl.tudelft.atlarge.gdeploy.core.script;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Contains data and behaviour necessary to run Scripts outside
 * the JAR environment.
 *
 * Created by Chris Lemaire on 5-6-2017.
 * @author Chris Lemaire
 */
public class ClassResourceScript {

    /**
     * The {@link ScriptType} for this ClassResourceScript.
     */
    private ScriptType type;

    /**
     * The {@link ScriptOptionList} used for this ClassResourceScript.
     */
    private ScriptOptionList options;

    /**
     * The execution command for executing the unpacked
     * command from within {@link #unpackFile}.
     */
    private String executionCommand;

    /**
     * The internal url for the script.
     */
    private URL internalUrl;

    /**
     * The file into which the script will be unpacked.
     */
    private File unpackFile;

    /**
     * Whether this script has been exported to the appropriate
     * directory.
     */
    private boolean isExported;

    /**
     * Creates a new ClassResourceScript from a script type, the internal url
     * and the unpacking directory. Also immediately generates
     * the script execution command.
     *
     * @param type of the ClassResourceScript.
     * @param internalUrl location of script in classpath.
     * @param unpackFile file to unpack to.
     */
    public ClassResourceScript(ScriptType type, ScriptOptionList options, URL internalUrl, File unpackFile) {
        this.type = type;
        this.internalUrl = internalUrl;
        this.unpackFile = unpackFile;
        this.isExported = false;

        this.executionCommand = type.genCmd(this);
    }

    /**
     * Exports this ClassResourceScript from its {@link #internalUrl} to the
     * given {@link #unpackFile} file.
     *
     * @throws IOException when something went wrong during exporting.
     */
    public void exportScript() throws IOException {
        if (isExported) {
            return;
        }

        try {
            FileUtils.copyURLToFile(internalUrl, unpackFile);
        } catch (IOException e) {
            System.err.println("Couldn't export ClassResourceScript '" + this + "'" +
                    "\n\tFrom:\t'" + internalUrl.getPath() + "'" +
                    "\n\tTo:\t'" + unpackFile.getPath() + "'");
            throw e;
        }

        isExported = true;
    }

    /**
     * Gets the execution command String.
     *
     * @return execution command String ready for execution.
     */
    public String getExecutionCommand() {
        return executionCommand;
    }

    /**
     * Gets the {@link #unpackFile} field.

     * @return file pointing to the location this ClassResourceScript should unpack to.
     */
    public File getFile() {
        return unpackFile;
    }
}