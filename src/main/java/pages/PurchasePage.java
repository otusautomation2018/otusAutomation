package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PurchasePage extends BasePage {

    @FindBy(tagName = "h2")
    public WebElement title;

    public PurchasePage() { super(); }

    public boolean isInitialized() { return title.isDisplayed(); }
}
