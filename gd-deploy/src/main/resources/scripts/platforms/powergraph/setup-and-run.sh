# Set the PowerGraph home directory
POWERGRAPH_HOME=/home/clemaire/graphmat/graphalytics-0.9.0-SNAPSHOT-graphmat-0.2-SNAPSHOT

# Copy over the settings for the platform if available
if [[ -v ${IPS} ]]; then
    sed -i "s/.*\(platform\.powergraph\.nodes\s*=\).*/\1 ${IPS}/" config/platform.properties
fi

if [[ -v ${NO_THREADS} ]]; then
    sed -i "s/.*\(platform\.powergraph\.num-threads\s*=\).*/\1 ${NO_THREADS}/" config/platform.properties
elif [[ -v ${NO_CORES} ]]; then
    sed -i "s/.*\(platform\.powergraph\.num-threads\s*=\).*/\1 ${NO_CORES}/" config/platform.properties
else
    sed -i "s/.*\(platform\.powergraph\.num-threads\s*=\).*/\1 1/" config/platform.properties
fi

# Start the benchmark
ssh ${IPS[0]} "${POWERGRAPH_HOME}/bin/sh/run-benchmark.sh"

rm config/benchmarks/${BENCHMARK_FILE}


