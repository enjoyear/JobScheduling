package chen.guo.example;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class SimpleScheduler {

  private final String _cronExpression;
  private final org.quartz.Scheduler _scheduler;

  public SimpleScheduler(Class<? extends Job> jobClass, String cronExpression, JobDataMap jobDataMap)
      throws SchedulerException {
    _cronExpression = cronExpression;
    SchedulerFactory sf = new StdSchedulerFactory();
    _scheduler = sf.getScheduler();

    JobDetail job = JobBuilder.newJob(jobClass).withIdentity("job1", "group1").usingJobData(jobDataMap).build();

    CronTrigger trigger = TriggerBuilder.newTrigger()
        .withIdentity("trigger1", "group1")
        .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
        .build();

    _scheduler.scheduleJob(job, trigger);
  }

  public void start() throws SchedulerException {
    _scheduler.start();
  }

  public void shutDown() throws SchedulerException {
    _scheduler.shutdown();
  }
}
