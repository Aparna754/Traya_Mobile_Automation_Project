package com.company.framework.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private AppiumDriver driver;

    public WaitUtils(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElement(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
