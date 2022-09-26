package uk.co.testing.tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uk.co.testing.POMPages.*;
import uk.co.testing.hooks.SharedDictionary;
import static org.hamcrest.Matchers.*;

public class EdgewordSteps {
    private SharedDictionary dict;
    private WebDriver driver;
    private LoginPagePOM login;
    private NavigationPOM nav;
    private CartPOM cart;
    private ShopPOM shop;
    private CheckoutPOM checkout;
    private ordersPOM orders;
    public EdgewordSteps(SharedDictionary dict) {
        this.dict = dict;
        this.driver = dict.getDriver();
        this.login = new LoginPagePOM(dict);
        this.nav = new NavigationPOM(dict);
        this.shop = new ShopPOM(dict);
        this.cart = new CartPOM(dict);
        this.checkout = new CheckoutPOM(dict);
        this.orders = new ordersPOM(dict);
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
    public void i_add_a_item_to_the_basket_on_the_shop_page() throws InterruptedException {
        nav.goShop();
        shop.addToCartRnd();
        Thread.sleep(3000);
    }
    @When("I navigate to the cart")
    public void i_navigate_to_the_cart() {
        nav.goCart();
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
    @Then("I get a {int}% discount")
    public void i_get_a_discount(int discountValue) throws InterruptedException {
        var discount = cart.getDiscount();
        var subTotal = cart.getSubTotal();
        var delivery = cart.getDeliveryCost();
        var total= cart.getTotal();
        var costBeforeDelivery = total - delivery;
        var test = 100 * (subTotal - costBeforeDelivery) / subTotal;
        MatcherAssert.assertThat("Discount is correct", test, is(discountValue));
        MatcherAssert.assertThat("Final price matches discount", (int)Math.round(subTotal - costBeforeDelivery), is(discount));
        Thread.sleep(3000);
        cart.cleanUp();
    }
    @When("Place the order")
    public void place_the_order() throws InterruptedException {
        cart.checkout();
        Thread.sleep(3000);
        //checkout.setFirstName("John");
        //checkout.setLastName("Doe");
        //checkout.setAddress("123 Fake Street");
        //checkout.setCity("Edinburgh");
        //checkout.setPostcode("EH11 3SR");
        //checkout.setPhoneNumber("1234567891");
        //checkout.setEmail("Ryan.Anderson@2itesting.com");
        checkout.payCheque();
        checkout.enterCheckoutInformation("John","Doe","123 Fake Street","Edinburgh",
                "EH11 3SR", "1234567801","testEmail@email.com");
        Thread.sleep(3000);
    }
    @Then("The order numbers should match")
    public void the_order_numbers_should_match() throws InterruptedException {
        checkout.placeOrder();
        String orderID = checkout.captureOrderID();
        Thread.sleep(3000);
        nav.goOrders();
        //checkout.goToAccount();
        //orders.ordersPage();
        String myOrderID = orders.getOrderId();
        MatcherAssert.assertThat("Orders match", orderID, is(myOrderID));
        nav.logout();
    }
}