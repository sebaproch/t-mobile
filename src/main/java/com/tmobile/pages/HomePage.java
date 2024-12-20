package com.tmobile.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.SeleniumHelper;

import java.util.List;

public class HomePage {

    WebDriver driver;
    private static final Logger logger = LogManager.getLogger();

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By cookies = By.xpath("//button[@id='didomi-notice-agree-button']");
    By devicesMenu = By.xpath("//button[text()= 'Urządzenia']");
    By smartphones = By.xpath("//a[@data-ga-ea='nav-links - Urządzenia/Bez abonamentu/Smartfony']");
    By basketIcon = By.xpath("//span[text()= 'Przejdź do strony koszyka']");

    public void acceptCookies() {
        SeleniumHelper.waitForElementToExist(driver, cookies);
        driver.findElement(cookies).click();
    }

    public void goToDevices() {
        logger.info("Move to menu devices");
        SeleniumHelper.waitForElementToExist(driver, devicesMenu);
        WebElement devicesMenuElement = driver.findElement(devicesMenu);
        Actions actions = new Actions(driver);
        actions.moveToElement(devicesMenuElement).perform();
        WebElement smartwatchesElement = driver.findElement(smartphones);
        SeleniumHelper.waitForElementToBeVisible(driver, smartwatchesElement);
        actions.moveToElement(smartwatchesElement).perform();
        actions.click(smartwatchesElement).build().perform();
        logger.info("Smartwatches clicked done");
    }

    public String getBasketIconText() {
        logger.info("Basket icon");
        SeleniumHelper.waitForElementToExist(driver, basketIcon);
        String message = "";
        List<WebElement> list = driver.findElements(basketIcon);
        if (!list.isEmpty()) {
            message = list.stream().filter(WebElement::isDisplayed).toList().getFirst().getText();
        }
        logger.info("Basket icon done");
        return message;
    }
}
