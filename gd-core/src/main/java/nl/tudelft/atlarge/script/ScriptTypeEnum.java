package nl.tudelft.atlarge.script;

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
        public String generateExecCommand(ClassResourceScript classResourceScript) {
            return "./" + classResourceScript.getFile().getName();
        }
    });

    /**
     * {@link nl.tudelft.atlarge.script.ScriptType.CmdGenerator} for generating
     * command Strings for the specific script type.
     */
    private CmdGenerator cmdGenerator;

    /**
     * Creates a new enumeration item by its CmdGenerator.
     *
     * @param cmdGenerator for generating commands from and for Scripts.
     */
    ScriptTypeEnum(CmdGenerator cmdGenerator) {
        this.cmdGenerator = cmdGenerator;
    }

    @Override
    public String genCmd(ClassResourceScript classResourceScript) {
        return cmdGenerator.generateExecCommand(classResourceScript);
    }
}
