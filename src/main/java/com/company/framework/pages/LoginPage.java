package com.company.framework.pages;

import com.company.framework.constants.FrameworkConstants;
import com.company.framework.utils.ExcelReader;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {

    /*private AndroidDriver driver;
    private WebElement usernameField;
    private WebElement passwordField;
    private WebElement loginButton;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {

        driver.findElement(
                AppiumBy.id("com.example:id/username")
        ).sendKeys(username);
    }

    public void enterPassword(String password) {

        driver.findElement(
                AppiumBy.id("com.example:id/password")
        ).sendKeys(password);
    }

    public void clickLogin() {

        driver.findElement(
                AppiumBy.accessibilityId("Login")
        ).click();
    }

    public void login(String username, String password) {

        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    private By getElement(
            String pageName,
            String elementName
    ) {

        try {

            String[] locatorData =
                    ExcelReader.getLocatorData(
                            FrameworkConstants.LOCATOR_FILE_PATH,
                            "Locators",
                            pageName,
                            elementName
                    );

            return LocatorUtil.getLocator(
                    locatorData[0],
                    locatorData[1]
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to find locator for "
                            + elementName,
                    e
            );
        }
    }*/
}
