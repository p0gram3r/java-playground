#!/bin/bash

BASEDIR=$(dirname $0)/..

source $BASEDIR/bin/config.sh


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

  # the MD5 hash is the basis fordetermines the target directory
  HASH=($(md5sum $FILE))

  # split the hash into 11 parts
  PARTS=()
  for i in $(seq 0 3 30); do
    INDEX=$(expr $i / 3)
    PARTS[$INDEX]=${HASH:i:3}
  done

  # create the target directory if necessary
  PARENT_DIR="${ARCHIVE_DIR}/${PARTS[0]}/${PARTS[1]}/${PARTS[2]}/${PARTS[3]}/${PARTS[4]}/${PARTS[5]}/${PARTS[6]}/${PARTS[7]}/${PARTS[8]}/${PARTS[9]}/${PARTS[10]}"
  mkdir -p $PARENT_DIR

  # register the file with the url
  URL_ID=$(basename "$FILE")
  TARGET_FILE_PATH="${PARENT_DIR}/${HASH}"
  execJava RegisterFile "$URL_ID" "$TARGET_FILE_PATH"

  # move the file
  mv "$FILE" "$TARGET_FILE_PATH"

  # status update
  FILE_COUNT=$(expr $FILE_COUNT + 1)
  if [ $(expr $FILE_COUNT % 10) == 0 ]; then
    echo "finished $FILE_COUNT of $TOTAL_FILE_COUNT"
  fi
done

echo "finished $FILE_COUNT of $TOTAL_FILE_COUNT"