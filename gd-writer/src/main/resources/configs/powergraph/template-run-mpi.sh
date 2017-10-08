#!/usr/bin/env bash

for host in `echo $1 | tr ',' ' '`;
do
  ssh $host mkdir -p `dirname $3`
  scp $3 $host:$3
done

LOG_PATH=$2
echo ${@:3}
mpirun -x GRAPHLAB_THREADS_PER_WORKER=1 -np 1 --map-by ppr:4:node --bind-to none --mca btl ^usnic -v --report-bindings --host $1 --nolocal ${@:3} &

echo $! > $LOG_PATH/executable.pid
wait $!
