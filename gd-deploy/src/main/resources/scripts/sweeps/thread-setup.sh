# Set the threads and processes to run with
SWEEP_THREADS="%sweep_threads%"
SWEEP_THREADS=(${SWEEP_THREADS})

NO_PROCS=1

# Start the loop:
for (( proc_index=0; proc_index<${#SWEEP_THREADS[@]}; ++proc_index ))
do
    # Set the number of processes and the number of threads
    NO_THREADS=${SWEEP_THREADS[$proc_index]}

    # Print the number of processes and the number of threads
    echo -e "[PROC-THREAD-SWEEP]:\tUsing number of threads ${NO_THREADS}"
    echo -e "[PROC-THREAD-SWEEP]:\tAnd using default number of processes ($NO_PROCS)"

    # Set the sweep prefix.
    SWEEP_PREFIX="${PROJECT_NAME}-${PROJECT_ID}_threads-${NO_THREADS}"


