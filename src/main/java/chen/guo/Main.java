package chen.guo;

import chen.guo.example.SimpleScheduler;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;


public class Main {
  private static final Logger logger = Logger.getLogger(Main.class);
  private static final String KEY_CRON_EXPRESSION = "cron.expression";
  private static final String KEY_JOB_CLASS = "job.class";
  private static final String KEY_JOB_ARGUMENTS = "job.args";

  public static final String FILE_LOCATION = "fileLocation";

  public static void main(String[] args) throws Exception {

    Properties properties = new Properties();
    InputStream configureFile = Main.class.getResourceAsStream("/scheduler.properties");
    properties.load(configureFile);

    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put(FILE_LOCATION, properties.getProperty(KEY_JOB_ARGUMENTS));

    Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(properties.getProperty(KEY_JOB_CLASS));

//    Constructor<?> ctor = jobClass.getDeclaredConstructor(String.class);
//    ctor.newInstance(properties.getProperty(KEY_JOB_ARGUMENTS));

    SimpleScheduler simpleScheduler =
        new SimpleScheduler(jobClass, properties.getProperty(KEY_CRON_EXPRESSION), jobDataMap);

    logger.info("Start scheduling...");
    simpleScheduler.start();
    //Thread.sleep(10 * 1000L);
    //simpleScheduler.shutDown();
  }
}
