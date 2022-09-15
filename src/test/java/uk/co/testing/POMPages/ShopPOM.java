package uk.co.testing.POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.co.testing.hooks.SharedDictionary;

import java.time.Duration;
import java.util.List;

public class ShopPOM {

    private WebDriver driver;
    private final SharedDictionary dict;

    private WebDriverWait waitDriver;

    public ShopPOM(SharedDictionary dict) {
        this.dict = dict;
        this.driver = dict.getDriver();
        PageFactory.initElements(driver, this);
        this.waitDriver = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    //Locators
    @FindBy(linkText = "Shop")
    private WebElement shopLink;

    @FindBy(partialLinkText = "View Cart")
    private WebElement cartLink;

    @FindBys(
            @FindBy(css = ".add_to_cart_button")
    )
    private List<WebElement> addToCartElements;

    public void addToCartRnd() {
        var element = (int) Math.round(Math.random() * (addToCartElements.size() - 1));
        addToCartElements.get(element).click();
        //waitDriver.until(drv -> drv.findElement(By.linkText("Add to cart")));
    }

    public void viewCart(){
        waitDriver.until(drv -> drv.findElement(By.linkText("View cart"))).click();
    }
}