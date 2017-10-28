# Set the GraphMat home directory
GRAPHMAT_HOME=/home/clemaire/graphmat/graphalytics-0.9.0-SNAPSHOT-graphmat-0.2-SNAPSHOT

# Go into the GraphMat directory
cd ${GRAPHMAT_HOME}

# Make benchmark properties file
RAND=$$
BENCHMARK_FILE=gd-deploy-benchmark-${RAND}.properties
cp ./config/benchmarks/custom.properties ./config/benchmarks/${BENCHMARK_FILE}
sed -i "s/.*\(benchmark\.type\s*=\).*/\1 custom/" config/benchmarks/${BENCHMARK_FILE}
sed -i "s/.*\(benchmark\.name\s*=\).*/\1 ${SWEEP_PREFIX} for GraphMat on ${HOST} with ${NODE_TYPE} nodes/" config/benchmarks/${BENCHMARK_FILE}
sed -i "s/.*\(benchmark\.custom\.graphs\s*=\).*/\1 ${DATA_SETS}/" config/benchmarks/${BENCHMARK_FILE}
sed -i "s/.*\(benchmark\.custom\.algorithms\s*=\).*/\1 ${ALGORITHMS}/" config/benchmarks/${BENCHMARK_FILE}

sed -i "s/.*include = benchmarks\/.*//" config/benchmark.properties
sed -i "1s/^/include = benchmarks\/${BENCHMARK_FILE}\n/" config/benchmark.properties

# Copy over the settings for the platform if available
if [[ -v ${NO_NODES} ]]; then
    sed -i "s/.*\(platform\.graphmat\.num-machines\s*=\).*/\1 ${NO_NODES}/" config/platform.properties
fi

if [[ -v ${NO_THREADS} ]]; then
    sed -i "s/.*\(platform\.graphmat\.num-threads\s*=\).*/\1 ${NO_THREADS}/" config/platform.properties
elif [[ -v ${NO_CORES} ]]; then
    sed -i "s/.*\(platform\.graphmat\.num-threads\s*=\).*/\1 ${NO_CORES}/" config/platform.properties
else
    sed -i "s/.*\(platform\.graphmat\.num-threads\s*=\).*/\1 1/" config/platform.properties
fi

if [[ -v ${NO_PROCS} ]]; then
    sed -i "s/.*\(platform\.graphmat\.num-procs\s*=\).*/\1 ${NO_PROCS}/" config/platform.properties
else
    sed -i "s/.*\(platform\.graphmat\.num-procs\s*=\).*/\1 1/" config/platform.properties
fi

# Temporarily write the starting script
cat > ./script.sh <<- EOM
    module rm openmpi/gcc
    module rm openmpi/open64

    module add intel/compiler
    module add intel-mpi/64cd /home/clemaire/graphmat/graphalytics-0.9.0-SNAPSHOT-graphmat-0.2-SNAPSHOT/

    cd \$1
    bin/sh/run-benchmark.sh
EOM

# Mod the script
chmod +x ./script.sh

# Start the benchmark
ssh ${IPS[0]} "./script.sh ${GRAPHMAT_HOME}"

rm ./script.sh
rm config/benchmarks/${BENCHMARK_FILE}


