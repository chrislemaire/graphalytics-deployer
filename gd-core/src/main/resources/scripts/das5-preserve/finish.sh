#!/bin/bash

# author: Alexandru Uta.

# Gets the following options/command line arguments:
#   user file_system_id


# Source the reservation ID.
source reservation

# Get the node numbers.
export IPS=`preserve -llist | grep $RESERVATION | tr -d '\t' | awk -F"node" '{ for(i = 0; i < 100; i++) { if ($i) print $i; } }' | grep -v $1 |  sed 's/$2\.*//' | tr -d '\n'`

# Put the nodes in the slaves file.
rm slaves.txt
for ip in ${IPS} ; do
    echo 10.149.$2.${ip} >> slaves.txt
done
