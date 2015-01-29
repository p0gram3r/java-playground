#!/bin/bash

BASEDIR=$(dirname $0)/..
DOWNLOAD_DIR=$BASEDIR/.downloads
ARCHIVE_DIR=$BASEDIR/.archive


TOTAL_FILE_COUNT=$(ls ${DOWNLOAD_DIR}/ | wc -l)
if [ $TOTAL_FILE_COUNT == 0 ]; then
  echo "No files to process"
  exit
else
  echo "Found $TOTAL_FILE_COUNT files to process"
fi


FILE_COUNT=0
for FILE in ${DOWNLOAD_DIR}/*; do
  if [ ! -f $FILE ]; then
    continue
  fi

  # the MD5 hash determines the target directory
  HASH=($(md5sum $FILE))

  # split the hash into 11 parts
  PARTS=()
  for i in $(seq 0 3 30); do
    INDEX=$(expr $i / 3)
    PARTS[$INDEX]=${HASH:i:3}
  done

  # create the target directory if necessary
  PARENT_DIR=$ARCHIVE_DIR/${PARTS[0]}/${PARTS[1]}/${PARTS[2]}/${PARTS[3]}/${PARTS[4]}/${PARTS[5]}/${PARTS[6]}/${PARTS[7]}/${PARTS[8]}/${PARTS[9]}/${PARTS[10]}/
  mkdir -p $PARENT_DIR

  # move the file
  mv $FILE $PARENT_DIR

  FILE_COUNT=$(expr $FILE_COUNT + 1)
  if [ $(expr $FILE_COUNT % 10) == 0 ]; then
    echo "finished $FILE_COUNT of $TOTAL_FILE_COUNT"
  fi
done

echo "finished $FILE_COUNT of $TOTAL_FILE_COUNT"