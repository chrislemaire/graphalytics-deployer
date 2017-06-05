#!/bin/bash

# author: Alexandru Uta.

# Gets no options/command line arguments.

source reservation

# Wait for the nodes to be available
while [ `preserve -llist | grep $RESERVATION | awk '{ print $9 }'` == "-" ]
do
    echo "WAITING FOR THE NODES TO BE AVAILABLE...";
    sleep 1;
done
