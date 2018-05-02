package tests.automationpractice;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.automationpractice.MainPage;
import tests.BaseTest;
import utils.PropertyReader;
import utils.helpers.ActionsHelpers;
import utils.helpers.WaitingsHelpers;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ExpectedConditionsTest extends BaseTest {

    private String baseUrl = PropertyReader
            .getPropertyFromFile("properties/automationpractice.properties", "baseUrl");

    private final String tShirtsPageTitle = "T-shirts - My Store";

    private MainPage mainPage = new MainPage();

    @Test
    public void test() {
        driver.get(baseUrl);
        WaitingsHelpers.waitForLoadPageByTextOnPage(mainPage.h1OfexpectedText, mainPage.expectedTextOnPage());

        assertEquals(driver.getCurrentUrl(), baseUrl + mainPage.getUrl());
        assertTrue(mainPage.isInitialized());

        ActionsHelpers.focusOnElement(mainPage.topMenuBlock.firstCategory, mainPage.topMenuBlock.subMenu);

        mainPage.topMenuBlock.tShirtsBtn.click();
        webDriverWait.until(ExpectedConditions.titleIs(tShirtsPageTitle));

        assertEquals(driver.getTitle(), tShirtsPageTitle);
    }
}
