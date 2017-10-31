package nl.tudelft.atlarge.gdeploy.deploy.writers;

import nl.tudelft.atlarge.gdeploy.core.script.ShellScriptBuilder;
import nl.tudelft.atlarge.gdeploy.core.writer.ScriptCopyWriter;
import nl.tudelft.atlarge.gdeploy.deploy.benchmark.Benchmark;

import java.util.Map;

/**
 * Implementation of the ScriptCopyWriter that adds
 * a Benchmark as data. The Benchmark has parameters
 * that will be replaced in the copied script by this
 * class.
 */
public class BenchmarkCopyWriter extends ScriptCopyWriter {

    /**
     * The benchmark of which the parameters will be
     * set in the specific replacements map.
     */
    private Benchmark benchmark;

    /**
     * Constructs a new BenchmarkCopyWriter using
     * its super constructor. Additionally the benchmark
     * is passed as an argument.
     *
     * @param builder   the builder to build the script with.
     * @param benchmark benchmark to write parameters of
     *                  by default.
     */
    public BenchmarkCopyWriter(ShellScriptBuilder builder, Benchmark benchmark) {
        super(builder);

        this.benchmark = benchmark;
    }

    @Override
    protected void specificReplacements(Map<String, String> map) {
        map.putAll(benchmark.getVariableMap());
    }

}
