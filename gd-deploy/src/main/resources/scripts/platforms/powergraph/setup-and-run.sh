# Setup platform properties.
PLATFORM_NAME="GraphMat"

# Copy over the settings for the platform if available
if [[ ! -z ${IPS} ]]; then
    COMMA_SEP_IPS=`echo ${IPS[*]} | sed "s/ /,/g"`
    sed -i "s/.*\(platform\.powergraph\.nodes\s*=\).*/\1 ${COMMA_SEP_IPS}/" config/platform.properties
    echo -e "[POWERGRAPH-SETUP]:\tSetup with platform.powergraph.nodes = ${COMMA_SEP_IPS}"
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

# Set the experiment file
EXPERIMENT_DONE="$PLATFORM_HOME/done-$PROJECT_ID"

# Temporarily write the starting script
cat > ${PWD}/script.sh <<- EOM
    cd ${PLATFORM_HOME}
    bin/sh/run-benchmark.sh
    echo "done" > ${EXPERIMENT_DONE}
EOM

# Mod the script
echo -e "[POWERGRAPH-RUN]:\tWrote script file to ${PWD}/script.sh"
chmod +x ${PWD}/script.sh

# Start the benchmark
echo -e "[POWERGRAPH-RUN]:\tStarting benchmark on ${IPS[0]}"
ssh ${IPS[0]} "nohup ${PWD}/script.sh > /dev/null 2>&1 &"

# Wait until benchmark-done-... file exists
seconds=0
while [[ ! -f ${EXPERIMENT_DONE} ]]
do
    # Sleep a minute
    sleep 60
    seconds=$((seconds+60))
    echo -e "[POWERGRAPH-RUN]:\tBenchmark has been running for $((seconds/60)) minutes"
done

# Benchmark finished
echo -e "[POWERGRAPH-RUN]:\tBenchmark finished after $((seconds/3600)) hours and $(( (seconds-(seconds/3600)*3600)/60 ))"

# Remove the done file
rm ${EXPERIMENT_DONE}

# Remove the script file
rm ${PWD}/script.sh


