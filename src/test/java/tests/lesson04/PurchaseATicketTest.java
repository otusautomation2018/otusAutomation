package tests.lesson04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import structures.BankCard;
import structures.Flight;
import structures.Location;
import structures.Person;
import tests.BaseTest;
import utils.helpers.DataHelpers;

import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PurchaseATicketTest extends BaseTest {

    @Test
    public void test() {

        Person user = new Person();
        user.firstName = DataHelpers.getRandomArrayItem(DataHelpers.getFirstnames());
        user.lastName = DataHelpers.getRandomArrayItem(DataHelpers.getLastnames());

        Location address = new Location();
        address.city = DataHelpers.getRandomArrayItem(DataHelpers.getCities());
        address.street = DataHelpers.getRandomArrayItem(DataHelpers.getStreets());
        address.state = DataHelpers.getRandomArrayItem(DataHelpers.getStates());
        address.zipCode = DataHelpers.generateZipCode();

        GregorianCalendar cal = new GregorianCalendar();
        int yearMin = cal.get(Calendar.YEAR);
        int yearMax = yearMin + 5;

        BankCard bankCard = new BankCard();
        bankCard.typeOfCard = DataHelpers.getRandomArrayItem(DataHelpers.getTypesOfCards());
        bankCard.cardNumber = DataHelpers.generateCardNumber();
        bankCard.month = String.valueOf(DataHelpers.random(1, 12));
        bankCard.year = String.valueOf(DataHelpers.random(yearMin, yearMax));
        bankCard.nameOnCard = user.fullName();

        Flight flight = new Flight();

        String textOnHeadingOnReservePage = "Flights from " + flight.departureCity() + " to " + flight.destinationCity() + ":";
        String textOnHeadingOnPurchasePage = "Your flight from " + flight.departureCity() + " to " + flight.destinationCity() + " has been reserved.";
        String textOnHeadingOnConfirmationPage = "Thank you for your purchase today!";

        driver.get(baseUrl);

        fillAndSubmitChoiseCitiesForm(flight.departureCity(), flight.destinationCity());
        reservePageIsDispalyed(textOnHeadingOnReservePage);

//        Reserve Page
//        находим все предложения перелетов
        ArrayList<WebElement> flights = new ArrayList<>(driver.findElements(By.cssSelector("table.table tbody tr")));
//       случайно выбираем перелет
        WebElement flightElement = flights.get(DataHelpers.random(0, flights.size() - 1));

//        создаем объект, который будет хранить информацию о выбранном перелете
        fillInTheFlightInformation(flightElement, flight);
        flightElement.findElement(By.cssSelector("td input[type=submit]")).click();
        purchasePageIsDisplayed(textOnHeadingOnPurchasePage);


////        Purchase Page
        WebElement pFlightNumberElement = driver.
                findElement(By.xpath("//p[contains(text(), 'Flight Number')]"));
        WebElement pAirlineCompanyElement = driver.
                findElement(By.xpath("//p[contains(text(), 'Airline')]"));
        WebElement pPriceCostElement = driver.
                findElement(By.xpath("//p[contains(text(), 'Price')]"));
        WebElement pArbitraryFeesAndTaxesElement = driver.
                findElement(By.xpath("//p[contains(text(), 'Arbitrary Fees and Taxes')]"));
        WebElement pTotalCostElement = driver.
                findElement(By.xpath("//p[contains(text(), 'Total Cost')]"));

        assertEquals(pFlightNumberElement.getText(), "Flight Number: " + flight.getNumber());
        assertEquals(pAirlineCompanyElement.getText(), "Airline: " + flight.getAirline());

        float priceCostValue = Float.parseFloat(pPriceCostElement.getText().
                replace("Price: ", ""));
        float arbitraryFeesAndTaxes = Float.parseFloat(pArbitraryFeesAndTaxesElement.getText().
                replace("Arbitrary Fees and Taxes: ",""));

        float totalCostResult =
                priceCostValue + arbitraryFeesAndTaxes;
        float totalCostValue = Float.
                parseFloat(pTotalCostElement.
                        getText().
                        replace("Total Cost: ",""));


//        цена на странице Purchare равна той, что мы выбрали на странице Reserve
        assertEquals(priceCostValue, flight.price);
//        отображаемая итоговая цена является суммой цены и сборов
        assertEquals(totalCostValue, totalCostResult);


        fillAndSubmitUserDataForm(user, address, bankCard);
        confirmationPageIsDisplayed(textOnHeadingOnConfirmationPage);

////        confirmation Page
        WebElement tdOrderId = driver.
                findElement(By.xpath("//td[contains(text(), 'Id')]/following-sibling::td"));
        WebElement tdOrderStatus = driver.
                findElement(By.xpath("//td[contains(text(), 'Status')]/following-sibling::td"));
        WebElement tdOrderAmount = driver.
                findElement(By.xpath("//td[contains(text(), 'Amount')]/following-sibling::td"));
        WebElement tdOrderCardNumber = driver.
                findElement(By.xpath("//td[contains(text(), 'Card Number')]/following-sibling::td"));
        WebElement tdOrderExpiration = driver.
                findElement(By.xpath("//td[contains(text(), 'Expiration')]/following-sibling::td"));
        WebElement tdOrderAuthCode = driver.
                findElement(By.xpath("//td[contains(text(), 'Auth Code')]/following-sibling::td"));
//        WebElement tdOrderDate = driver.findElement(By.xpath("//td[contains(text(), 'Date')]/following-sibling::td"));

        assertTrue(tdOrderId.isDisplayed());
        assertTrue(tdOrderStatus.isDisplayed());

        assertTrue(tdOrderAmount.isDisplayed());
        assertEquals(tdOrderAmount.getText(), "USD");

//        проверяем соответствие последних 4 знаков
        assertEquals(tdOrderCardNumber.getText().substring(tdOrderCardNumber.getText().length()-4),
                bankCard.cardNumber.substring(bankCard.cardNumber.length()-4));

        assertTrue(tdOrderExpiration.isDisplayed());
        assertEquals(tdOrderExpiration.getText(), bankCard.month + " /" + bankCard.year);

        assertTrue(tdOrderAuthCode.isDisplayed());
    }

    public void selectDepartureCity(String value) {
        WebElement selectElementDepartureCity = driver.findElement(By.cssSelector("[name='fromPort']"));
        Select selectDepartureCity = new Select(selectElementDepartureCity);
        selectDepartureCity.selectByValue(value);
    }

    public void selectDestinationCity(String value) {
        WebElement selectElementDestinationCity = driver.findElement(By.cssSelector("[name='toPort']"));
        Select selectDestinationCity = new Select(selectElementDestinationCity);
        selectDestinationCity.selectByValue(value);
    }

    public void fillAndSubmitChoiseCitiesForm(String valueDepartureCity, String valueDestinationCity){
        selectDepartureCity(valueDepartureCity);
        selectDestinationCity(valueDestinationCity);
        driver.findElement(By.cssSelector("[type='submit']")).click();
    }

    public void waitForLoadPageByTextOnPage(WebElement expectedElement, String expectedText){
        webDriverWait.until(
                ExpectedConditions.textToBePresentInElement(expectedElement, expectedText));
    }
    
    public void reservePageIsDispalyed(String expectedText){
        WebElement titleReservePage = driver.findElement(By.tagName("h3"));
        waitForLoadPageByTextOnPage(titleReservePage, expectedText);
        assertTrue(titleReservePage.isDisplayed());
    }
    public void purchasePageIsDisplayed(String expectedText) {
        WebElement titlePurchasePage = driver.findElement(By.tagName("h2"));
        waitForLoadPageByTextOnPage(titlePurchasePage, expectedText);
        assertTrue(titlePurchasePage.isDisplayed());
    }

    public void confirmationPageIsDisplayed(String expectedText) {
        String expectedUrl = baseUrl + "/confirmation.php";
        WebElement titleConfirmationPage = driver.findElement(By.tagName("h1"));

        waitForLoadPageByTextOnPage(titleConfirmationPage, expectedText);

        assertTrue(titleConfirmationPage.isDisplayed());
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    public void fillInTheFlightInformation(WebElement flightElement, Flight flight) {
        flight.setNumber(flightElement.findElement(By.cssSelector("input[name=flight]")).getAttribute("value"));
        flight.setPrice(Float.parseFloat(flightElement.
                findElement(
                        By.cssSelector("input[name=price]")).
                getAttribute("value")));
        flight.setAirline(flightElement.findElement(By.cssSelector("input[name=airline]")).getAttribute("value"));
    }

    public void fillAndSubmitUserDataForm(Person user, Location address, BankCard bankCard) {
        WebElement iName = driver.findElement(By.id("inputName"));
        iName.clear();
        iName.sendKeys(user.fullName());

        WebElement iAddress = driver.findElement(By.id("address"));
        iAddress.clear();
        iAddress.sendKeys(address.street);

        WebElement iCity = driver.findElement(By.id("city"));
        iCity.clear();
        iCity.sendKeys(address.city);

        WebElement iState = driver.findElement(By.id("state"));
        iState.clear();
        iState.sendKeys(address.state);

        WebElement iZipCode = driver.findElement(By.id("zipCode"));
        iZipCode.clear();
        iZipCode.sendKeys(address.zipCode);

        WebElement selectElementCardType = driver.findElement(By.id("cardType"));
        Select selectCardType = new Select(selectElementCardType);
        selectCardType.selectByValue(bankCard.typeOfCard);

        WebElement iCreditCardNumber = driver.findElement(By.id("creditCardNumber"));
        iCreditCardNumber.clear();
        iCreditCardNumber.sendKeys(bankCard.cardNumber);

        WebElement iCreditCardMonth = driver.findElement(By.id("creditCardMonth"));
        iCreditCardMonth.clear();
        iCreditCardMonth.sendKeys(bankCard.month);

        WebElement iCreditCardYear = driver.findElement(By.id("creditCardYear"));
        iCreditCardYear.clear();
        iCreditCardYear.sendKeys(bankCard.year);

        WebElement iNameOnBankCard = driver.findElement(By.id("nameOnCard"));
        iNameOnBankCard.clear();
        iNameOnBankCard.sendKeys(bankCard.nameOnCard);

        driver.findElement(By.cssSelector("input[type=submit]")).click();
    }
}
