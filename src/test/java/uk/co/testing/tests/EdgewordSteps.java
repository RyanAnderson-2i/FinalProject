package uk.co.testing.tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uk.co.testing.POMPages.CartPOM;
import uk.co.testing.POMPages.LoginPagePOM;
import uk.co.testing.POMPages.NavigationPOM;
import uk.co.testing.POMPages.ShopPOM;
import uk.co.testing.hooks.SharedDictionary;
import static org.hamcrest.Matchers.*;

public class EdgewordSteps {
    private SharedDictionary dict;
    private WebDriver driver;
    private LoginPagePOM login;
    private NavigationPOM nav;
    private CartPOM cart;
    private ShopPOM shop;
    public EdgewordSteps(SharedDictionary dict) {
        this.dict = dict;
        this.driver = dict.getDriver();
        this.login = new LoginPagePOM(dict);
        this.nav = new NavigationPOM(dict);
        this.shop = new ShopPOM(dict);
        this.cart = new CartPOM(dict);
    }

    //Scenario 1
    @Given("I am on the edgewords login page")
    public void i_am_on_the_edgewords_login_page() {
        nav.goHome();
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

    //Scenario 2
    @Given("I am logged in with my edgewords account {string} and {string}")
    public void i_am_logged_in_with_my_edgewords_account_and(String username, String password) {
        nav.goHome();
        boolean didWeLogin = login.loginExpectSuccess(username, password);
        MatcherAssert.assertThat("Login successful", didWeLogin);
    }
    @When("I add a item to the basket on the shop page")
    public void i_add_a_item_to_the_basket_on_the_shop_page() {
        nav.goShop();
        shop.addToCartRnd();
    }
    @When("I navigate to the cart")
    public void i_navigate_to_the_cart() {
        shop.viewCart();
    }
    @When("I redeem promo code {string}")
    public void i_redeem_promo_code(String promocode) throws InterruptedException {
        cart.enterCode(promocode);
        cart.submitCode();
        Thread.sleep(3000);
        String bodyText = driver.findElement(By.cssSelector("body")).getText();
        MatcherAssert.assertThat("promo code error", bodyText, containsString("Coupon code applied successfully"));
        Thread.sleep(3000);
    }
    @Then("I get a {double}% discount")
    public void i_get_a_discount(Double discountValue) throws InterruptedException {
        var discount = cart.getDiscount();
        var subTotal = cart.getSubTotal();
        var delivery = cart.getDeliveryCost();
        var total= cart.getTotal();

        var costBeforeDelivery = total - delivery;

        System.out.println(discount);
        System.out.println(subTotal);
        System.out.println(delivery);
        System.out.println(total);

        var test = 100 * (subTotal - costBeforeDelivery) / subTotal;

        MatcherAssert.assertThat("Discount is correct", test, is(10));
        MatcherAssert.assertThat("Final price matches discount", (int)Math.round(subTotal - costBeforeDelivery), is(discount));
        Thread.sleep(3000);
        cart.removeCode();
        cart.removeAllItems();
    }
}