package uk.co.testing.POMPages;

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
    private WebDriverWait waitDriver;

    public CheckoutPOM(SharedDictionary dict) {
        this.dict = dict;
        this.driver = dict.getDriver();
        PageFactory.initElements(driver, this);
        this.waitDriver = new WebDriverWait(driver, Duration.ofSeconds(3));
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

    @FindBy(id = "select2-billing_country-container")
    private WebElement dropdownMenu;
    Select drp = new Select(dropdownMenu);

    @FindBy(id = "#payment > ul > li.wc_payment_method.payment_method_cheque > label")
    private WebElement chequeRadio;

    @FindBy(id = "#payment > ul > li.wc_payment_method.payment_method_cod > label")
    private WebElement cashRadio;
}