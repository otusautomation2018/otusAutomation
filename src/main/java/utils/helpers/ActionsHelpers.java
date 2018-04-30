package utils.helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.Driver;

public class ActionsHelpers {

    private static final int PAUSE = 500;
    private static Actions action = ActionsHelpers.getInstanceAction();

    public static Actions getInstanceAction() {
        if( action == null) {
            action = new Actions(Driver.getInstance());
        }
        return action;
    }

    public static void focusOnElement(WebElement element) {
        action
                .moveToElement(element)
                .pause(PAUSE)
                .build()
                .perform();
    }
}
