# Register the end time of the run
RUN_END=`date`

# The report metadata file is given by $METADATA
METADATA=${DEPLOYER_ROOT}/util/resources/report-metadata.json

# The report directory is the latest created directory in ./report/
if [[ -z ${REPORT} ]]; then
	REPORT=$(ls -td -- ./report/* | head -n 1)
fi


# Copy the metadata file into the report.
cp ${METADATA} ${REPORT}/metadata.json
METADATA=${REPORT}/metadata.json


# Fill in the metadata for the report.
sed -i "s/.*%PROJ_NAME%.*/${PROJECT_NAME}/" ${METADATA}
sed -i "s/.*%PROJ_ID%.*/${PROJECT_ID}/" ${METADATA}

sed -i "s/.*%RUN_START%.*/${RUN_START}/" ${METADATA}
sed -i "s/.*%RUN_END%.*/${RUN_END}/" ${METADATA}
sed -i "s/.*%RUN_END%.*/`$(($(date -d "$RUN_START" "+%s") - $(date -d "$RUN_END" "+%s")))`/" ${METADATA}
sed -i "s/.*%RUN_ID%.*/${RUN_ID}/" ${METADATA}

sed -i "s/.*%PLATFORM_NAME%.*/${PLATFORM_NAME}/" ${METADATA}
sed -i "s/.*%PLATFORM_HOME%.*/${PLATFORM_HOME}/" ${METADATA}

sed -i "s/.*%NETWORK%.*/${NETWORK}/" ${METADATA}
sed -i "s/.*%SITE_NAME%.*/${SITE_NAME}/" ${METADATA}
sed -i "s/.*%USER%.*/${USER}/" ${METADATA}

sed -i "s/.*%NODES%.*/${NODES_JSON}/" ${METADATA}
sed -i "s/.*%DATASETS%.*/${DATA_SETS_JSON}/" ${METADATA}
sed -i "s/.*%ALGORITHMS%.*/${ALGORITHMS_JSON}/" ${METADATA}

# If the json directory exists in the report, apparently it finished.
if [[ -d ${REPORT}/json ]]; then
    sed -i "s/.*%FINISHED%.*/true/" ${METADATA}
else
    sed -i "s/.*%FINISHED%.*/false/" ${METADATA}
fi


