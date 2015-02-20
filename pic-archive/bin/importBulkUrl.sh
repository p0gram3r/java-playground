#!/bin/bash

BASEDIR=$(dirname $0)/..

source $BASEDIR/bin/config.sh


if [[ "$#" == 2 && "$1" == "-f" ]]; then
	if [[ ! -f "$2" ]]; then
		echo "file $2 not found!"
		exit -1
	fi

	echo "Reading content of file $2"
	execJava ImportBulkUrl `cat $2`
else
	execJava ImportBulkUrl "$@"
fi