package nl.tudelft.atlarge.gdeploy.live;

import nl.tudelft.atlarge.gdeploy.live.runner.CommandRunner;
import nl.tudelft.atlarge.gdeploy.live.runner.NativeLinuxRunner;

/**
 * Created by Chris Lemaire on 29-7-2017.
 */
public class Global {

    public static final CommandRunner NATIVE_RUNNER = new NativeLinuxRunner();

}
