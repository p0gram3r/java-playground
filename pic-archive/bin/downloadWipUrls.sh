#!/bin/bash

BASEDIR=$(dirname $0)/..

TMP_FILE=$BASEDIR/bin/$(date +%s-%N)

echo "exporting urls to temp file $TMP_FILE"
java -cp $BASEDIR/target/pic-archive.jar ae/picarchive/ExportWipUrlsAsWgetDownload >> $TMP_FILE

echo "changing file permissions"
chmod ugo+x $TMP_FILE

echo "executing file"
$TMP_FILE

echo "removing temp file"
rm $TMP_FILE