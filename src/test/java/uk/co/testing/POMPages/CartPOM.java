package uk.co.testing.POMPages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.co.testing.hooks.SharedDictionary;

import java.time.Duration;
import java.util.List;

public class CartPOM {

    private WebDriver driver;
    private final SharedDictionary dict;

    private WebDriverWait waitDriver;

    public CartPOM(SharedDictionary dict) {
        this.dict = dict;
        this.driver = dict.getDriver();
        PageFactory.initElements(driver, this);
        this.waitDriver = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    //Locators
    @FindBy(id = "coupon_code")
    private WebElement codeField;

    @FindBy(name = "apply_coupon")
    private WebElement applyCoupon;

    @FindBys(@FindBy(css = ".cart_item"))
    private List<WebElement> basketItems;

    @FindBy(css = ".remove")
    private WebElement remove;

    @FindBy(linkText = "Remove")
    private WebElement removeCode;

    //Helper Methods
    public void enterCode(String promocode){
        codeField.clear();
        codeField.sendKeys(promocode);
    }

    public void submitCode(){
        applyCoupon.click();
    }

    public void removeAllItems() {
        System.out.println("Removing " + basketItems.size() + " basket items.");
        basketItems.forEach(e -> {
            remove.click();
            //dict.getWait().until(drv -> drv.findElement(By.cssSelector(".restore-item")));
        });
    }

    public boolean isBasketEmpty() {
        try {
            dict.getWait().until(drv -> drv.findElement(By.cssSelector(".cart-empty")));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

}