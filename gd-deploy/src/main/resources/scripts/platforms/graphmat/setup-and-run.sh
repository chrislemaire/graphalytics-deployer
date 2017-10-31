# Copy over the settings for the platform if available
if [[ ! -z ${NO_NODES} ]]; then
    sed -i "s/.*\(platform\.graphmat\.num-machines\s*=\).*/\1 ${NO_NODES}/" config/platform.properties
    echo -e "[GRAPHMAT-SETUP]:\tSetup with platform.graphmat.num-machines = ${NO_NODES} by NO_NODES"
fi

if [[ ! -z ${NO_THREADS} ]]; then
    sed -i "s/.*\(platform\.graphmat\.num\-threads\s*=\).*/\1 ${NO_THREADS}/" config/platform.properties
    echo -e "[GRAPHMAT-SETUP]:\tSetup with platform.graphmat.num-threads = ${NO_THREADS} by NO_THREADS"
elif [[ ! -z ${NO_CORES} ]]; then
    sed -i "s/.*\(platform\.graphmat\.num\-threads\s*=\).*/\1 ${NO_CORES}/" config/platform.properties
    echo -e "[GRAPHMAT-SETUP]:\tSetup with platform.graphmat.num-threads = ${NO_CORES} by NO_CORES"
else
    sed -i "s/.*\(platform\.graphmat\.num\-threads\s*=\).*/\1 1/" config/platform.properties
    echo -e "[GRAPHMAT-SETUP]:\tSetup with platform.graphmat.num-threads = 1 by default"
fi

if [[ ! -z ${NO_PROCS} ]]; then
    sed -i "s/.*\(platform\.graphmat\.num\-procs\s*=\).*/\1 ${NO_PROCS}/" config/platform.properties
    echo -e "[GRAPHMAT-SETUP]:\tSetup with platform.graphmat.num-procs = ${NO_PROCS} by NO_PROCS"
else
    sed -i "s/.*\(platform\.graphmat\.num\-procs\s*=\).*/\1 1/" config/platform.properties
    echo -e "[GRAPHMAT-SETUP]:\tSetup with platform.graphmat.num-procs = 1 by default"
fi

# Temporarily write the starting script
cat > ./script.sh <<- EOM
    module rm openmpi/gcc
    module rm openmpi/open64

    module add intel/compiler
    module add intel-mpi/64

    cd \$1
    bin/sh/run-benchmark.sh
EOM

# Mod the script
chmod +x ./script.sh

# Start the benchmark
ssh ${IPS[0]} "${PWD}/script.sh ${PLATFORM_HOME}"

rm ./script.sh


