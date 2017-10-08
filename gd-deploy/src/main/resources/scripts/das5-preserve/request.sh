#!/bin/bash

# author: Alexandru Uta.

# Gets the following options/command line arguments:
#   number_of_nodes time_to_reserve
echo "Reserving $1 nodes."

# Make the reservation for the worker nodes.
RESERVATION=`preserve -1 -# $1 -t $2  | grep "Reservation number" | awk '{ print $3 }' | sed 's/://'`
echo RESERVATION=${RESERVATION} > reservation
