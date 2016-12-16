#!/usr/bin/env bash

function die() {
  echo -e "\nError: $@\n" 1>&2
  exit 1
}

if [ -z "$CHEN_SCHEDULER_DIR" ]; then
  die "CHEN_SCHEDULER_DIR is not set!"
fi

java -jar $1 --job $2
