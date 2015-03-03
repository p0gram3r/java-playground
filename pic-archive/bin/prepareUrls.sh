#!/bin/bash

CODE_DIR=$(dirname $0)/..

source $CODE_DIR/bin/config.sh


execJava PrepareUrlsForDownload "$@"
