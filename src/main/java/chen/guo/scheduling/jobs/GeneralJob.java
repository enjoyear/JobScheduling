package chen.guo.scheduling.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionException;


public interface GeneralJob extends Job {
  void executeOnce() throws JobExecutionException;
}
