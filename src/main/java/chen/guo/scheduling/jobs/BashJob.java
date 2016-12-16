package chen.guo.scheduling.jobs;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class BashJob extends ShellJob implements Job {
  private static final Logger logger = Logger.getLogger(BashJob.class);

  public void execute(JobExecutionContext context) throws JobExecutionException {
    logger.info("Executing BashJob");
    super.execute("/bin/bash", context);
  }
}
