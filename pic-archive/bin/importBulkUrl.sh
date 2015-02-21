#!/bin/bash

BASEDIR=$(dirname $0)/..

source $BASEDIR/bin/config.sh


if [[ "$#" == 2 && "$1" == "-f" ]]; then
	if [[ ! -f "$2" ]]; then
		echo "file $2 not found!"
		exit -1
	fi

	echo "importing content of file $2"
	while read line; do execJava ImportBulkUrl "$line"; done < "$2"
else
	echo "importing command line arguments"
	execJava ImportBulkUrl "$@"
fi