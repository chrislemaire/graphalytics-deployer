# Setup the run parameters
ALGORITHMS=%algorithms%
DATA_SETS=%data_sets%

# Set the threads and processes to run with
SWEEP_THREADS=("%sweep_threads%")
SWEEP_PROCS=("%sweep_procs%")

# Start the loop:
for (( i=0; i<${#SWEEP_THREADS[@]}; ++i ))
do
    # Set the number of processes and the number of threads
    NO_PROCS=${SWEEP_PROCS[i]}
    NO_THREADS=${SWEEP_THREADS[i]}

    # Set the sweep prefix.
    SWEEP_PREFIX="threads-vs-processes-${NO_THREADS}-${NO_PROCS}"

