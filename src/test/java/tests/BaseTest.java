package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.Driver;
import utils.PropertyReader;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public String baseUrl = PropertyReader.
            getPropertyFromFile("properties/settings.properties", "baseUrl");
    public int baseTimeout = Integer.parseInt(PropertyReader.
            getPropertyFromFile("properties/settings.properties", "timeout"));
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    @BeforeClass
    public void beforeClass(){
        driver = Driver.getInstance();
        driver.manage().timeouts().implicitlyWait(baseTimeout, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, baseTimeout);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
