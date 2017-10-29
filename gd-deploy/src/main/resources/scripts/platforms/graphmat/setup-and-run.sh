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
ssh ${IPS[0]} "${PWD}/script.sh ${PLATFORM_HOME}"

rm ./script.sh


