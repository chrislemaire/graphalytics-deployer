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
cat > ${PWD}/script.sh <<- EOM
    module rm openmpi/gcc
    module rm openmpi/open64

    module add intel/compiler
    module add intel-mpi/64

    cd \$1
    nohup bin/sh/run-benchmark.sh > /dev/null 2>&1 & echo \$! > \$1/experiment.pid
EOM

# Mod the script
echo -e "[GRAPHMAT-RUN]:\t\tWrote script file to ${PWD}/script.sh"
chmod +x ${PWD}/script.sh

# Remove the old pid file
rm ${PWD}/experiment.pid

# Start the benchmark
echo -e "[GRAPHMAT-RUN]:\t\tStarting benchmark..."
nohup ssh ${IPS[0]} "${PWD}/script.sh ${PLATFORM_HOME}" &

# Wait a few seconds
echo -e "[GRAPHMAT-RUN]:\t\tSleeping for 10 seconds to wait for benchmark to start..."
sleep 10

# Wait until the script has started
PID_FOUND=0
for (( i=0; i<10; ++i ))
do
    # Check if experiment.pid file exists.
    if [[ -f "$PWD/experiment.pid" ]]; then
        # Experiment pid is found, set PID_FOUND
        PID_FOUND=1
        echo -e "[GRAPHMAT-RUN]:\t\tFound PID file after $((i * 1/2)) minutes and $((30*(i-2*(i*1/2)))) seconds"
        break
    fi

    # Wait for half a minute until checking the pid file again.
    echo -e "[GRAPHMAT-RUN]:\t\tDid not find PID file yet, sleeping for 30 seconds..."
    sleep 30
done

if [[ PID_FOUND == 0 ]]; then

    echo -e "[GRAPHMAT-RUN]:\t\tCouldn't successfully start benchmark... Skipping this benchmark..."

else

    # Get the experiment pid
    PID=$(cat $PWD/experiment.pid)

    # Wait until it's done
    SLEEP_TIME=0
    echo -e "[GRAPHMAT-RUN]:\t\tSleeping while pid=${PID} is active..."
    while `ssh ${IPS[0]} kill -0 "$PID"`
    do
        # Wait for a minute until polling again.
        sleep 60
        SLEEP_TIME=$(($SLEEP_TIME+1))
        echo -e "[GRAPHMAT-RUN]:\t\tSlept for $SLEEP_TIME minutes."
    done
    echo -e "[GRAPHMAT-RUN]:\t\tBenchmark run terminated, cleaning up..."

fi

# Remove the script file
rm ${PWD}/script.sh


