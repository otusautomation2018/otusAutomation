package tests;

import listeners.EventListener;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.Driver;
import utils.PropertyReader;
import utils.helpers.WaitingsHelpers;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

@Listeners(TestListener.class)
public class BaseTest {

    public int baseTimeout = Integer.parseInt(PropertyReader.
            getPropertyFromFile("properties/settings.properties", "timeout"));

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    @BeforeTest
    public void beforeClass(){
        driver = Driver.getInstance();
        driver.manage().timeouts().implicitlyWait(baseTimeout, TimeUnit.SECONDS);
        webDriverWait = WaitingsHelpers.getInstanceWebDriverWait();
//
//        //            ChromeOptions options = new ChromeOptions();
//        DesiredCapabilities caps = DesiredCapabilities.chrome();
//        LoggingPreferences logs = new LoggingPreferences();
//        logs.enable(LogType.PERFORMANCE, Level.INFO);
//        caps.setCapability(CapabilityType.LOGGING_PREFS, logs);
//
//        driver = new ChromeDriver(caps);
//        LogEntries logEntries = driver.manage().logs().get(LogType.PERFORMANCE);
//        for (LogEntry entry : logEntries){
//            System.out.println(entry.getLevel() + " " + entry.getMessage());
//        }
    }

    @AfterSuite
    public void afterClass() {
        driver.quit();
    }
}
