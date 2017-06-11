package nl.tudelft.atlarge.config;

import java.io.*;
import java.net.URL;

/**
 * Abstract config class with super constructor to
 * let {@link Configurable}s be initialized by their related
 * File.
 *
 * @author Chris Lemaire
 */
public abstract class AbstractConfig implements Configurable {

    /**
     * File the configurations relate to.
     */
    protected URL file;

    /**
     * Super constructor for config classes to initialize
     * {@link Configurable} using the file it relates to.
     *
     * @param file the configurations relate to.
     */
    AbstractConfig(String file) {
    	assert file != null;
    	
        this.file = getClass().getResource(file);
    }
    
    @Override
    public void read() throws IOException {
        try (InputStream inputStream = file.openStream()) {
            readImpl();
        } catch (FileNotFoundException e) {
            System.err.println("Given config file '" + file.getPath() + "' does not exist.");
            throw e;
        } catch (IOException e) {
            System.err.println("Something went wrong while reading properties file '" + file.getPath() + "'");
            throw e;
        }
    }
    
    @Override
    public void writeBack() throws IOException {
        write(new File(file.getFile()));
    }
    
    @Override
    public void write(File file) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            writeImpl(file);
        } catch (FileNotFoundException e) {
            System.err.println("Given config file '" + file.getPath() + "' does not exist.");
            throw e;
        } catch (IOException e) {
            System.err.println("Something went wrong while writing properties file '" + file.getPath() + "'");
            throw e;
        }
    }
    
    /**
     * Abstract method that should be implemented and should
     * immediately throw exceptions by method signature if found.
     * 
     * It should read configurations from the file field
     * into the internal representation for configurations.
     * 
     * @throws IOException when something went wrong during the
     *          reading from file.
     */
    protected abstract void readImpl() throws IOException;
    
    /**
     * Abstract method that should be implemented and should
     * immediately throw exceptions by method signature if found.
     * 
     * It should write internal representations of the configurations
     * back to the file specified.
     * 
     * @param file to write configuration representation to.
     * @throws IOException when something went wrong during the
     *          writing to file.
     */
    protected abstract void writeImpl(File file) throws IOException;

}
