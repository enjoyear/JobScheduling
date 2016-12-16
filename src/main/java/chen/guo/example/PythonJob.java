package chen.guo.example;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class PythonJob implements Job {
  private static final Logger logger = Logger.getLogger(PythonJob.class);
  private final String _arg1;

  public PythonJob(String arg1) {
    _arg1 = arg1;
  }

  public void execute(JobExecutionContext context) throws JobExecutionException {
    logger.info("Running...");
//    JobKey jobKey = context.getJobDetail().getKey();
//    logger.info("BashJob says: " + jobKey + " executing at " + new Date());
  }
}
