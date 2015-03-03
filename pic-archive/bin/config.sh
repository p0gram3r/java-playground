#!/bin/bash

ROOT_DIR="/opt/p0gram3r/pic-archive"
DOWNLOAD_DIR="${ROOT_DIR}/downloads"
ARCHIVE_DIR="${ROOT_DIR}/archive"
BACKUP_DIR="${ROOT_DIR}/backup"

MINIMUM_FILE_SIZE=20000

function execJava() {
	local CLASS_NAME=$1
	shift

	java -cp ${CODE_DIR}/target/pic-archive.jar org/p0gram3r/picarchive/${CLASS_NAME} "$@"

	EXIT_STATUS=$?
	if [ "$EXIT_STATUS" -ne "0" ]; then
        echo "error while executing java command"
        exit $EXIT_STATUS
    fi

}
