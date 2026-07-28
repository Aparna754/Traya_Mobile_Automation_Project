package com.company.framework.pages;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import java.util.Random;

public class LetsGetStartedPage {

    private final AndroidDriver driver;
    private final WaitUtils waitUtils;

    private final By mobileNumberField = AppiumBy.xpath("//android.widget.EditText[@text='Mobile number']");
    private final By getOTPButton = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Get OTP']/android.view.ViewGroup");
    private final By letsGetStartedText = AppiumBy.xpath("//android.widget.TextView[@text='Let’s get started']");
    private final By nameField = AppiumBy.xpath("//android.widget.EditText[@resource-id='username-name-input']");
    private final By ageField = AppiumBy.xpath("//android.widget.EditText[@resource-id='username-age-input']");
    private final By maleRadioButton = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Male']");
    private final By femaleRadioButton = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Female']");
    private final By continueButton = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='Continue']/android.view.ViewGroup");
    
//    reading excel data
//    private By mobileNumberField = LocatorReader.getLocator("Login_Page", "Mobile Name");
//    private By GetOTPButton = LocatorReader.getLocator("Login_Page", "GetOTP");
//    private By letsGetStartedText = LocatorReader.getLocator("Lets get started", "Lets get started");
//    private By nameField = LocatorReader.getLocator("Lets get started", "Please enter your name");
//    private By ageField = LocatorReader.getLocator("Lets get started","Please enter your age");
//    private By maleRadioButton = LocatorReader.getLocator("Lets get started", "Male");
//    private By femaleRadioButton = LocatorReader.getLocator("Lets get started", "Female");
//    private By continueButton = LocatorReader.getLocator("Lets get started", "Continue");

    public LetsGetStartedPage(AndroidDriver driver) {

        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);

    }
    public void enterMobileNumber(String mobileNumber) {
        waitUtils.waitForElement(mobileNumberField);
        driver.findElement(mobileNumberField).sendKeys(mobileNumber);
    }

    public void clickGetOTP() {
        waitUtils.waitForElement(getOTPButton);
        driver.findElement(getOTPButton).click();
    }

    public boolean isLetsGetStartedDisplayed() {
        waitUtils.waitForElement(letsGetStartedText);
        return driver.findElement(letsGetStartedText).isDisplayed();
    }

    public void enterName(String name) {
        waitUtils.waitForElement(nameField);
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterAge(String age) {
        waitUtils.waitForElement(ageField);
        driver.findElement(ageField).sendKeys(age);
    }

    public void selectMale() {
        waitUtils.waitForElement(maleRadioButton);
        driver.findElement(maleRadioButton).click();
    }


    public void selectFemale() {
        waitUtils.waitForElement(femaleRadioButton);
        driver.findElement(femaleRadioButton).click();
    }


    public void clickContinue() {
        waitUtils.waitForElement(continueButton);
        driver.findElement(continueButton).click();
    }

    public static String generateMobileNumber() {
        Random rand = new Random();
        int[] digitCount = new int[10];
        StringBuilder number = new StringBuilder();

        int firstDigit = 2 + rand.nextInt(4); // 2,3,4,5
        number.append(firstDigit);
        digitCount[firstDigit]++;

        for (int pos = 1; pos < 10; pos++) {
            int digit;
            do {
                digit = rand.nextInt(10); // 0-9
            } while (digitCount[digit] >= 3);

            number.append(digit);
            digitCount[digit]++;
        }

        return number.toString();
    }

}
