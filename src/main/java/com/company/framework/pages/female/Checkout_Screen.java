package com.company.framework.pages.female;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class Checkout_Screen {

    private final WaitUtils waitUtils;

    public Checkout_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Checkout']")
    private WebElement CheckoutText;
    public boolean isCheckoutTextDisplayed() {
        return waitUtils.isElementDisplayed(CheckoutText);
    }
    public boolean isCheckoutTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(CheckoutText, timeout);
    }

    // Real account already has a saved address - confirmed live as a "Home" labeled, pre-selected
    // radio card. Distinct from "Add New Address" below (only relevant for a fresh account).
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Home']")
    private WebElement AlreadyPresentAddress;
    public boolean isAlreadyPresentAddressDisplayed() {
        return waitUtils.isElementDisplayed(AlreadyPresentAddress);
    }
    public boolean isAlreadyPresentAddressDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(AlreadyPresentAddress, timeout);
    }
    public void clickAlreadyPresentAddress() {
        waitUtils.waitForElement(AlreadyPresentAddress).click();
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Add New Address')]")
    private WebElement AddNewAddressButton;
    public boolean isAddNewAddressButtonDisplayed() {
        return waitUtils.isElementDisplayed(AddNewAddressButton);
    }
    public void clickAddNewAddressButton() {
        waitUtils.waitForElement(AddNewAddressButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Address Details']")
    private WebElement AddressDetailsText;
    public boolean isAddressDetailsTextDisplayed() {
        return waitUtils.isElementDisplayed(AddressDetailsText);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter Pincode']")
    private WebElement PincodeTextField;
    public boolean isPincodeTextFieldDisplayed() {
        return waitUtils.isElementDisplayed(PincodeTextField);
    }
    public void enterPincode(String pincode) {
        waitUtils.waitForElement(PincodeTextField).sendKeys(pincode);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter Locality']")
    private WebElement LocalityTextField;
    public boolean isLocalityTextFieldDisplayed() {
        return waitUtils.isElementDisplayed(LocalityTextField);
    }
    public void enterLocality(String locality) {
        waitUtils.waitForElement(LocalityTextField).sendKeys(locality);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter Address']")
    private WebElement AddressTextField;
    public boolean isAddressTextFieldDisplayed() {
        return waitUtils.isElementDisplayed(AddressTextField);
    }
    public void enterAddress(String address) {
        waitUtils.waitForElement(AddressTextField).sendKeys(address);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Work']")
    private WebElement WorkButton;
    public boolean isWorkButtonDisplayed() {
        return waitUtils.isElementDisplayed(WorkButton);
    }
    public void clickWorkButton() {
        waitUtils.waitForElement(WorkButton).click();
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Save and Pay')]")
    private WebElement SaveAndPayButton;
    public boolean isSaveAndPayButtonDisplayed() {
        return waitUtils.isElementDisplayed(SaveAndPayButton);
    }
    public void clickSaveAndPayButton() {
        waitUtils.waitForElement(SaveAndPayButton).click();
    }

    // Confirmed live: a plain TextView, not the ViewGroup content-desc style seen on some other
    // screens in this app.
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Proceed to Pay']")
    private WebElement ProceedToPayButton;
    public boolean isProceedToPayButtonDisplayed() {
        return waitUtils.isElementDisplayed(ProceedToPayButton);
    }
    public void clickProceedToPayButton() {
        waitUtils.waitForElement(ProceedToPayButton).click();
    }
}
