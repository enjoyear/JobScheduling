package chen.guo.example;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class BashJob implements Job {
  private static final Logger logger = Logger.getLogger(BashJob.class);

  public void execute(JobExecutionContext context) throws JobExecutionException {
    logger.info("Running...");
//    JobKey jobKey = context.getJobDetail().getKey();
//    logger.info("BashJob says: " + jobKey + " executing at " + new Date());
  }
}
