package tests.lesson04;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class PurchareATicket {

    @Test
    public void test() {

        WebDriver driver = new Driver().getDriver(null);

        driver.get("https://yandex.ru");
//        driver.close();
        driver.quit();
    }
}
