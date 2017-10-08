# assuming the current directory is the home directory
# of the graphalytics install.

# Parse arguments
while [[ $# -gt 1 ]]; do
	key="$1"
	case $key in
		-n|--name)
			new_name="$2"
			shift
			;;
		-r|--report)
			report="$2"
			shift
			;;
	esac
	shift
done

# Parse the platform name
config=./config/platform.properties
platform_name=$(grep "platform.name" $config | cut -d '=' -f 2 | tr -d '[:space:]')

# Check if a first argument is supplied
if [[ "$report" -eq "" ]]; then
	report=$(ls -td -- ./report/* | head -n 1)
fi

echo "Finishing report at '$report'"

# Copy the graphlaytics configx into a configs directory in the report.
echo "Copying graphalytics configs into report at '$report/configs'"
cp -r ./config "$report/configs"


# Set REPORTS global variable to some default value if none is supplied.
if [[ "$REPORTS" == "" ]]; then
        REPORTS=/var/scratch/clemaire/reports
        echo "Defaulting reports directory to '$REPORTS'"
else
	echo "Found reports directory to be '$REPORTS'"
fi

# If there is new name specified, move the report into a directory with that name.
if [[ "$new_name" == "" ]]; then
	synced_report=$REPORTS/$platform_name/${report##*/}
else
	synced_report=$REPORTS/$platform_name/$new_name
fi
mkdir -p "${synced_report%\/*}"

# Move the entire report to the sync location.
echo "Moving report to '$synced_report'"
mv $report $synced_report


# Make all files in the report read-only to prevent accidental changes.
find $synced_report/ -type f | xargs chmod 0444

