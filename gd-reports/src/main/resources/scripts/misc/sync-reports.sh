#!/usr/bin/env bash

# Setup variables.
SITE="%reports_host%"
SITE_REPORTS="%reports_host_dir%"
LOCAL_REPORTS="%reports_local_dir"

echo -e "[SYNC-REPORTS]:\tSyncing hosts to single host $SITE."
ssh bastion "ssh $SITE /home/clemaire/scripts/sync-hosts.sh"

echo -e "[SYNC-REPORTS]:\tSyncing from $SITE to bastion."
ssh bastion "rsync -rv $SITE:/var/scratch/clemaire/reports ./"

echo -e "[SYNC-REPORTS]:\tSyncing from bastion to native system."
rsync -rv bastion:reports ./

echo -e "[SYNC-REPORTS]:\tUnzipping reports tar to get "
tar --skip-old-files -xzvf reports.tar.gz

echo -e "[SYNC-REPORTS]:\tRemoving temporary reports on bastion."
ssh bastion "rm -rf ./reports"