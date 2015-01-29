#!/bin/bash

BASEDIR=$(dirname $0)/..

java -cp $BASEDIR/target/pic-archive.jar ae/picarchive/ExportWipUrlsAsWgetDownload
