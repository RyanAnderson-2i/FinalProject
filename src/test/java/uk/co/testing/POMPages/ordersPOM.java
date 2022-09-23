package uk.co.testing.POMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import uk.co.testing.hooks.SharedDictionary;

public class ordersPOM {

    private WebDriver driver;
    private final SharedDictionary dict;

    public ordersPOM(SharedDictionary dict) {
        this.dict = dict;
        this.driver = dict.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Orders")
    private WebElement orderPageLink;

    @FindBy(css = "tr:nth-child(1) > td.woocommerce-orders-table__cell.woocommerce-orders-table__cell-order-number > a")
    private WebElement orderNoField;

    public String getOrderId() {
        return orderNoField.getText().substring(1);
    }

    public void ordersPage(){
        orderPageLink.click();
    }

}
