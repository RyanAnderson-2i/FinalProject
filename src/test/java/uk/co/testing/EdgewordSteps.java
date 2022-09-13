package uk.co.testing;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import uk.co.testing.POMPages.LoginPagePOM;
import uk.co.testing.hooks.SharedDictionary;

public class EdgewordSteps {

    private SharedDictionary dict;
    //private WebDriver driver;

    public EdgewordSteps(SharedDictionary dict) {
        this.dict = dict;
        //this.driver = dict.getDriver();
    }

    LoginPagePOM login = new LoginPagePOM(dict);

    @Given("I am on the edgewords login page")
    public void i_am_on_the_edgewords_login_page() {
        dict.getDriver().get("https://www.edgewordstraining.co.uk/demo-site/my-account/");
    }
    @When("I enter valid login information")
    public void i_enter_valid_login_information() {
        login.doLogin("Ryan.Anderson@2itesting.com","TheAlliance322");
    }
    @Then("I log in to my account")
    public void i_log_in_to_my_account() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}