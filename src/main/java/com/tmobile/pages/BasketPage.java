package com.tmobile.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.SeleniumHelper;

public class BasketPage {

    WebDriver driver;
    private static final Logger logger = LogManager.getLogger();

    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }

    By backMainPage = By.xpath("//img[@alt='T-Mobile - przejdź na stronę główną']");
    By priceOnStartInBasket = By.xpath("//span[@data-qa='BKT_TotalupFront']");
    By priceMonthlyInBasket = By.xpath("//span[@data-qa='BKT_TotalMonthly']");


    public String basketPriceOnStart() {
        logger.info("Display basket price on start");
        SeleniumHelper.waitForElementToExist(driver, priceOnStartInBasket);
        WebElement price = driver.findElement(priceOnStartInBasket);
        logger.info("Basket price on start returned");
        return price.getText();
    }

    public String basketPriceMonthly() {
        logger.info("Display basket price monthly");
        SeleniumHelper.waitForElementToExist(driver, priceMonthlyInBasket);
        WebElement price = driver.findElement(priceMonthlyInBasket);
        logger.info("Basket price monthly returned");
        return price.getText();
    }

    public void backToMainPage() {
        logger.info("Go back to main page");
        SeleniumHelper.waitForElementToExist(driver, backMainPage);
        driver.findElement(backMainPage).click();
        logger.info("Back to main page done");
    }
}
