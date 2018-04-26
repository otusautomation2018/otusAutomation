package tests.lesson04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.ConfirmationPage;
import pages.MainPage;
import pages.PurchasePage;
import pages.ReservePage;
import structures.BankCard;
import structures.Flight;
import structures.Location;
import structures.Person;
import tests.BaseTest;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PurchaseATicketTest extends BaseTest {

    @Test
    public void test() {

        Person user = new Person.PersonBuilder().createPerson();
        Location address = new Location.LocationBuilder().createLocation();
        BankCard bankCard = new BankCard.BankCardBuilder(user.fullName()).createBankCard();

        Flight flight = new Flight();

        String textOnHeadingOnReservePage = "Flights from " +
                flight.departureCity() +
                " to " +
                flight.destinationCity() + ":";
        String textOnHeadingOnPurchasePage = "Your flight from " +
                flight.departureCity() +
                " to " +
                flight.destinationCity() +
                " has been reserved.";
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

        assertEquals(purchasePage.flightNumber.getText(), "Flight Number: " + flight.getNumber());
        assertEquals(purchasePage.airlineCompany.getText(), "Airline: " + flight.getAirline());

        float priceCostValue = Float.parseFloat(purchasePage.priceCost.getText().
                replace("Price: ", ""));
        float arbitraryFeesAndTaxes = Float.parseFloat(purchasePage.arbitraryFeesAndTaxes.getText().
                replace("Arbitrary Fees and Taxes: ",""));

        float totalCostResult =
                priceCostValue + arbitraryFeesAndTaxes;
        float totalCostValue = Float.parseFloat(purchasePage.totalCost.getText().
                        replace("Total Cost: ",""));

//        цена на странице Purchare равна той, что мы выбрали на странице Reserve
        assertEquals(priceCostValue, flight.price);
//        отображаемая итоговая цена является суммой цены и сборов
        assertEquals(totalCostValue, totalCostResult);

        purchasePage.fillPayForm(user, address, bankCard);
        ConfirmationPage confirmationPage = purchasePage.submitForm();

        waitForLoadPageByTextOnPage(confirmationPage.title, textOnHeadingOnConfirmationPage);
        String expectedUrl = baseUrl + confirmationPage.getUrl();
        assertEquals(expectedUrl, driver.getCurrentUrl());
        assertTrue(confirmationPage.isInitialized());

        assertTrue(confirmationPage.orderId.isDisplayed());
        assertTrue(confirmationPage.orderStatus.isDisplayed());

        assertTrue(confirmationPage.orderAmount.isDisplayed());
        assertEquals(confirmationPage.orderAmount.getText(), "USD");

//        проверяем соответствие последних 4 знаков
        assertEquals(confirmationPage.
                        orderCardNumber.
                        getText().
                        substring(confirmationPage.
                                orderCardNumber.
                                getText().
                                length()-4),
                        bankCard.
                                getCardNumber().
                                substring(bankCard.getCardNumber().length()-4));

        assertTrue(confirmationPage.orderExpiration.isDisplayed());
        assertEquals(confirmationPage.orderExpiration.getText(), bankCard.getMonth() + " /" + bankCard.getYear());

        assertTrue(confirmationPage.orderAuthCode.isDisplayed());
    }

    public void waitForLoadPageByTextOnPage(WebElement expectedElement, String expectedText){
        webDriverWait.until(
                ExpectedConditions.textToBePresentInElement(expectedElement, expectedText));
    }
}
