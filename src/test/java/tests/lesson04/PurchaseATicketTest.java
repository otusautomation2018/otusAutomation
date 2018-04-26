package tests.lesson04;

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
import utils.helpers.WaitingsHelpers;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PurchaseATicketTest extends BaseTest {

    @Test
    public void test() {

        Person user = new Person.PersonBuilder().createPerson();
        Location address = new Location.LocationBuilder().createLocation();
        BankCard bankCard = new BankCard.BankCardBuilder(user.fullName()).createBankCard();

        Flight flight = new Flight();

        driver.get(baseUrl);

        MainPage homePage = new MainPage();
        assertTrue(homePage.isInitialized());

        homePage.fillChoiseCitiesForm(flight.departureCity(), flight.destinationCity());

        ReservePage reservePage = homePage.submitForm();
        String textOnHeadingOnReservePage = reservePage.expectedTextOnTitle(
                flight.departureCity(),
                flight.destinationCity());
        WaitingsHelpers.waitForLoadPageByTextOnPage(reservePage.title, textOnHeadingOnReservePage);
        assertTrue(reservePage.isInitialized());

        reservePage.fillInTheFlightInformation(flight);
        PurchasePage purchasePage = reservePage.choiseFlight();
        String textOnHeadingOnPurchasePage = purchasePage.expectedTextOnTitle(
                flight.departureCity(),
                flight.destinationCity());
        WaitingsHelpers.waitForLoadPageByTextOnPage(purchasePage.title, textOnHeadingOnPurchasePage);
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

        WaitingsHelpers.waitForLoadPageByTextOnPage(confirmationPage.title, confirmationPage.expectedTextOnTitle());
        String expectedUrl = baseUrl + confirmationPage.getUrl();
        assertEquals(driver.getCurrentUrl(), expectedUrl);
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

//    public void waitForLoadPageByTextOnPage(WebElement expectedElement, String expectedText){
//        webDriverWait.until(
//                ExpectedConditions.textToBePresentInElement(expectedElement, expectedText));
//    }
}
