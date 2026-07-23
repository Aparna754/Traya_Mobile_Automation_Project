package com.company.framework.pages;

import com.company.framework.utils.LocatorReader;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LetsGetStartedPage {

    private AndroidDriver driver;

    private By mobileNumberField = LocatorReader.getLocator("Login_Page", "Mobile Name");
    private By GetOTPButton = LocatorReader.getLocator("Login_Page", "GetOTP");
    private By letsGetStartedText = LocatorReader.getLocator("Lets get started", "Lets get started");
    private By nameField = LocatorReader.getLocator("Lets get started", "Please enter your name");
    private By ageField = LocatorReader.getLocator("Lets get started","Please enter your age");
    private By maleRadioButton = LocatorReader.getLocator("Lets get started", "Male");
    private By femaleRadioButton = LocatorReader.getLocator("Lets get started", "Female");
    private By continueButton = LocatorReader.getLocator("Lets get started", "Continue");
    public LetsGetStartedPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void enterMobileNumber(String mobileNumber) {
        driver.findElement(mobileNumberField).sendKeys(mobileNumber);
    }

    public void clickGetOTP() {
        driver.findElement(GetOTPButton).click();
    }

    public boolean isLetsGetStartedDisplayed() {
        return driver.findElement(letsGetStartedText).isDisplayed();
    }

    public void enterName(String name) {
        driver.findElement(nameField).click();
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterAge(String age) {
        driver.findElement(ageField).click();
        driver.findElement(ageField).sendKeys(age);
    }

    public void selectMale() {
        driver.findElement(maleRadioButton).click();
    }

    public boolean isMaleSelected() {
        return driver.findElement(maleRadioButton).isSelected();
    }

    public void selectFemale() {
        driver.findElement(femaleRadioButton).click();
    }
    public boolean isFemaleSelected() {
        return driver.findElement(femaleRadioButton).isSelected();
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }


}
