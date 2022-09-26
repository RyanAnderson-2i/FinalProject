package uk.co.testing.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
    private WebDriver driver;
    private final SharedDictionary dict;

    public Hooks(SharedDictionary dict) {
        this.dict = dict;
    }

    @Before
    public void setUp(){
        String browser = System.getProperty("browser");
        System.out.println("Browser set to: " + browser);
        if (browser == null) {
            browser = "";
        }
        switch (browser) {
            case "firefox":
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case "chrome":
                driver = WebDriverManager.chromedriver().create();
                break;
            case "safari":
                WebDriverManager wdm = WebDriverManager.safaridriver().browserInDocker();
                driver = wdm.create();
                break;
            default:
                System.out.println("Browser not set at command line so using ChromeDriver");
                driver = WebDriverManager.chromedriver().create();
                break;
        }
        dict.setDriver(driver);
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}