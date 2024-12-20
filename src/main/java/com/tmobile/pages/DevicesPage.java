package com.tmobile.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.SeleniumHelper;

import java.util.List;

public class DevicesPage {

    WebDriver driver;
    private static final Logger logger = LogManager.getLogger();

    public DevicesPage(WebDriver driver) {
        this.driver = driver;
    }

    By firstSmartphone = By.xpath("//div[@data-qa='LST_ProductCard0']");
    By devicePriceOnStart = By.xpath("(//div[@class='sc-feUZmu gSyZgc dt_typography variant_h4'])[6]");
    By devicePriceMonthly = By.xpath("(//div[@class='sc-feUZmu gSyZgc dt_typography variant_h4'])[7]");
    By buttons = By.xpath("//span[@class='buttonText']");

    public void selectFirstSmartphone() {
        logger.info("Select first smartphone");
        SeleniumHelper.waitForElementToExist(driver, firstSmartphone);
        driver.findElement(firstSmartphone).click();
        logger.info(("Select first smartphone done"));
    }

    public String devicePriceDisplayedOnStart() {
        logger.info("Display device price on start");
        SeleniumHelper.waitForElementToExist(driver, devicePriceOnStart);
        WebElement price = driver.findElement(devicePriceOnStart);
        logger.info("Device price on start returned");
        return price.getText();
    }

    public String devicePriceDisplayedMonthly() {
        logger.info("Display device price monthly");
        SeleniumHelper.waitForElementToExist(driver, devicePriceMonthly);
        WebElement price = driver.findElement(devicePriceMonthly);
        logger.info("Device price monthly returned");
        return price.getText();
    }

    public void addSmartphoneToBasket() {
        logger.info("Add smartphone to basket");
        SeleniumHelper.waitForNotEmptyList(driver, buttons);
        List<WebElement> list = driver.findElements(buttons);
        list.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        logger.info("Add smartphone to basket done");
    }
}
