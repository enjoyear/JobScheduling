package chen.guo.example;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class SimpleScheduler {

  private final String _cronExpression;
  private final org.quartz.Scheduler _scheduler;

  public SimpleScheduler(String cronExpression) throws SchedulerException {
    _cronExpression = cronExpression;
    SchedulerFactory sf = new StdSchedulerFactory();
    _scheduler = sf.getScheduler();

    JobDetail job = JobBuilder.newJob(BashJob.class).withIdentity("job1", "group1").build();
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
