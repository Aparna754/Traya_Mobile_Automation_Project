package com.company.framework.pages.male;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.company.framework.utils.SwipeUtils;
import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Checkout_Screen {
    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public Checkout_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Checkout']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement CheckoutText;
    public boolean isCheckoutTextDisplayed() {
        return waitUtils.isElementDisplayed(CheckoutText);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Add New Address')]")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement AddNewAddressButton;
    public boolean isAddNewAddressButtonDisplayed() {
        return waitUtils.isElementDisplayed(AddNewAddressButton);
    }
    public void clickAddNewAddressButton() {
        waitUtils.waitForElement(AddNewAddressButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Address Details']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement AddressDetailsText;
    public boolean isAddressDetailsTextDisplayed() {
        return waitUtils.isElementDisplayed(AddressDetailsText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Country/Region*']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement CountryRegionText;
    public boolean isCountryRegionTextDisplayed() {
        return waitUtils.isElementDisplayed(CountryRegionText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='House No., Building, Road, Area*']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement HouseNoBuildingRoadAreaText;
    public boolean isHouseNoBuildingRoadAreaTextDisplayed() {
        return waitUtils.isElementDisplayed(HouseNoBuildingRoadAreaText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Locality']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement LocalityText;
    public boolean isLocalityTextDisplayed() {
        return waitUtils.isElementDisplayed(LocalityText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Pincode*']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement PincodeText;
    public boolean isPincodeTextDisplayed() {
        return waitUtils.isElementDisplayed(PincodeText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='City*']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement CityText;
    public boolean isCityTextDisplayed() {
        return waitUtils.isElementDisplayed(CityText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='State*']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement StateText;
    public boolean isStateTextDisplayed() {
        return waitUtils.isElementDisplayed(StateText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Address Type']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement AddressTypeText;
    public boolean isAddressTypeTextDisplayed() {
        return waitUtils.isElementDisplayed(AddressTypeText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Home']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement HomeButton;
    public boolean isHomeButtonDisplayed() {
        return waitUtils.isElementDisplayed(HomeButton);
    }
    public void clickHomeButton() {
        waitUtils.waitForElement(HomeButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Work']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement WorkButton;
    public boolean isWorkButtonDisplayed() {
        return waitUtils.isElementDisplayed(WorkButton);
    }
    public void clickWorkButton() {
        waitUtils.waitForElement(WorkButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Other']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement OtherButton;
    public boolean isOtherButtonDisplayed() {
        return waitUtils.isElementDisplayed(OtherButton);
    }
    public void clickOtherButton() {
        waitUtils.waitForElement(OtherButton).click();
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'Save and Pay')]")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement SaveAndPayButton;
    public boolean isSaveAndPayButtonDisplayed() {
        return waitUtils.isElementDisplayed(SaveAndPayButton);
    }
    public void clickSaveAndPayButton() {
        waitUtils.waitForElement(SaveAndPayButton).click();
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'560078')]")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement AlreadyPresentAddress;
    public void swipeUntilAlreadyPresentAddress() {
        SwipeUtils.swipeUntilVisible(driver, AlreadyPresentAddress);
    }
    public boolean isAlreadyPresentAddressDisplayed() {
        return waitUtils.isElementDisplayed(AlreadyPresentAddress);
    }
    public void clickAlreadyPresentAddress() {
        waitUtils.waitForElement(AlreadyPresentAddress).click();
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter Pincode']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement PincodeTextField;
    public boolean isPincodeTextFieldDisplayed() {
        return waitUtils.isElementDisplayed(PincodeTextField);
    }
    public void enterPincode(String pincode) {
        waitUtils.waitForElement(PincodeTextField).sendKeys(pincode);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter Locality']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement LocalityTextField;
    public boolean isLocalityTextFieldDisplayed() {
        return waitUtils.isElementDisplayed(LocalityTextField);
    }
    public void enterLocality(String locality) {
        waitUtils.waitForElement(LocalityTextField).sendKeys(locality);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter Address']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement AddressTextField;
    public boolean isAddressTextFieldDisplayed() {
        return waitUtils.isElementDisplayed(AddressTextField);
    }
    public void enterAddress(String address) {
        waitUtils.waitForElement(AddressTextField).sendKeys(address);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Proceed to Pay']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ProceedToPayButton;
    public boolean isProceedToPayButtonDisplayed() {
        return waitUtils.isElementDisplayed(ProceedToPayButton);
    }
    public void clickProceedToPayButton() {
        waitUtils.waitForElement(ProceedToPayButton).click();
    }
    

}
