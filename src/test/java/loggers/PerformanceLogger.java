package loggers;

import data.CommonForTheSiteData;
import listeners.TestListener;
import org.apache.log4j.Logger;
import org.openqa.selenium.logging.LogEntry;
import utils.Driver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class PerformanceLogger {
    private static Logger logger = Logger.getLogger(TestListener.class);
    private static final String PERFORMANCE_LOG_DIRECTORY = "performanceLog\\";
    private static final String EXTENSION_OF_LOG = ".log";
    private static final String ENCODING = "UTF-8";

    public static void setLogForChrome(){
        if(Driver.currentDriverName().contains("chrome")){
            Driver.setLogEntries();
        }
    }

    public static void writeLogToFile(String fileName){
        String pathToFile = CommonForTheSiteData.LOG_DIRECTORY + PERFORMANCE_LOG_DIRECTORY + fileName + EXTENSION_OF_LOG;
        if (Driver.getLogEntries().getAll().size()>0){
            try {
                PrintWriter writer = new PrintWriter(pathToFile, ENCODING);
                for (LogEntry entry : Driver.getLogEntries()){
                    writer.println(entry.getLevel() + " " + entry.getMessage());
                }
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printLogForChrome(){
        if(Driver.currentDriverName().contains("chrome")){
            for (LogEntry entry : Driver.getLogEntries()){
                logger.info(entry.getLevel() + " " + entry.getMessage());
            }
        }
    }
}
