# author: Alexandru Uta.

# Parameters:
# %partition% -> The partition to reserve on.
# %time%      -> The time to reserve the node(s) for.

echo -e [DAS5-PRESERVE]:'\t'Reserving nodes with command preserve %preserve_args%.

# Make the reservation for the worker nodes.
RESERVATION=`preserve -# ${NO_NODES} -q %partition% -t %time% | grep "Reservation number" | awk '{ print $3 }' | sed 's/://'`


# Wait for the nodes to be available
while [ `preserve -llist | grep ${RESERVATION} | awk '{ print $9 }'` == "-" ]
do
    echo -e [DAS5-PRESERVE]:'\t'Waiting for nodes to be available...
    sleep 1;
done


# Get the node numbers.
NODES=`preserve -llist | grep ${RESERVATION} | awk '{ for(i=9; i<=NF; i++) print $i; }' | sed "s/node[0-9]//"`
NODES=(${NODES})

# Get the IP of infiniband
IP_IB0=`cat /etc/sysconfig/network-scripts/ifcfg-ib0 | grep IPADDR | sed "s/.*=\(.*\.\).*/\1/"`

# Move the node numbers into proper ips
for node in ${NODES}
do
    IPS="${IPS} ${IP_IB0}${node}"
done
IPS=("${IPS}")

echo -e [DAS5-PRESERVE]:'\t'Completed request.
echo -e [DAS5-PRESERVE]:'\t'IP-list: ${IPS}



