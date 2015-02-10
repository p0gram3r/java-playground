#!/bin/bash

BASEDIR=$(dirname $0)/..

source $BASEDIR/bin/config.sh


echo "start downloading files to ${DOWNLOAD_DIR}"

java -cp $BASEDIR/target/pic-archive.jar ae/picarchive/ExportWipUrlsAsWgetDownload ${DOWNLOAD_DIR} | /bin/bash

echo "finished downloading"
