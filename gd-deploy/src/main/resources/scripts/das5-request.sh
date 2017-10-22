#!/bin/bash

# author: Alexandru Uta.

# Parameters:
# %preserve_args% -> The arguments for preserve.

echo "Reserving $1 nodes."

# Make the reservation for the worker nodes.
RESERVATION=`preserve %preserve_args% | grep "Reservation number" | awk '{ print $3 }' | sed 's/://'`
echo RESERVATION=${RESERVATION} > reservation


# Wait for the nodes to be available
while [ `preserve -llist | grep ${RESERVATION} | awk '{ print $9 }'` == "-" ]
do
    echo "WAITING FOR THE NODES TO BE AVAILABLE...";
    sleep 1;
done


# Get the node numbers.
NODE_NAMES=`preserve -llist | grep ${RESERVATION} | awk '{ for(i=9; i<=NF; i++) print $i; }' | sed "s/node\d//"`
NODE_NAMES=(${NODE_NAMES})

# Get the IP of infiniband
IP_IB0=`cat /etc/sysconfig/network-scripts/ifcfg-ib0 | grep IPADDR | sed "s/.*=\(.*\.\).*/\1/"`

# Move the node numbers into proper ips
for node in ${NODES}
do
    IPS="${IPS} ${IP_IB0}${node}"
done

