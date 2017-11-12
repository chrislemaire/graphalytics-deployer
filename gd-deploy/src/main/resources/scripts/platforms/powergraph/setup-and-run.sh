# Copy over the settings for the platform if available
if [[ ! -z ${IPS} ]]; then
    sed -i "s/.*\(platform\.powergraph\.nodes\s*=\).*/\1 ${IPS}/" config/platform.properties
    echo -e "[POWERGRAPH-SETUP]:\tSetup with platform.powergraph.nodes = ${IPS}"
fi

if [[ ! -z ${NO_THREADS} ]]; then
    sed -i "s/.*\(platform\.powergraph\.num-threads\s*=\).*/\1 ${NO_THREADS}/" config/platform.properties
    echo -e "[POWERGRAPH-SETUP]:\tSetup with platform.powergraph.num-threads = ${NO_THREADS} by NO_THREADS"
elif [[ ! -z ${NO_CORES} ]]; then
    sed -i "s/.*\(platform\.powergraph\.num-threads\s*=\).*/\1 ${NO_CORES}/" config/platform.properties
    echo -e "[POWERGRAPH-SETUP]:\tSetup with platform.powergraph.num-threads = ${NO_CORES} by NO_CORES"
else
    sed -i "s/.*\(platform\.powergraph\.num-threads\s*=\).*/\1 1/" config/platform.properties
    echo -e "[POWERGRAPH-SETUP]:\tSetup with platform.powergraph.num-threads = 1 by default"
fi

# Temporarily write the starting script
cat > ./script.sh <<- EOM
    cd \$1
    nohup bin/sh/run-benchmark.sh > /dev/null 2>&1 &
    echo \$! > \$2
EOM

# Mod the script
chmod +x ./script.sh

# Set the experiment file
EXPERIMENT_PID="$PLATFORM_HOME/exp-%project_id%.pid"

# Start the benchmark
echo -e "[POWERGRAPH-RUN]:\tStarting benchmark..."
nohup ssh ${IPS[0]} "${PWD}/script.sh $PLATFORM_HOME $EXPERIMENT_PID" &

# Wait a few seconds
echo -e "[POWERGRAPH-RUN]:\tSleeping for 10 seconds to wait for benchmark to start..."
sleep 10

# Wait until the script has started
PID_FOUND=0
for (( i=0; i<10; ++i ))
do
    # Check if experiment pid file exists.
    if [[ -f "$EXPERIMENT_PID" ]]; then
        # Experiment pid is found, set PID_FOUND
        PID_FOUND=1
        echo -e "[POWERGRAPH-RUN]:\tFound PID file after $((i * 1/2)) minutes and $((30*(i-2*(i*1/2)))) seconds"
        break
    fi

    # Wait for half a minute until checking the pid file again.
    echo -e "[POWERGRAPH-RUN]:\tDid not find PID file yet, sleeping for 30 seconds..."
    sleep 30
done

if [[ PID_FOUND -ge 0 ]]; then

    # Get the experiment pid
    PID=$(cat ${EXPERIMENT_PID})

    # Wait until it's done
    SLEEP_TIME=0
    echo -e "[POWERGRAPH-RUN]:\tSleeping while pid=${PID} is active..."
    while [[ ! -z `ssh ${IPS[0]} "ps -eaf" | grep ${USER} | grep ${PID}` ]];
    do
        # Wait for a minute until polling again.
        sleep 60
        SLEEP_TIME=$(($SLEEP_TIME+1))
        echo -e "[POWERGRAPH-RUN]:\tSlept for $SLEEP_TIME minutes."
    done
    echo -e "[POWERGRAPH-RUN]:\tBenchmark run terminated, cleaning up..."

else

    # Print out that the benchmark wasn't started successfully
    echo -e "[POWERGRAPH-RUN]:\tCouldn't successfully start benchmark... Skipping this benchmark..."

fi

# Remove the old pid file
rm ${EXPERIMENT_PID}

# Remove the script file
rm ${PWD}/script.sh


