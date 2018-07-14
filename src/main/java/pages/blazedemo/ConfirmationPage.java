package pages.blazedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class ConfirmationPage extends BasePage {

    @FindBy(tagName = "h1")
    public WebElement title;

    @FindBy(xpath = "//td[contains(text(), 'Id')]/following-sibling::td")
    public WebElement orderId;

    @FindBy(xpath = "//td[contains(text(), 'Status')]/following-sibling::td")
    public WebElement orderStatus;

    @FindBy(xpath = "//td[contains(text(), 'Amount')]/following-sibling::td")
    public WebElement orderAmount;

    @FindBy(xpath = "//td[contains(text(), 'Card Number')]/following-sibling::td")
    public WebElement orderCardNumber;

    @FindBy(xpath = "//td[contains(text(), 'Expiration')]/following-sibling::td")
    public WebElement orderExpiration;

    @FindBy(xpath = "//td[contains(text(), 'Auth Code')]/following-sibling::td")
    public WebElement orderAuthCode;

    private String url = "/confirmation.php";

    @Step
    public boolean isInitialized() { return title.isDisplayed(); }

    @Step
    public String getUrl() {
        return url;
    }

    @Step
    public String expectedTextOnTitle() {
        return "Thank you for your purchase today!";
    }
}
