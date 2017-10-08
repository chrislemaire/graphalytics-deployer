package nl.tudelft.atlarge.gdeploy.core.script;

import java.io.File;

/**
 * Type of ClassResourceScript generalized by an interface with a single method
 * returning the command needed to execute the type of script.
 *
 * This should be implemented by enumerations to extend the list
 * of ScriptTypes.
 *
 * Created by Chris Lemaire on 5-6-2017.
 * @author Chris Lemaire
 */
public interface ScriptType {

    /**
     * Class that is to be implemented by each ScriptType
     * within an enumeration of such classes.
     * This class should allow use of lambda expressions more easily.
     */
    abstract class CmdGenerator {

        /**
         * Generates the execution command given the classResourceScript {@link File}.
         *
         * @param classResourceScript of the classResourceScript to execute.
         * @return command String.
         */
        public abstract String generateExecCommand(ClassResourceScript classResourceScript);

    }

    /**
     * Generates the execution command given the classResourceScript {@link File}.
     *
     * @param classResourceScript to execute.
     * @return command String.
     */
    String genCmd(ClassResourceScript classResourceScript);


}
