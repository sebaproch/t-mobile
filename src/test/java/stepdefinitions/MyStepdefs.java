package stepdefinitions;

import com.tmobile.pages.BasketPage;
import com.tmobile.pages.DevicesPage;
import com.tmobile.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class MyStepdefs {

    WebDriver driver;
    HomePage homePage;
    DevicesPage devicesPage;
    BasketPage basketPage;
    String devicePriceDisplayedOnStart;
    String devicePriceDisplayedMonthly;
    final String basketText = "Przejdź do strony koszyka";

    @Given("Otwieram przegladarke na stronie {string}")
    public void openBrowser(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sebastian\\IdeaProjects\\Chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--enable-javascript");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);
        homePage = new HomePage(driver);
        homePage.acceptCookies();
    }

    @When("Wybieram smartfony bez abonamentu")
    public void selectDevices() {
        homePage.goToDevices();

    }

    @And("Klikam w pierwszy element listy")
    public void clickOnProduct() {
        devicesPage = new DevicesPage(driver);
        WebElement firstSmartphone = driver.findElement(By.xpath("//div[@data-qa='LST_ProductCard0']"));
        Assert.assertTrue(firstSmartphone.isDisplayed());
        devicesPage.selectFirstSmartphone();
        devicePriceDisplayedOnStart = devicesPage.devicePriceDisplayedOnStart().substring(0, 2);
        devicePriceDisplayedMonthly = devicesPage.devicePriceDisplayedMonthly().substring(0, 2);
        devicesPage.addSmartphoneToBasket();
    }

    @And("Dodaje produkt do koszyka")
    public void verifyProductInCart() {
        basketPage = new BasketPage(driver);
        Assert.assertEquals(devicePriceDisplayedOnStart, basketPage.basketPriceOnStart());
        Assert.assertEquals(devicePriceDisplayedMonthly, basketPage.basketPriceMonthly());
        basketPage.backToMainPage();
    }

    @Then("Przechodze na strone glowna T-Mobile")
    public void goToMainPage() {
        homePage.getBasketIconText();
        Assert.assertEquals(homePage.getBasketIconText(), basketText);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}