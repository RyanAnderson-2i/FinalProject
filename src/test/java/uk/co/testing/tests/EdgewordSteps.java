package uk.co.testing.tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uk.co.testing.POMPages.LoginPagePOM;
import uk.co.testing.hooks.SharedDictionary;
import static org.hamcrest.Matchers.*;

public class EdgewordSteps {

    private SharedDictionary dict;
    private WebDriver driver;
    private LoginPagePOM login;
    public EdgewordSteps(SharedDictionary dict) {
        this.dict = dict;
        this.driver = dict.getDriver();
        this.login = new LoginPagePOM(dict);
    }
    @Given("I am on the edgewords login page")
    public void i_am_on_the_edgewords_login_page() {
        login.homePage("https://www.edgewordstraining.co.uk/demo-site/my-account/");
    }
    @When("I enter valid login information {string} and {string}")
    public void i_enter_valid_login_information(String username, String password) {
        boolean didWeLogin = login.loginExpectSuccess(username, password);
        MatcherAssert.assertThat("Login successful", didWeLogin);
    }
    @Then("I log in to my account")
    public void i_log_in_to_my_account() {
        String logOut =  driver.findElement(By.linkText("Log out")).getText();
        System.out.println(logOut);
        MatcherAssert.assertThat(logOut, is("Log out"));
    }
}