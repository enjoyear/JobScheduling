package chen.guo;

import chen.guo.example.SimpleScheduler;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;


public class Main {
  private static final Logger logger = Logger.getLogger(Main.class);
  private static final String KEY_CRON_EXPRESSION = "cron.expression";

  public static void main(String[] args) throws IOException, SchedulerException {

    Properties properties = new Properties();
    InputStream configureFile = Main.class.getResourceAsStream("/scheduler.properties");
    properties.load(configureFile);
    SimpleScheduler simpleScheduler = new SimpleScheduler(properties.getProperty(KEY_CRON_EXPRESSION));

    logger.info("Start scheduling...");
    simpleScheduler.start();
    //Thread.sleep(10 * 1000L);
    //simpleScheduler.shutDown();
  }
}
