package chen.guo.scheduling.jobs;

import java.util.Map;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class BashJob extends ShellJob implements GeneralJob {
  private static final Logger logger = Logger.getLogger(BashJob.class);
  private final Map<String, Object> _dataMap;

  public BashJob() {
    this(null);
  }

  public BashJob(Map<String, Object> dataMap) {
    super("/bin/bash");
    _dataMap = dataMap;
  }

  public void execute(JobExecutionContext context) throws JobExecutionException {
    super.execute(context);
  }

  @Override
  public void executeOnce() throws JobExecutionException {
    logger.info(String.format("Executing %s once.", getClass().getSimpleName()));
    super.executeOnce(_dataMap);
  }
}
