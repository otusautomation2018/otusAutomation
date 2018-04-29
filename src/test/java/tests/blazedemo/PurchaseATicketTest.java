package tests.blazedemo;

import org.testng.annotations.Test;
import pages.blazedemo.ConfirmationPage;
import pages.blazedemo.MainPage;
import pages.blazedemo.PurchasePage;
import pages.blazedemo.ReservePage;
import entities.BankCard;
import entities.Flight;
import entities.Location;
import entities.Person;
import tests.BaseTest;
import utils.PropertyReader;
import utils.helpers.WaitingsHelpers;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PurchaseATicketTest extends BaseTest {

    private String baseUrl = PropertyReader.
            getPropertyFromFile("properties/blazedemo.properties", "baseUrl");

    private Person user = new Person.PersonBuilder().createPerson();
    private Location address = new Location.LocationBuilder().createLocation();
    private BankCard bankCard = new BankCard.BankCardBuilder(user.fullName()).createBankCard();
    private Flight flight = new Flight();
    private final String currency = "USD";

    @Test
    public void test() {

        driver.get(baseUrl);

        MainPage homePage = new MainPage();
        assertTrue(homePage.isInitialized());

        homePage.fillChoiseCitiesForm(flight.departureCity(), flight.destinationCity());

        ReservePage reservePage = homePage.submitForm();

        String textOnHeadingOnReservePage = reservePage.expectedTextOnTitle(
                flight.departureCity(),
                flight.destinationCity());
        WaitingsHelpers.waitForLoadPageByTextOnPage(reservePage.title, textOnHeadingOnReservePage);
        assertEquals(driver.getCurrentUrl(), baseUrl + reservePage.getUrl());
        assertTrue(reservePage.isInitialized());

        reservePage.fillInTheFlightInformation(flight);

        PurchasePage purchasePage = reservePage.choiseFlight();

        String textOnHeadingOnPurchasePage = purchasePage.expectedTextOnTitle(
                flight.departureCity(),
                flight.destinationCity());
        WaitingsHelpers.waitForLoadPageByTextOnPage(purchasePage.title, textOnHeadingOnPurchasePage);
        assertEquals(driver.getCurrentUrl(), baseUrl + purchasePage.getUrl());
        assertTrue(purchasePage.isInitialized());

        assertEquals(purchasePage.flightNumber.getText(), "Flight Number: " + flight.getNumber());
        assertEquals(purchasePage.airlineCompany.getText(), "Airline: " + flight.getAirline());

        compareCosts(purchasePage);

        purchasePage.fillPayForm(user, address, bankCard);
        ConfirmationPage confirmationPage = purchasePage.submitForm();

        WaitingsHelpers.waitForLoadPageByTextOnPage(confirmationPage.title, confirmationPage.expectedTextOnTitle());
        String expectedUrl = baseUrl + confirmationPage.getUrl();
        assertEquals(driver.getCurrentUrl(), expectedUrl);
        assertTrue(confirmationPage.isInitialized());

        assertTrue(confirmationPage.orderId.isDisplayed());
        assertTrue(confirmationPage.orderStatus.isDisplayed());

        assertTrue(confirmationPage.orderAmount.isDisplayed());
        assertEquals(confirmationPage.orderAmount.getText(), currency);

        compareLast4SymbolsOfBankCard(confirmationPage);

        assertTrue(confirmationPage.orderExpiration.isDisplayed());
        assertEquals(confirmationPage.orderExpiration.getText(), bankCard.getMonth() + " /" + bankCard.getYear());

        assertTrue(confirmationPage.orderAuthCode.isDisplayed());
    }

    public void compareCosts(PurchasePage purchasePage) {
        String priceCost = purchasePage.priceCost.getText().
                replace("Price: ", "");
        String feesAndTaxes = purchasePage.arbitraryFeesAndTaxes.getText().
                replace("Arbitrary Fees and Taxes: ","");
        String totalCost = purchasePage.totalCost.getText().
                replace("Total Cost: ","");

        float priceCostValue = Float.parseFloat(priceCost);
        float arbitraryFeesAndTaxesValue = Float.parseFloat(feesAndTaxes);
        float totalCostValue = Float.parseFloat(totalCost);

        float totalCostResult = priceCostValue + arbitraryFeesAndTaxesValue;

//        цена на странице Purchase равна той, что мы выбрали на странице Reserve
        assertEquals(priceCostValue, flight.getPrice());
//        отображаемая итоговая цена является суммой цены и сборов
        assertEquals(totalCostValue, totalCostResult);
    }

    //        проверяем соответствие последних 4 знаков
    public void compareLast4SymbolsOfBankCard(ConfirmationPage confirmationPage){
        String cardNumberOnPage = confirmationPage.orderCardNumber.getText();
        String actualCardNumbers = cardNumberOnPage.substring(cardNumberOnPage.length()-4);
        String expectedCardNumbers = bankCard.getCardNumber().substring(bankCard.getCardNumber().length()-4);

        assertEquals(actualCardNumbers, expectedCardNumbers);
    }
}
