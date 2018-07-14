package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.util.logging.Level;

public class Driver {

    public static String driverName;
    private static LogEntries logEntries;
    private static WebDriver driver;
    private static final boolean DISPLAY = Boolean.valueOf(System.getProperty("display"));

    public static LogEntries getLogEntries() {
        return logEntries;
    }

    public static void setLogEntries() {
        Driver.logEntries = driver.manage().logs().get(LogType.PERFORMANCE);
    }

    public static WebDriver createFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        return driver;
    }

    public static WebDriver createOperaDriver() {
        WebDriverManager.operadriver().setup();
        driver = new OperaDriver();
        return driver;
    }

    public static WebDriver createChromeDriver() {
//        WebDriverManager.chromedriver().setup();
        if(DISPLAY) {

            System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
            DesiredCapabilities caps = DesiredCapabilities.chrome();
            LoggingPreferences logs = new LoggingPreferences();
            logs.enable(LogType.PERFORMANCE, Level.INFO);
            caps.setCapability(CapabilityType.LOGGING_PREFS, logs);

            ChromeOptions options = new ChromeOptions();
            options.merge(caps);
            driver = new ChromeDriver(options);
            return driver;
        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
            return driver;
        }
    }

    public static WebDriver createSafariDriver() {
        return new SafariDriver();
    }

    private static WebDriver getDriver() {
        driverName = System.getProperty("browser");
        if(driverName == null) {
            driverName = PropertyReader.
                    getPropertyFromFile(
                            "properties/settings.properties",
                            "browser");
        }
        if (driverName == null) driverName = "chrome";
        switch (driverName){
            case "chrome": return createChromeDriver();
            case "firefox": return createFireFoxDriver();
            case "opera": return createOperaDriver();
            case "safari": return createSafariDriver();
            default: return createChromeDriver();
        }
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            driver = Driver.getDriver();
        }
        return driver;
    }

    public static String currentDriverName(){
        return Driver.getInstance().getClass().toString().toLowerCase();
    }
}
