# Setup the run parameters
ALGORITHMS="%algorithms%"
DATA_SETS="%data_sets%"
ALGORITHMS_JSON="%algorithms_json%"
DATA_SETS_JSON="%data_sets_json%"
REPETITIONS=%repetitions%
AFFINITY="%affinity%"

# Print out the run-setup parameters.
echo -e "[SETUP-RUN-PARAMETERS]:\tALGORITHMS=$ALGORITHMS"
echo -e "[SETUP-RUN-PARAMETERS]:\tDATA_SETS=$DATA_SETS"
echo -e "[SETUP-RUN-PARAMETERS]:\tALGORITHMS_JSON=$ALGORITHMS_JSON"
echo -e "[SETUP-RUN-PARAMETERS]:\tDATA_SETS_JSON=$DATA_SETS_JSON"
echo -e "[SETUP-RUN-PARAMETERS]:\tREPETITIONS=$REPETITIONS"
echo -e "[SETUP-RUN-PARAMETERS]:\tAFFINITY=$AFFINITY"

# Save the some run-specific information
RAND=$$
RUN_ID=${RAND}
RUN_START=`date`

