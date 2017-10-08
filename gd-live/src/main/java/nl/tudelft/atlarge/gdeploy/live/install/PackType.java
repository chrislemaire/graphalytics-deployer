package nl.tudelft.atlarge.gdeploy.live.install;

/**
 * Describes a set of packed file types.
 * The preferred file packing is .tar.gz,
 * however, if another is introduced, one
 * might add this to this enumeration.
 *
 * @author Chris Lemaire
 */
public enum PackType {

    TAR_GZ(".tar.gz", "tar -xvzf ", " -C ");

    /**
     * The extension of the {@link PackType}.
     */
    private String extension;

    /**
     * The prefix the {@link PackType} unpack command has.
     */
    private String prefix;

    /**
     * Option to use to specify a target directory.
     */
    private String dirOption;

    /**
     * Creates a new PackType from its fields.
     *
     * @param extension postfix of file of type.
     * @param prefix of command to unpack file.
     * @param dirOption to use to unpack to.
     */
    PackType(String extension, String prefix, String dirOption) {
        this.extension = extension;
        this.prefix = prefix;
        this.dirOption = dirOption;
    }

    /**
     * Classifies the name of a file as a certain
     * {@link PackType} or as null if no match is
     * found.
     *
     * @param fileName to match to an extension.
     * @return {@link PackType} of file with name fileName.
     */
    public static PackType classify(String fileName) {
        for (PackType type : values()) {
            if (fileName.endsWith(type.extension)) {
                return type;
            }
        }
        return null;
    }

    /**
     * Returns the compiled unpacking command for a
     * file of this type.
     *
     * @param fileName to unpack.
     * @return String command to execute to unpack file (locally).
     */
    public String getUnpackCommand(String fileName) {
        // Add exception rules for certain types here.

        return prefix + fileName;
    }

    /**
     * Returns the compiled unpacking command for a
     * file of this type.
     *
     * @param fileName to unpack.
     * @param targetDir to unpack to.
     * @return String command to execute to unpack file (locally).
     */
    public String getUnpackCommand(String fileName, String targetDir) {
        // Add exception rules for certain types here.

        return prefix + fileName + dirOption + targetDir;
    }

}
