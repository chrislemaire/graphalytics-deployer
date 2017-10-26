    # Generate a random number for the report
    RAND=`awk 'BEGIN { srand(); print int(rand() * 1000000000) }'`

    # Finish the report using the finish report script
    ./finish-report.sh -n ${SWEEP_PREFIX}_${PLATFORM}-${NO_NODES}-nodes-${NO_CPUS}-threads_${RAND}

# Finish the sweep loop.
done


