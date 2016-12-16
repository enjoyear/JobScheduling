package chen.guo.scheduling.jobs;

import chen.guo.Main;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public abstract class ShellJob {
  private static final Logger logger = Logger.getLogger(ShellJob.class);
  protected final String _executor;

  protected ShellJob(String executor) {
    _executor = executor;
  }

  protected void execute(JobExecutionContext context) throws JobExecutionException {
    logger.info(String.format("Executing %s as scheduled...", getClass().getSimpleName()));
    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
    executeOnce(dataMap);
  }

  protected void executeOnce(Map<String, Object> dataMap) throws JobExecutionException {
    String arg = (String) dataMap.get(Main.KEY_JOB_ARGUMENTS);
    logger.info("Current time: " + new Date());
    try {
      String[] cmdArray = {_executor, arg};
      logger.info("Running " + Arrays.toString(cmdArray));
      int exitCode = Runtime.getRuntime().exec(cmdArray).waitFor();
      if (exitCode != 0) {
        logger.error(String.format("Failed with exit code %d while running %s", exitCode, Arrays.toString(cmdArray)));
      }
    } catch (InterruptedException | IOException e) {
      throw new JobExecutionException(e, false);
    }
  }
}
