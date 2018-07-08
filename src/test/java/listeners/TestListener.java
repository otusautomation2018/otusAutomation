package listeners;

import loggers.PerformanceLogger;
import org.apache.log4j.Logger;
import org.openqa.selenium.logging.LogEntry;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Driver;
import utils.ScreenShoter;

import java.util.Date;

public class TestListener implements ITestListener {

    private Logger logger = Logger.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info("Test " + iTestResult.getName() + " run.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
//        PerformanceLogger.writeLogToFile(getFileName(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.error(iTestResult.getName(), iTestResult.getThrowable());
        ScreenShoter.makeAScreenshot(getFileName(iTestResult));
        PerformanceLogger.setLogForChrome();
        PerformanceLogger.writeLogToFile(getFileName(iTestResult));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private String getFileName(ITestResult iTestResult){
        long mills = new Date(iTestResult.getEndMillis()).getTime();
        return iTestResult.getName() + mills;
    }


}
