#!/usr/bin/env bash

bin_path="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $bin_path/..

#Currently at project path
mvn package
tar -czf job-scheduling-dist.tar.gz bin/ target/job-scheduler-1.0-SNAPSHOT-jar-with-dependencies.jar
