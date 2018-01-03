package nl.tudelft.atlarge.gdeploy.deploy.platform;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

import java.lang.reflect.Constructor;

public enum SoftwarePlatforms {

    NONE(PlatformRunWriter.class),
    POWERGRAPH(PowergraphRunWriter.class),
    GRAPHMAT(GraphmatRunWriter.class);

    Class<? extends PlatformRunWriter> writer;

    SoftwarePlatforms(Class<? extends PlatformRunWriter> writer) {
        this.writer = writer;
    }

    public PlatformRunWriter newInstance(ShellScriptBuilder builder, Benchmark benchmark) {
        try {
            Constructor<? extends PlatformRunWriter> cons = writer.getDeclaredConstructor(
                    ShellScriptBuilder.class, Benchmark.class);
            return cons.newInstance(builder, benchmark);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
