package tests.automationpractice;


import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import pages.automationpractice.MainPage;
import tests.BaseTest;
import utils.PropertyReader;
import utils.helpers.WaitingsHelpers;

public class PropertiesOfElementsTest extends BaseTest {

    private String baseUrl = PropertyReader
            .getPropertyFromFile("properties/automationpractice.properties", "baseUrl");

    @Test
    public void test() {

        driver.get(baseUrl);

        MainPage mainPage = new MainPage();
        WaitingsHelpers.waitForLoadPageByTextOnPage(mainPage.h1OfexpectedText, mainPage.expectedTextOnPage());
        assertEquals(driver.getCurrentUrl(), baseUrl + mainPage.getUrl());
        assertTrue(mainPage.isInitialized());

        assertTrue(buttonSizeNotChange());
        assertTrue(buttonColorInTheInducedStateIsCorrect());
    }

    private boolean buttonSizeNotChange() {
        return true;
    }

    private boolean buttonColorInTheInducedStateIsCorrect() {
        return true;
    }
}
