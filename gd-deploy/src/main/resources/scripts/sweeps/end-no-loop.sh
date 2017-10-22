# Generate a random number for the report
RAND=`awk 'BEGIN { srand(); print int(rand() * 1000000000) }'`

# Finish the report using the finish report script
./finish-report.sh -n ${PLATFORM}-${NO_NODES}-nodes_${SWEEP_PREFIX}_${RAND}


