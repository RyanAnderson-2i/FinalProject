package uk.co.testing.POMPages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import uk.co.testing.hooks.SharedDictionary;

public class LoginPagePOM {
    private WebDriver driver;
    private final SharedDictionary dict;
    public LoginPagePOM(SharedDictionary dict) {
        this.dict = dict;
        this.driver = dict.getDriver();
        PageFactory.initElements(driver, this);
    }

    //Locators
    @FindBy(id = "username")
    WebElement usernameField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(name = "login")
    WebElement logInButton;
    @FindBy(linkText = "Dismiss")
    WebElement dismiss;

    @FindBy(linkText = "Logout")
    WebElement logoutLink;

    public void homePage(String url){
        driver.get(url);
    }

    public void setUsername(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void setPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void submitForm(){
        logInButton.click();
    }

    //Helper
    public void clickDismiss(){
        dismiss.click();
    }
    public void doLogin(String username, String password){
        clickDismiss();
        setUsername(username);
        setPassword(password);
        submitForm();
    }

    public boolean loginExpectSuccess(String username, String password) {
        doLogin(username, password);
        try {
            logoutLink.isDisplayed();
        } catch(NoSuchElementException e) {
            return false;
        }
        return true;
    }
}