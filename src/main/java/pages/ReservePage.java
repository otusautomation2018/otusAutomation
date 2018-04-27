package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import structures.Flight;
import utils.Driver;
import utils.helpers.DataHelpers;

import java.util.ArrayList;

public class ReservePage extends BasePage {

    @FindBy(tagName = "h3")
    public WebElement title;


    private ArrayList<WebElement> flightsList = new ArrayList<>(Driver.
        getInstance().
        findElements(By.cssSelector("table.table tbody tr")));
    private WebElement flightElement = flightsList.get(DataHelpers.random(0, flightsList.size() - 1));
    private WebElement flightNumber = flightElement.findElement(By.cssSelector("input[name=flight]"));
    private WebElement flightPrice = flightElement.findElement(By.cssSelector("input[name=price]"));
    private WebElement flightAirline = flightElement.findElement(By.cssSelector("input[name=airline]"));

    private WebElement choiseFlight = flightElement.findElement(By.cssSelector("td input[type=submit]"));

    private String url = "/reserve.php";

    public ReservePage() { super(); }

    public boolean isInitialized() { return title.isDisplayed(); }

    public String getUrl() {
        return url;
    }

    public void fillInTheFlightInformation(Flight flight) {
        flight.setNumber(flightNumber.getAttribute("value"));
        flight.setPrice(Float.parseFloat(flightPrice.getAttribute("value")));
        flight.setAirline(flightAirline.getAttribute("value"));
    }

    public PurchasePage choiseFlight() {
        this.choiseFlight.click();
        return new PurchasePage();
    }

    public String expectedTextOnTitle(String departureCity, String destinationCity) {
        return "Flights from " + departureCity + " to " + destinationCity + ":";
    }
}
