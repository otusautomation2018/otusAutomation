package pages.automationpractice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class TopMenuBlock extends BasePage {

    @FindBy(css = "ul.sf-menu>li:nth-of-type(1)>a")
    public  WebElement firstCategory;

    @FindBy(css = "ul.submenu-container")
    public WebElement subMenu;

    @FindBy(xpath = "//a[@title='T-shirts']")
    public WebElement tShirtsBtn;
}
