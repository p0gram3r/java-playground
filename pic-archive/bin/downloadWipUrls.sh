#!/bin/bash

BASEDIR=$(dirname $0)/..

source $BASEDIR/bin/config.sh


echo "start downloading files to ${DOWNLOAD_DIR}"
execJava ExportWipUrlsAsWgetDownload ${DOWNLOAD_DIR} | /bin/bash

echo "finished downloading"
