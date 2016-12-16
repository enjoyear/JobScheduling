package chen.guo.scheduling.jobs;

import chen.guo.Main;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class ShellJob {
  private static final Logger logger = Logger.getLogger(ShellJob.class);

  public void execute(String executor, JobExecutionContext context) throws JobExecutionException {
    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
    String arg = (String) dataMap.get(Main.KEY_JOB_ARGUMENTS);
    logger.info("Current time: " + new Date());
    try {
      String[] cmdArray = {executor, arg};
      logger.info("Running " + Arrays.toString(cmdArray));
      int exitCode = Runtime.getRuntime().exec(cmdArray).waitFor();
      if (exitCode != 0) {
        logger.error("Failed while running " + Arrays.toString(cmdArray));
      }
    } catch (InterruptedException e) {
      throw new JobExecutionException(e, false);
    } catch (IOException e) {
      throw new JobExecutionException(e, false);
    }
  }
}
