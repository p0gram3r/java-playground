#!/bin/bash

CODE_DIR=$(dirname $0)/..

source $CODE_DIR/bin/config.sh


TOTAL_FILE_COUNT=$(ls ${DOWNLOAD_DIR}/ | wc -l)
if [ $TOTAL_FILE_COUNT == 0 ]; then
  echo "No files to process"
  exit
else
  echo "Found $TOTAL_FILE_COUNT files to process"
fi


TMP_FILE="/tmp/mapping_$(date +%Y-%m-%d)_$$.tmp"
echo "creating mapping file $TMP_FILE"


FILE_COUNT=0
for FILE in ${DOWNLOAD_DIR}/*; do
  if [ ! -f $FILE ]; then
    continue
  fi

  FILE_COUNT=$(expr $FILE_COUNT + 1)

  # filter small and empty files
  FILE_SIZE=$(wc -c "$FILE" | cut -f 1 -d ' ')
  if [ $FILE_SIZE -lt $MINIMUM_FILE_SIZE ]; then
    echo "removing $FILE (file size < $MINIMUM_FILE_SIZE)"
    rm $FILE
    continue;
  fi

  # the MD5 hash is the new identifier of the file within the archive
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

  # move the file to the archive
  TARGET_FILE_PATH="${PARENT_DIR}/${HASH}"
  mv "$FILE" "$TARGET_FILE_PATH"

  # register the file with the url
  URL_ID=$(basename "$FILE")
  echo "$URL_ID $HASH" >> $TMP_FILE
  
  # status update
  if [ $(expr $FILE_COUNT % 100) == 0 ]; then
    echo "finished $FILE_COUNT of $TOTAL_FILE_COUNT"
  fi
done

# register file hashes in database
execJava RegisterFile "$TMP_FILE"

# save backup of mapping file
mv ${TMP_FILE} ${BACKUP_DIR}

echo "finished archiving"
