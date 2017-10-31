# Parameters:
# %partition% -> The partition to reserve on.
# %duration%  -> The time to reserve the node(s) for.

# Reserve the nodes
salloc --partition=%partition% --nodes=${NO_NODES} --time=%duration%

# Get the reservation number
RESERVATION=`scontrol show jobid -o | grep "${USER}" | sed -e '$!d' -e 's/JobId=\([0-9]\+\).*/\1/'`




