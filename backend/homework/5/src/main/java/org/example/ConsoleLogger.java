package org.example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ConsoleLogger {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleLogger.class);

    public static void warnMethod(String logString){
        logger.warn(logString);
    }
    public static void errorMethod(String logString){
        logger.error(logString);
    }
    public static void infoMethod(String logString){
        logger.info(logString);
    }
    public static void debugMethod(String logString){
        logger.debug(logString);
    }

}
