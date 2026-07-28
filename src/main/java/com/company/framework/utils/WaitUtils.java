package com.company.framework.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private AndroidDriver driver;

    public WaitUtils(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElement(By locator) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
