# Register the end time of the run
RUN_END=`date`
RUN_DURATION=$(($(date -d "${RUN_END}" "+%m") - $(date -d "${RUN_START}" "+%m")))

# The report metadata file is given by $METADATA
METADATA=${DEPLOYER_ROOT}/util/resources/metadata.json

# The report directory is the latest created directory in ./report/
if [[ -z ${REPORT} ]]; then
	REPORT=$(ls -td -- ./report/* | head -n 1)
fi


# Copy the metadata file into the report.
echo -e "[METADATA-WRITE]:\tCopying metadata.json into report directory at \'$REPORT\'"
cp ${METADATA} ${REPORT}/metadata.json
METADATA=${REPORT}/metadata.json


# Fill in the metadata for the report.
echo -e "[METADATA-WRITE]:\tFilling in metadata entries for \'$METADATA\'"
sed -i "s/\(.*\)%PROJ_NAME%\(.*\)/\1${PROJECT_NAME}\2/" ${METADATA}
sed -i "s/\(.*\)%PROJ_ID%\(.*\)/\1${PROJECT_ID}\2/" ${METADATA}

sed -i "s/\(.*\)%RUN_START%\(.*\)/\1${RUN_START}\2/" ${METADATA}
sed -i "s/\(.*\)%RUN_END%\(.*\)/\1${RUN_END}\2/" ${METADATA}
sed -i "s/\(.*\)%RUN_DURATION%\(.*\)/\1${RUN_DURATION}\2/" ${METADATA}
sed -i "s/\(.*\)%RUN_ID%\(.*\)/\1${RUN_ID}\2/" ${METADATA}

sed -i "s/\(.*\)%PLATFORM_NAME%\(.*\)/\1${PLATFORM_NAME}\2/" ${METADATA}
sed -i "s:\(.*\)%PLATFORM_HOME%\(.*\):\1${PLATFORM_HOME}\2:" ${METADATA}

sed -i "s/\(.*\)%NETWORK%\(.*\)/\1${NETWORK}\2/" ${METADATA}
sed -i "s/\(.*\)%SITE_NAME%\(.*\)/\1${HOST}\2/" ${METADATA}
sed -i "s/\(.*\)%USER%\(.*\)/\1${USER}\2/" ${METADATA}

sed -i "s/\(.*\)%NODES%\(.*\)/\1${NODES_JSON}\2/" ${METADATA}
sed -i "s/\(.*\)%DATASETS%\(.*\)/\1${DATA_SETS_JSON}\2/" ${METADATA}
sed -i "s/\(.*\)%ALGORITHMS%\(.*\)/\1${ALGORITHMS_JSON}\2/" ${METADATA}

# If the json directory exists in the report, apparently it finished.
if [[ -d ${REPORT}/json ]]; then
    echo -e "[METADATA-WRITE]:\tBenchmark has exited and finished normally"
    sed -i "s/\(.*\)%FINISHED%\(.*\)/\1true\2/" ${METADATA}
else
    echo -e "[METADATA-WRITE]:\tBenchmark exited in an unusual manner!"
    sed -i "s/\(.*\)%FINISHED%\(.*\)/\1false\2/" ${METADATA}
fi


