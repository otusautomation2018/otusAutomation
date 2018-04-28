package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import entities.Flight;
import utils.Driver;

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

    public WebElement findElementWithMinimumPrice(){
        float min = 0;
        WebElement res = null;

        for(int i = 0; i < flightsList.size(); i++) {
            WebElement currentElem = flightsList.get(i);
            float currentPrice = flightPriceFloat(currentElem);

            if(i == 0) {
                min = currentPrice;
                res = currentElem;
            } else if (min > currentPrice) {
                min = currentPrice;
                res = currentElem;
            }
        }
        return res;
    }

    private Float flightPriceFloat(WebElement flightElement) {
        flightPrice = flightElement.findElement(By.cssSelector("input[name=price]"));
        return Float.parseFloat(flightPrice.getAttribute("value"));
    }
}
