package tests.automationpractice;


import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import pages.automationpractice.MainPage;
import tests.BaseTest;
import utils.PropertyReader;
import utils.helpers.ActionsHelpers;
import utils.helpers.WaitingsHelpers;

public class PropertiesOfElementsTest extends BaseTest {

    private String baseUrl = PropertyReader
            .getPropertyFromFile("properties/automationpractice.properties", "baseUrl");

    private MainPage mainPage = new MainPage();

    @Test
    public void test() {
        String colorPattern = "rgba?\\(51, 51, 51(, 1)?\\)";

        driver.get(baseUrl);

        WaitingsHelpers.waitForLoadPageByTextOnPage(mainPage.h1OfexpectedText, mainPage.expectedTextOnPage());
        assertEquals(driver.getCurrentUrl(), baseUrl + mainPage.getUrl());
        assertTrue(mainPage.isInitialized());

        Dimension beforeFocusSizeFirstCategory = mainPage.topMenuBlock.firstCategory.getSize();

        ActionsHelpers.focusOnElement(mainPage.topMenuBlock.firstCategory);

        Dimension afterFocusSizeFirstCategory = mainPage.topMenuBlock.firstCategory.getSize();
        String afterFocusBackgroundColorFirstCategory = mainPage.topMenuBlock.firstCategory.getCssValue("background-color");

        assertEquals(beforeFocusSizeFirstCategory, afterFocusSizeFirstCategory);
        assertTrue(afterFocusBackgroundColorFirstCategory.matches(colorPattern));
    }
}
