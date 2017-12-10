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

sed -i "s/.*%RUN_START%.*/`date`/" ${METADATA}
sed -i "s/.*%RUN_END%.*/`date`/" ${METADATA}
sed -i "s/.*%RUN_ID%.*/${RUN_ID}/" ${METADATA}

sed -i "s/.*%PLATFORM_NAME%.*/${PLATFORM_NAME}/" ${METADATA}
sed -i "s/.*%PLATFORM_HOME%.*/${PLATFORM_HOME}/" ${METADATA}

sed -i "s/.*%SITE_NAME%.*/${SITE_NAME}/" ${METADATA}
sed -i "s/.*%USER%.*/${USER}/" ${METADATA}
