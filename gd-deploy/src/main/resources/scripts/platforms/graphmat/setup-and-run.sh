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
    nohup bin/sh/run-benchmark.sh > \$1/experiment.log 2>&1 & echo \$! > \$1/experiment.pid
EOM

# Mod the script
chmod +x ${PWD}/script.sh

# Start the benchmark
ssh ${IPS[0]} "${PWD}/script.sh ${PLATFORM_HOME}"

# Wait until the script has started
sleep 5

# Get the experiment pid
PID=$(cat $PWD/experiment.pid)

# Wait until it's done
SLEEP_TIME=0
echo -e "[GRAPHMAT-RUN]:\t\tSleeping while pid=${PID} is active..."
while `ssh ${IPS[0]} kill -0 "$PID"`
do
        sleep 60
        SLEEP_TIME=$(($SLEEP_TIME+1))
        echo -e "[GRAPHMAT-RUN]:\t\tSlept for $SLEEP_TIME minutes."
done
echo -e "[GRAPHMAT-RUN]:\t\tBenchmark run terminated, cleaning up..."

# Remove the script file
rm ${PWD}/script.sh


