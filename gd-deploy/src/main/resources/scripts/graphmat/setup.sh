# Copy over the settings for the platform if available
if [[ -v ${NO_NODES} ]]; then
    sed config/platform.properties "s/.*(platform\.graphmat\.num-machines\s*=).*/\1 ${NO_NODES}" > config/platform.properties
fi

if [[ -v ${NO_THREADS} ]]; then
    sed config/platform.properties "s/.*(platform\.graphmat\.num-threads\s*=).*/\1 ${NO_THREADS}" > config/platform.properties
elif [[ -v ${NO_CPUS} ]]; then
    sed config/platform.properties "s/.*(platform\.graphmat\.num-threads\s*=).*/\1 ${NO_CPUS}" > config/platform.properties
else
    sed config/platform.properties "s/.*(platform\.graphmat\.num-threads\s*=).*/\1 1" > config/platform.properties
fi

if [[ -v ${NO_PROCS} ]]; then
    sed config/platform.properties "s/.*(platform\.graphmat\.num-procs\s*=).*/\1 ${NO_PROCS}" > config/platform.properties
else
    sed config/platform.properties "s/.*(platform\.graphmat\.num-procs\s*=).*/\1 1" > config/platform.properties
fi


