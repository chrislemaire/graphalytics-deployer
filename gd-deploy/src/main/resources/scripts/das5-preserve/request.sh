#!/bin/bash

# author: Alexandru Uta.

# Command line arguments:
# 1 -> number of nodes to reserve
# 2 -> time to reserve nodes
# 3 -> special queue
# 4 ->


echo "Reserving $1 nodes."

# Make the reservation for the worker nodes.
RESERVATION=`preserve -1 -# $1 -t $2 $3  | grep "Reservation number" | awk '{ print $3 }' | sed 's/://'`
echo RESERVATION=${RESERVATION} > reservation


# Wait for the nodes to be available
while [ `preserve -llist | grep ${RESERVATION} | awk '{ print $9 }'` == "-" ]
do
    echo "WAITING FOR THE NODES TO BE AVAILABLE...";
    sleep 1;
done


# Get the node numbers.
IPS=`preserve -llist | grep ${RESERVATION} | awk '{ for(i=9; i<=NF; i++) print $i; }' | sed "s/node//"`
%all_ips%=($IPS)


