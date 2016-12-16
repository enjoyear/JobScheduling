#!/usr/bin/env bash

PID="$CHEN_SCHEDULER_DIR/.chen-scheduler-pid"

if [ -f "$PID" ]; then
  PID_VALUE=`cat $PID` > /dev/null 2>&1
else
  PID_VALUE=""
fi

if [ -f "$PID" ]; then
  if kill -0 $PID_VALUE > /dev/null 2>&1; then
    echo 'Stopping chen scheduler...'
    kill $PID_VALUE
    sleep 1
    if kill -0 $PID_VALUE > /dev/null 2>&1; then
      echo "Chen scheduler daemon did not stop gracefully, killing with kill -9"
      kill -9 $PID_VALUE
    fi
  else
    echo "Process $PID_VALUE is not running"
  fi
else
  echo "No pid file found"
fi
