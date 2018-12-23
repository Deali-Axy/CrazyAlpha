package temp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4JTest {
    public static void main(String... args) {
        Logger logger = LogManager.getLogger(Log4JTest.class);
        logger.debug("hello world");
        logger.error("hello world");
        logger.info("hello");
    }
}
