#!/usr/bin/env bash

function die() {
  echo -e "\nError: $@\n" 1>&2
  exit 1
}

if [ -z "$JAVA_HOME" ]; then
  die "$JAVA_HOME is not set! Only Java 8+ is supported."
fi

if [ -z "$CHEN_SCHEDULER_DIR" ]; then
  die "CHEN_SCHEDULER_DIR is not set!"
fi

if [ ! -d "$CHEN_SCHEDULER_DIR" ]; then
  mkdir -p "$CHEN_SCHEDULER_DIR"
fi

PID="$CHEN_SCHEDULER_DIR/.chen-scheduler-pid"

echo "Start scheduling..."
command="$JAVA_HOME/bin/java -Xmx2g -Xms1g "
command+="-jar $CHEN_SCHEDULER_DIR/target/job-scheduler-1.0-SNAPSHOT-jar-with-dependencies.jar --job $1"
echo "Running command is:"
echo "$command"
nohup $command & echo $! > $PID
