package utils.helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;
import utils.PropertyReader;

public class WaitingsHelpers {

    private static int baseTimeout = Integer.parseInt(PropertyReader.
            getPropertyFromFile("properties/settings.properties", "timeout"));

    private static WebDriverWait webDriverWait;

    public static WebDriverWait getInstanceWebDriverWait() {
        if (webDriverWait == null) {
            webDriverWait = new WebDriverWait(Driver.getInstance(), baseTimeout);
        }
        return webDriverWait;
    }
    public static void waitForLoadPageByTextOnPage(WebElement expectedElement, String expectedText){
        getInstanceWebDriverWait().until(
                ExpectedConditions.textToBePresentInElement(expectedElement, expectedText));
    }
}
