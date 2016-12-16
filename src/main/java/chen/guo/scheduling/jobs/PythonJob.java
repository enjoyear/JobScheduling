package chen.guo.scheduling.jobs;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class PythonJob extends ShellJob implements Job {
  private static final Logger logger = Logger.getLogger(PythonJob.class);

  public void execute(JobExecutionContext context) throws JobExecutionException {
    logger.info("Executing PythonJob");
    super.execute("/bin/python", context);
  }
}
