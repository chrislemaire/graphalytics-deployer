# Set the threads and processes to run with
SWEEP_THREADS="%sweep_threads%"
SWEEP_THREADS=(${SWEEP_THREADS})
SWEEP_PROCS="%sweep_procs%"
SWEEP_PROCS=(${SWEEP_PROCS})

# Start the loop:
for (( proc_index=0; proc_index<${#SWEEP_THREADS[@]}; ++proc_index ))
do
    # Set the number of processes and the number of threads
    NO_PROCS=${SWEEP_PROCS[$proc_index]}
    NO_THREADS=${SWEEP_THREADS[$proc_index]}

    # Print the number of processes and the number of threads
    echo -e "[PROC-THREAD-SWEEP]:\tUsing number of threads ${NO_THREADS}"
    echo -e "[PROC-THREAD-SWEEP]:\tAnd using the number of processes ${NO_PROCS}"

    # Set the sweep prefix.
    SWEEP_PREFIX="${PROJECT_NAME}-${PROJECT_ID}_threads-vs-processes-${NO_THREADS}-${NO_PROCS}"


