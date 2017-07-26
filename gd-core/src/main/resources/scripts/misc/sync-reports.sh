# Set REPORTS global variable to some default value if none is supplied.
if [[ "$REPORTS" == "" ]]; then
	REPORTS=/var/scratch/clemaire/reports
	echo "Defaulting reports directory to '$REPORTS'"
fi

# Rsync reports from all report hosts to this report host.
sync_hosts=./sync_hosts.sshids
if [ -f $sync_hosts ]; then
	while IFS= read -r host
	do
		echo "Syncing with report host: '$host'"
		rsync -rv $host $REPORTS
	done < "$sync_hosts"
fi

# Finally sync-upload the reports to google drive.
gdrive sync upload --delete-extraneous $REPORTS 0B8b6EnQqY3pdS2luQkpQMTNEU2M
