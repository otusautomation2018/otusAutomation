package pages.blazedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import entities.Flight;
import pages.BasePage;
import utils.Driver;
import utils.helpers.DataHelpers;

import java.util.ArrayList;

public class ReservePage extends BasePage {

    @FindBy(tagName = "h3")
    public WebElement title;

    private ArrayList<WebElement> flightsList = new ArrayList<>(Driver.
        getInstance().
        findElements(By.cssSelector("table.table tbody tr")));
    private WebElement flightElement = findElementWithMinimumPrice();
    private WebElement flightNumber = flightElement.findElement(By.cssSelector("input[name=flight]"));
    private WebElement flightPrice = flightElement.findElement(By.cssSelector("input[name=price]"));
    private WebElement flightAirline = flightElement.findElement(By.cssSelector("input[name=airline]"));

    private WebElement choiseFlight = flightElement.findElement(By.cssSelector("td input[type=submit]"));

    private String url = "/reserve.php";

    public ReservePage() { super(); }

    @Step
    public boolean isInitialized() { return title.isDisplayed(); }

    @Step
    public String getUrl() {
        return url;
    }

    @Step
    public void fillInTheFlightInformation(Flight flight) {
        flight.setNumber(flightNumber.getAttribute("value"));
        float price = Float.parseFloat(flightPrice.getAttribute("value"));
        flight.setPrice(DataHelpers.discardUpTo2DecimalPlaces(price));
        flight.setAirline(flightAirline.getAttribute("value"));
    }

    @Step
    public PurchasePage choiseFlight() {
        this.choiseFlight.click();
        return new PurchasePage();
    }

    @Step
    public String expectedTextOnTitle(String departureCity, String destinationCity) {
        return "Flights from " + departureCity + " to " + destinationCity + ":";
    }

    @Step
    public WebElement findElementWithMinimumPrice(){
        float min = 0;
        WebElement flightWithMinimumPrice = null;

        for(int i = 0; i < flightsList.size(); i++) {
            WebElement currentElem = flightsList.get(i);
            float currentPrice = flightPriceByElem(currentElem);

            if(i == 0) {
                min = currentPrice;
                flightWithMinimumPrice = currentElem;
            } else if (min > currentPrice) {
                min = currentPrice;
                flightWithMinimumPrice = currentElem;
            }
        }
        return flightWithMinimumPrice;
    }

    @Step
    private Float flightPriceByElem(WebElement flightElement) {
        WebElement flightPrice = flightElement.findElement(By.cssSelector("input[name=price]"));
        return Float.parseFloat(flightPrice.getAttribute("value"));
    }
}
