package tests.lesson04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.PurchasePage;
import pages.ReservePage;
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

        Person user = new Person.PersonBuilder().createPerson();
        Location address = new Location.LocationBuilder().createLocation();
        BankCard bankCard = new BankCard.BankCardBuilder(user.fullName()).createBankCard();

        Flight flight = new Flight();

        String textOnHeadingOnReservePage = "Flights from " + flight.departureCity() + " to " + flight.destinationCity() + ":";
        String textOnHeadingOnPurchasePage = "Your flight from " + flight.departureCity() + " to " + flight.destinationCity() + " has been reserved.";
        String textOnHeadingOnConfirmationPage = "Thank you for your purchase today!";

        driver.get(baseUrl);

        MainPage homePage = new MainPage();
        assertTrue(homePage.isInitialized());

        homePage.fillChoiseCitiesForm(flight.departureCity(), flight.destinationCity());

        ReservePage reservePage = homePage.submitForm();
        waitForLoadPageByTextOnPage(reservePage.title, textOnHeadingOnReservePage);
        assertTrue(reservePage.isInitialized());

        reservePage.fillInTheFlightInformation(flight);
        PurchasePage purchasePage = reservePage.choiseFlight();
        waitForLoadPageByTextOnPage(purchasePage.title, textOnHeadingOnPurchasePage);
        assertTrue(purchasePage.isInitialized());

//        System.out.println("================================");
//        System.out.println("================================");
//        System.out.println("++++++++++++++++++++++++++++++++");
//        System.out.println(flight.number);
//        System.out.println(flight.airline);
//        System.out.println(flight.price);
//        System.out.println("++++++++++++++++++++++++++++++++");
//        System.out.println("================================");
//        System.out.println("================================");

//        fillAndSubmitChoiseCitiesForm(flight.departureCity(), flight.destinationCity());
//        reservePageIsDispalyed(textOnHeadingOnReservePage);

//        Reserve Page
//        находим все предложения перелетов
//        ArrayList<WebElement> flightsList = new ArrayList<>(driver.findElements(By.cssSelector("table.table tbody tr")));
//       случайно выбираем перелет
//        WebElement flightElement = flightsList.get(DataHelpers.random(0, flightsList.size() - 1));

//        fillInTheFlightInformation(flightElement, flight);
//        flightElement.findElement(By.cssSelector("td input[type=submit]")).click();
//        purchasePageIsDisplayed(textOnHeadingOnPurchasePage);


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
                bankCard.getCardNumber().substring(bankCard.getCardNumber().length()-4));

        assertTrue(tdOrderExpiration.isDisplayed());
        assertEquals(tdOrderExpiration.getText(), bankCard.getMonth() + " /" + bankCard.getYear());

        assertTrue(tdOrderAuthCode.isDisplayed());
    }

//    public void selectDepartureCity(String value) {
//        WebElement selectElementDepartureCity = driver.findElement(By.cssSelector("[name='fromPort']"));
//        Select selectDepartureCity = new Select(selectElementDepartureCity);
//        selectDepartureCity.selectByValue(value);
//    }
//
//    public void selectDestinationCity(String value) {
//        WebElement selectElementDestinationCity = driver.findElement(By.cssSelector("[name='toPort']"));
//        Select selectDestinationCity = new Select(selectElementDestinationCity);
//        selectDestinationCity.selectByValue(value);
//    }

//    public void fillAndSubmitChoiseCitiesForm(String valueDepartureCity, String valueDestinationCity){
//        selectDepartureCity(valueDepartureCity);
//        selectDestinationCity(valueDestinationCity);
//        driver.findElement(By.cssSelector("[type='submit']")).click();
//    }

    public void waitForLoadPageByTextOnPage(WebElement expectedElement, String expectedText){
        webDriverWait.until(
                ExpectedConditions.textToBePresentInElement(expectedElement, expectedText));
    }
    
//    public void reservePageIsDispalyed(String expectedText){
//        WebElement titleReservePage = driver.findElement(By.tagName("h3"));
//        waitForLoadPageByTextOnPage(titleReservePage, expectedText);
//        assertTrue(titleReservePage.isDisplayed());
//    }
//    public void purchasePageIsDisplayed(String expectedText) {
//        WebElement titlePurchasePage = driver.findElement(By.tagName("h2"));
//        waitForLoadPageByTextOnPage(titlePurchasePage, expectedText);
//        assertTrue(titlePurchasePage.isDisplayed());
//    }

    public void confirmationPageIsDisplayed(String expectedText) {
        String expectedUrl = baseUrl + "/confirmation.php";
        WebElement titleConfirmationPage = driver.findElement(By.tagName("h1"));

        waitForLoadPageByTextOnPage(titleConfirmationPage, expectedText);

        assertTrue(titleConfirmationPage.isDisplayed());
        assertEquals(expectedUrl, driver.getCurrentUrl());
    }

//    public void fillInTheFlightInformation(WebElement flightElement, Flight flight) {
//        flight.setNumber(flightElement.findElement(By.cssSelector("input[name=flight]")).getAttribute("value"));
//        flight.setPrice(Float.parseFloat(flightElement.
//                findElement(
//                        By.cssSelector("input[name=price]")).
//                getAttribute("value")));
//        flight.setAirline(flightElement.findElement(By.cssSelector("input[name=airline]")).getAttribute("value"));
//    }

    public void fillAndSubmitUserDataForm(Person user, Location address, BankCard bankCard) {
        WebElement iName = driver.findElement(By.id("inputName"));
        iName.clear();
        iName.sendKeys(user.fullName());

        WebElement iAddress = driver.findElement(By.id("address"));
        iAddress.clear();
        iAddress.sendKeys(address.getStreet());

        WebElement iCity = driver.findElement(By.id("city"));
        iCity.clear();
        iCity.sendKeys(address.getCity());

        WebElement iState = driver.findElement(By.id("state"));
        iState.clear();
        iState.sendKeys(address.getState());

        WebElement iZipCode = driver.findElement(By.id("zipCode"));
        iZipCode.clear();
        iZipCode.sendKeys(address.getZipCode());

        WebElement selectElementCardType = driver.findElement(By.id("cardType"));
        Select selectCardType = new Select(selectElementCardType);
        selectCardType.selectByValue(bankCard.getTypeOfCard());

        WebElement iCreditCardNumber = driver.findElement(By.id("creditCardNumber"));
        iCreditCardNumber.clear();
        iCreditCardNumber.sendKeys(bankCard.getCardNumber());

        WebElement iCreditCardMonth = driver.findElement(By.id("creditCardMonth"));
        iCreditCardMonth.clear();
        iCreditCardMonth.sendKeys(bankCard.getMonth());

        WebElement iCreditCardYear = driver.findElement(By.id("creditCardYear"));
        iCreditCardYear.clear();
        iCreditCardYear.sendKeys(bankCard.getYear());

        WebElement iNameOnBankCard = driver.findElement(By.id("nameOnCard"));
        iNameOnBankCard.clear();
        iNameOnBankCard.sendKeys(bankCard.getNameOnCard());

        driver.findElement(By.cssSelector("input[type=submit]")).click();
    }
}
