# Go into the GraphMat directory
cd /home/clemaire/graphmat/graphalytics-0.9.0-SNAPSHOT-graphmat-0.2-SNAPSHOT/

# Copy over the settings for the platform if available
if [[ -v ${NO_NODES} ]]; then
    sed "s/.*(platform\.graphmat\.num-machines\s*=).*/\1 ${NO_NODES}" config/platform.properties > config/platform.properties
fi

if [[ -v ${NO_THREADS} ]]; then
    sed "s/.*(platform\.graphmat\.num-threads\s*=).*/\1 ${NO_THREADS}" config/platform.properties > config/platform.properties
elif [[ -v ${NO_CPUS} ]]; then
    sed "s/.*(platform\.graphmat\.num-threads\s*=).*/\1 ${NO_CPUS}" config/platform.properties > config/platform.properties
else
    sed "s/.*(platform\.graphmat\.num-threads\s*=).*/\1 1" config/platform.properties > config/platform.properties
fi

if [[ -v ${NO_PROCS} ]]; then
    sed "s/.*(platform\.graphmat\.num-procs\s*=).*/\1 ${NO_PROCS}" config/platform.properties > config/platform.properties
else
    sed "s/.*(platform\.graphmat\.num-procs\s*=).*/\1 1" config/platform.properties > config/platform.properties
fi

# Random number generator and pid file name
RAND=$$
PID_FILE="/home/clemaire/graphmat/graphalytics-0.9.0-SNAPSHOT-graphmat-0.2-SNAPSHOT/experiment-${RAND}.pid"

# Start the benchmark
ssh ${IPS[0]} "/home/clemaire/graphmat/graphalytics-0.9.0-SNAPSHOT-graphmat-0.2-SNAPSHOT/bin/sh/run-benchmark.sh > ${PID_FILE}"

# Get the PID in memory
PID=`cat ${PID_FILE}`

# Wait until the process has finished
sleep_time=0
echo -e "[SETUP-GRAPHMAT]:\tSleeping while pid=$PID is active..."
while kill -0 "$PID"; do
        sleep 60
        sleep_time=$(($sleep_time+1))
        echo -e "[SETUP-GRAPHMAT]:\tSlept for $sleep_time minutes."
done
echo -e "[SETUP-GRAPHMAT]:\tBenchmark run terminated, cleaning up..."

# Remove the PID file to clean up
rm ${PID_FILE}

