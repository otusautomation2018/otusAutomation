package pages.automationpractice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class MainPage extends BasePage {

    @FindBy(css = "#editorial_block_center h1")
    public WebElement h1OfexpectedText;

    private String url = "/index.php";


    public boolean isInitialized() { return h1OfexpectedText.isDisplayed(); }

    public String getUrl() {
        return url;
    }

    public String expectedTextOnPage() {
        return "Automation Practice Website";
    }

}
