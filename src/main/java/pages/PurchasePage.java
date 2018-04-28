package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import entities.BankCard;
import entities.Location;
import entities.Person;

public class PurchasePage extends BasePage {

    @FindBy(tagName = "h2")
    public WebElement title;

    @FindBy(xpath = "//p[contains(text(), 'Flight Number')]")
    public WebElement flightNumber;

    @FindBy(xpath = "//p[contains(text(), 'Airline')]")
    public WebElement airlineCompany;

    @FindBy(xpath = "//p[contains(text(), 'Price')]")
    public WebElement priceCost;

    @FindBy(xpath = "//p[contains(text(), 'Arbitrary Fees and Taxes')]")
    public WebElement arbitraryFeesAndTaxes;

    @FindBy(xpath = "//p[contains(text(), 'Total Cost')]")
    public WebElement totalCost;

    @FindBy(id = "inputName")
    private WebElement nameInput;

    @FindBy(id = "address")
    private WebElement addressInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "zipCode")
    private WebElement zipCodeInput;

    @FindBy(id = "creditCardNumber")
    private WebElement creditCardNumberInput;

    @FindBy(id = "creditCardMonth")
    private WebElement creditCardMonthInput;

    @FindBy(id = "creditCardYear")
    private WebElement creditCardYearInput;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCardInput;

    @FindBy(id = "cardType")
    private WebElement cardTypeSelect;
    private Select cardType = new Select(cardTypeSelect);

    @FindBy(css = "input[type=submit]")
    public WebElement submitFormButton;

    private String url = "/purchase.php";

    public PurchasePage() { super(); }

    public String getUrl() {
        return url;
    }

    public boolean isInitialized() { return title.isDisplayed(); }

    public void fillUserDataInForm(Person user) {
        this.nameInput.clear();
        this.nameInput.sendKeys(user.fullName());
    }

    public void fillLocationDataInForm(Location location) {
        this.addressInput.clear();
        this.addressInput.sendKeys(location.getStreet());

        this.cityInput.clear();
        this.cityInput.sendKeys(location.getCity());

        this.stateInput.clear();
        this.stateInput.sendKeys(location.getState());

        this.zipCodeInput.clear();
        this.zipCodeInput.sendKeys(location.getZipCode());
    }

    public void fillCreditCardDataInForm(BankCard creditCard) {
        this.cardType.selectByValue(creditCard.getTypeOfCard());

        this.creditCardNumberInput.clear();
        this.creditCardNumberInput.sendKeys(creditCard.getCardNumber());

        this.creditCardMonthInput.clear();
        this.creditCardMonthInput.sendKeys(creditCard.getMonth());

        this.creditCardYearInput.clear();
        this.creditCardYearInput.sendKeys(creditCard.getYear());

        this.nameOnCardInput.clear();
        this.nameOnCardInput.sendKeys(creditCard.getNameOnCard());
    }

    public void fillPayForm(Person user, Location location, BankCard creditCard) {
        fillUserDataInForm(user);
        fillLocationDataInForm(location);
        fillCreditCardDataInForm(creditCard);
    }

    public ConfirmationPage submitForm() {
        this.submitFormButton.click();
        return new ConfirmationPage();
    }

    public String expectedTextOnTitle(String departureCity, String destinationCity) {
        return "Your flight from " + departureCity + " to " + destinationCity + " has been reserved.";
    }

}
