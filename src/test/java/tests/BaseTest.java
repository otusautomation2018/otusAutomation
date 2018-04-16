package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    public String baseUrl = "http://blazedemo.com";
    int baseTimeout = 5;

    @BeforeClass
    public void beforeClass(){
        driver = new Driver().getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(baseTimeout, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, baseTimeout);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
