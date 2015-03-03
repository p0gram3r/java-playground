#!/bin/bash

CODE_DIR=$(dirname $0)/..

source $CODE_DIR/bin/config.sh


echo "start downloading files to ${DOWNLOAD_DIR}"
execJava ExportUrlsAsWgetDownload ${DOWNLOAD_DIR} | /bin/bash

echo "finished downloading"
