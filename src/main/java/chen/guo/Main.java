package chen.guo;

import chen.guo.scheduling.SimpleScheduler;
import chen.guo.scheduling.jobs.GeneralJob;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import java.io.File;
import java.util.Map;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.impl.JobExecutionContextImpl;


public class Main {
  public static final String KEY_CRON_EXPRESSION = "cron.expression";
  public static final String KEY_JOB_CLASS = "job.class";
  public static final String KEY_JOB_ARGUMENTS = "job.args";

  private static final Logger logger = Logger.getLogger(Main.class);

  @Parameter(names = {"--job"}, description = "Set a single scheduled job with a property file", required = true, converter = FileConverter.class)
  private File jobFile = null;

  @Parameter(names = {"-h", "-?", "-help", "--help"}, help = true, hidden = true)
  private boolean help = false;

  public static void main(String[] args) throws Exception {
    Main main = new Main();
    JCommander jCommander = new JCommander(main);
    jCommander.setProgramName(main.getClass().getName());

    jCommander.parse(args);
    if (main.help) {
      jCommander.usage();
      System.exit(0);
    }

    run(new FileInputStream(main.jobFile));
  }

  private static void run(FileInputStream fileInputStream)
      throws IOException, ClassNotFoundException, SchedulerException, NoSuchMethodException, IllegalAccessException,
             InvocationTargetException, InstantiationException {
    Properties properties = new Properties();
    properties.load(fileInputStream);

    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put(KEY_JOB_ARGUMENTS, properties.getProperty(KEY_JOB_ARGUMENTS));

    String cronExpression = properties.getProperty(KEY_CRON_EXPRESSION);
    if (cronExpression == null || cronExpression.trim().isEmpty()) {
      runOnce(properties, jobDataMap);
    } else {
      runAsScheduled(properties, jobDataMap);
    }
  }

  private static void runAsScheduled(Properties properties, JobDataMap jobDataMap)
      throws ClassNotFoundException, SchedulerException {
    Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(properties.getProperty(KEY_JOB_CLASS));
    SimpleScheduler simpleScheduler =
        new SimpleScheduler(jobClass, properties.getProperty(KEY_CRON_EXPRESSION), jobDataMap);
    logger.info("Start scheduling...");
    simpleScheduler.start();
    //Thread.sleep(10 * 1000L);
    //simpleScheduler.shutDown();
  }

  private static void runOnce(Properties properties, JobDataMap jobDataMap)
      throws NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException,
             InvocationTargetException, JobExecutionException {
    Constructor<?> ctor = Class.forName(properties.getProperty(KEY_JOB_CLASS)).getDeclaredConstructor(Map.class);
    GeneralJob job = (GeneralJob) ctor.newInstance(jobDataMap);
    job.executeOnce();
  }
}
