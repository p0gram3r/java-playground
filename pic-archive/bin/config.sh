#!/bin/bash

DOWNLOAD_DIR=/var/p0gram3r/pic-archive/downloads
ARCHIVE_DIR=/var/p0gram3r/pic-archive/archive

function execJava() {
	local CLASS_NAME=$1
	shift

	java -cp $BASEDIR/target/pic-archive.jar ae/picarchive/${CLASS_NAME} "$@"

	EXIT_STATUS=$?
	if [ "$EXIT_STATUS" -ne "0" ]; then
        echo "error while executing java command"
        exit $EXIT_STATUS
    fi

}