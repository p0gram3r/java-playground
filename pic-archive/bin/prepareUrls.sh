#!/bin/bash

BASEDIR=$(dirname $0)/..

source $BASEDIR/bin/config.sh

execJava PrepareUrlsForDownload "$@"
