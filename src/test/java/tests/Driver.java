package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
    public WebDriver createFireFoxDriver() {
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

    public WebDriver createOperaDriver() {
        OperaDriver driver = new OperaDriver();
        return driver;
    }

    public WebDriver createChromeDriver() {
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public WebDriver createSafariDriver() {
        WebDriver driver = new SafariDriver();
        return driver;
    }

    public WebDriver getDriver(String value) {
        if (value == null) value = "chrome";
        switch (value){
            case "chrome": return createChromeDriver();
            case "firefox": return createFireFoxDriver();
            case "opera": return createOperaDriver();
            case "safari": return createSafariDriver();
            default: return createChromeDriver();
        }
    }
}
