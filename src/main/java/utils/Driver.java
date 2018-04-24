package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {

    public static String driverName;

    public WebDriver createFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    public WebDriver createOperaDriver() {
        WebDriverManager.operadriver().setup();
        return new OperaDriver();
    }

    public WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    public WebDriver createSafariDriver() {
        return new SafariDriver();
    }

    public WebDriver getDriver() {
        driverName = PropertyReader.getPropertyFromFile("properties/settings.properties", "webDriver");
        if (driverName == null) driverName = "chrome";
        switch (driverName){
            case "chrome": return createChromeDriver();
            case "firefox": return createFireFoxDriver();
            case "opera": return createOperaDriver();
            case "safari": return createSafariDriver();
            default: return createChromeDriver();
        }
    }
}
