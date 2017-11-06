# Go into the graphalytics platform home directory
cd ${PLATFORM_HOME}

# Make benchmark properties file
RAND=$$
BENCHMARK_FILE=gd-deploy-benchmark-${RAND}.properties

# Remove old benchmark files
rm ./config/benchmarks/gd-deploy-benchmark-*.properties

# Copy the benchmark file from the custom benchmark
cp ./config/benchmarks/custom.properties ./config/benchmarks/${BENCHMARK_FILE}

# Write benchmark configurations
sed -i "s/.*\(benchmark\.type\s*=\).*/\1 custom/" config/benchmarks/${BENCHMARK_FILE}
sed -i "s/.*\(benchmark\.name\s*=\).*/\1 ${SWEEP_PREFIX} sweep for GraphMat on ${HOST} with ${NODE_TYPE} nodes/" config/benchmarks/${BENCHMARK_FILE}
sed -i "s/.*\(benchmark\.custom\.graphs\s*=\).*/\1 ${DATA_SETS}/" config/benchmarks/${BENCHMARK_FILE}
sed -i "s/.*\(benchmark\.custom\.algorithms\s*=\).*/\1 ${ALGORITHMS}/" config/benchmarks/${BENCHMARK_FILE}

# Write include statement to benchmark.properties
sed -i "s/.*include = benchmarks\/.*//" config/benchmark.properties
sed -i "/^$/d" config/benchmark.properties
sed -i "1s/^/include = benchmarks\/${BENCHMARK_FILE}\n/" config/benchmark.properties


