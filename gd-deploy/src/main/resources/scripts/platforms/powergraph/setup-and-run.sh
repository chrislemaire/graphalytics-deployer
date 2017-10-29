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

# Temporarily write the starting script
cat > ./script.sh <<- EOM
    cd \$1
    bin/sh/run-benchmark.sh
EOM

# Mod the script
chmod +x ./script.sh

# Start the benchmark
ssh ${IPS[0]} "${PWD}/script.sh ${PLATFORM_HOME}"

rm ./script.sh


