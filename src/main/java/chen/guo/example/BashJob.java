package chen.guo.example;

import chen.guo.Main;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class BashJob implements Job {
  private static final Logger logger = Logger.getLogger(BashJob.class);

  public void execute(JobExecutionContext context) throws JobExecutionException {
    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
    String arg = (String) dataMap.get(Main.FILE_LOCATION);
    logger.info(arg);
//    JobKey jobKey = context.getJobDetail().getKey();
//    logger.info("BashJob says: " + jobKey + " executing at " + new Date());
  }
}
