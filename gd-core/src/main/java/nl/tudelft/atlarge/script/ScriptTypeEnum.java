package nl.tudelft.atlarge.script;

import java.io.File;

/**
 * Enumeration of default script types and also implementation
 * of the ScriptType interface.
 *
 * Created by Chris Lemaire on 5-6-2017.
 * @author Chris Lemaire
 */
public enum ScriptTypeEnum implements ScriptType {

    SHELL(new CmdGenerator() {
        @Override
        public String generateExecCommand(File script) {
            return "./" + script.getName();
        }
    });

    private CmdGenerator cmdGenerator;

    ScriptTypeEnum(CmdGenerator cmdGenerator) {
        this.cmdGenerator = cmdGenerator;
    }

    @Override
    public String genCmd(File script) {
        return cmdGenerator.generateExecCommand(script);
    }
}
