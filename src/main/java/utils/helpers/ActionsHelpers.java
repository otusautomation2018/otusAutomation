package utils.helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Driver;

public class ActionsHelpers {

    @Deprecated
    private static final int PAUSE = 500;

    private static Actions action = ActionsHelpers.getInstanceAction();

    public static Actions getInstanceAction() {
        if( action == null) {
            action = new Actions(Driver.getInstance());
        }
        return action;
    }

    public static void focusOnElement(WebElement focusElement, WebElement visibilityOfElement) {
        action
                .moveToElement(focusElement)
                .build()
                .perform();
        WaitingsHelpers.getInstanceWebDriverWait().until(ExpectedConditions.visibilityOf(visibilityOfElement));
    }
}
