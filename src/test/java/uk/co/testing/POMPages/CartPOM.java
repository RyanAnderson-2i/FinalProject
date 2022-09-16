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

    @FindBy(partialLinkText = "Remove")
    private WebElement removeCode;

    @FindBy(xpath = "//*[@id=\"post-5\"]/div/div/div[2]/div/table/tbody/tr[1]/td/span/bdi")
    private WebElement subTotal;

    @FindBy(xpath = "//*[@id=\"shipping_method\"]/li/label/span/bdi")
    private WebElement deliveryCost;

    @FindBy(xpath = "//*[@id=\"post-5\"]/div/div/div[2]/div/table/tbody/tr[2]/td/span")
    private WebElement discount;

    @FindBy(xpath = "//*[@id=\"post-5\"]/div/div/div[2]/div/table/tbody/tr[4]/td/strong/span/bdi")
    private WebElement total;

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

    public int getSubTotal(){
        return getPrice(subTotal);
    }

    public int getDiscount(){
        return getPrice(discount);
    }

    public int getDeliveryCost(){
        return getPrice(deliveryCost);
    }

    public int getTotal(){
        return getPrice(total);
    }

    public boolean isBasketEmpty() {
        try {
            dict.getWait().until(drv -> drv.findElement(By.cssSelector(".cart-empty")));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void removeCode(){
        removeCode.click();
    }

    private int getPrice(WebElement element) {
        return Math.round(Integer.parseInt(element.getText().substring(1).replace(".", "")));
    }

}