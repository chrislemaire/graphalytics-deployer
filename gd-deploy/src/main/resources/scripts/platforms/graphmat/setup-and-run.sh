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

if [[ ! -z ${AFFINITY} ]]; then
    sed -i "s/.*\(platform\.graphmat\.affinity\s*=\).*/\1 ${AFFINITY}/" config/platform.properties
    echo -e "[GRAPHMAT-SETUP]:\tSetup with platform.graphmat.affinity = ${AFFINITY} by AFFINITY"
else
    sed -i "s/.*\(platform\.graphmat\.affinity\s*=\).*/\1 scatter/" config/platform.properties
    echo -e "[GRAPHMAT-SETUP]:\tSetup with platform.graphmat.affinity = scatter by default"
fi

# Set the experiment file
EXPERIMENT_DONE="$PLATFORM_HOME/done-$PROJECT_ID"

# Temporarily write the starting script
cat > ${PWD}/script.sh <<- EOM
#!/bin/bash
    module rm openmpi/gcc
    module rm openmpi/open64

    module add intel/compiler
    module add intel-mpi/64

    cd ${PLATFORM_HOME}
    bin/sh/run-benchmark.sh
    echo "done" > ${EXPERIMENT_DONE}
EOM

# Mod the script
echo -e "[GRAPHMAT-RUN]:\t\tWrote script file to ${PWD}/script.sh"
chmod +x ${PWD}/script.sh

# Start the benchmark
echo -e "[GRAPHMAT-RUN]:\t\tStarting benchmark..."
ssh ${IPS[0]} "nohup ${PWD}/script.sh > /dev/null 2>&1 &"

# Wait until benchmark-done-... file exists
seconds=0
while [[ ! -f ${EXPERIMENT_DONE} ]]
do
    # Sleep a minute
    sleep 60
    seconds=$((seconds+60))
    echo -e "[GRAPHMAT-RUN]:\t\tBenchmark has been running for $((seconds/60)) minutes"
done

# Benchmark finished
echo -e "[GRAPHMAT-RUN]:\t\tBenchmark finished after $((seconds/3600)) hour(s) and $(( (seconds-(seconds/3600)*3600)/60 )) minute(s)"

# Remove the done file
rm ${EXPERIMENT_DONE}

# Remove the script file
rm ${PWD}/script.sh


