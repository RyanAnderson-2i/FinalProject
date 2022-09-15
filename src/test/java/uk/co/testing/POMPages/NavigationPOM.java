package uk.co.testing.POMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import uk.co.testing.hooks.SharedDictionary;

public class NavigationPOM {

    private WebDriver driver;
    private final SharedDictionary dict;

    public NavigationPOM(SharedDictionary dict) {
        this.dict = dict;
        this.driver = dict.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Shop")
    private WebElement shopLink;
    @FindBy(linkText = "Cart")
    private WebElement cartLink;

    @FindBy(linkText = "My account")
    private WebElement myAccountLink;

    @FindBy(linkText = "Orders")
    private WebElement ordersLink;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    public void goHome() {
        driver.get("https://www.edgewordstraining.co.uk/demo-site/my-account/");
    }

    public void goShop() {
        shopLink.click();
    }

    public void goCart() {
        cartLink.click();
    }

    public void goAccount() {
        myAccountLink.click();
    }

    public void goOrders() {
        goAccount();
        ordersLink.click();
    }

    public void logout() {
        goAccount();
        logoutLink.click();
    }
}