## How to use
1. Generate the distribution file
	* call ./bin/package.sh
	* find the gzipped distribution file at your root folder named as job-scheduling-dist.tar.gz
2. Deploy and un-tar the distribution file. And set environment variable CHEN_SCHEDULER_DIR to where you un-tar the distribution file
3. Start the job by calling $CHEN_SCHEDULER_DIR/bin/startScheduler.sh yourJobConfigurationFile.properties
4. Stop the job by calling $CHEN_SCHEDULER_DIR/bin/stopScheduler.sh 

## Configuration

Name | description
---- | -----
cron.expression | Find examples at [http://www.quartz-scheduler.org/documentation/quartz-2.2.x/examples/Example3.html](http://)
job.class | Choose BashJob or PythonJob (Will be merged in the future)
job.args | Specify a comma separated args for your job


## ToDo list:
- refactor scripts under bin
- Using thread pool 'org.quartz.simpl.SimpleThreadPool' - with 3 threads???
- allow scheduling for multiple jobs.
- print scheduling information to log
