package pages.blazedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

public class MainPage extends BasePage {

    @FindBy(css = "[name='fromPort']")
    private WebElement selectElementDepartureCity;
    private Select selectDepartureCity = new Select(selectElementDepartureCity);

    @FindBy(css = "[name='toPort']")
    private WebElement selectElementDestinationCity;
    private Select selectDestinationCity = new Select(selectElementDestinationCity);

    @FindBy(css = "[type='submit']")
    private WebElement submitFormButton;

    public MainPage() { super(); }

    @Step
    public boolean isInitialized() { return selectElementDepartureCity.isDisplayed(); }

    @Step
    public void fillChoiseCitiesForm(String valueDepartureCity, String valueDestinationCity) {
        this.selectDepartureCity.selectByValue(valueDepartureCity);
        this.selectDestinationCity.selectByValue(valueDestinationCity);
    }

    @Step
    public ReservePage submitForm() {
        this.submitFormButton.click();
        return new ReservePage();
    }
}
