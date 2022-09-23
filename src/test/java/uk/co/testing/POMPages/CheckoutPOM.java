package uk.co.testing.POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.co.testing.hooks.SharedDictionary;

import java.time.Duration;

public class CheckoutPOM {
    private WebDriver driver;
    private final SharedDictionary dict;

    public CheckoutPOM(SharedDictionary dict) {
        this.dict = dict;
        this.driver = dict.getDriver();
        PageFactory.initElements(driver, this);
    }
    //Locators
    @FindBy(id = "billing_first_name")
    private WebElement firstNameField;

    @FindBy(id = "billing_last_name")
    private WebElement lastNameField;

    @FindBy(id = "billing_address_1")
    private WebElement AddressField;

    @FindBy(id = "billing_city")
    private WebElement cityField;

    @FindBy(id = "billing_postcode")
    private WebElement postcodeField;

    @FindBy(id = "billing_phone")
    private WebElement phoneField;

    @FindBy(id = "billing_email")
    private WebElement emailField;

    @FindBy(id = "place_order")
    private WebElement placeOrderButton;

    //@FindBy(id = "select2-billing_country-container")
    //private WebElement dropdownMenu;
    //Select drp = new Select(dropdownMenu);

    @FindBy(xpath = "//*[@id=\"payment\"]/ul/li[1]/label")
    private WebElement chequeRadio;

    @FindBy(xpath = "//*[@id=\"payment\"]/ul/li[2]/label")
    private WebElement cashRadio;

    @FindBy(linkText = "My account")
    private WebElement accountLink;


    //Helper Methods
    public void placeOrder(){
        placeOrderButton.click();
    }
    public void payCheque(){
        chequeRadio.click();
    }
    public void payCash(){
        cashRadio.click();
    }

    //public void setCountry(String country){
        //drp.selectByVisibleText(country);
    //}

    public void setFirstName(String firstName){
        enterValue(firstNameField, firstName);
    }

    public void setLastName(String lastName){
        enterValue(lastNameField, lastName);
    }

    public void setAddress(String address){
        enterValue(AddressField, address);
    }

    public void setCity(String city){
        enterValue(cityField,city);
    }

    public void setPostcode(String postcode){
        enterValue(postcodeField,postcode);
    }

    public void setPhoneNumber(String phoneNumber){
        enterValue(phoneField,phoneNumber);
    }

    public void setEmail(String email){
        enterValue(emailField,email);
    }

    private void enterValue(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }

    public String captureOrderID(){
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        myWait.until((drv) -> drv.findElement(By.cssSelector("li.woocommerce-order-overview__order.order > strong")));
        String orderID = driver.findElement(By.cssSelector("li.woocommerce-order-overview__order.order > strong")).getText();

        return orderID;
    }

    public void goToAccount(){
        accountLink.click();
    }
}