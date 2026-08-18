package com.company.framework.pages.web;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.company.framework.utils.WebWaitUtils;
import java.time.Duration;
public class Customer_Discription_Page {

    private final WebWaitUtils waitUtils;

    public Customer_Discription_Page(WebDriver driver) {
        this.waitUtils = new WebWaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@id='cancelId']")
    private WebElement canceledButton;
    public boolean isCanceledButtonDisplayed() {
        return waitUtils.isElementDisplayed(canceledButton);
    }
    public boolean isCanceledButtonDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(canceledButton, timeout);
    }
    public void clickCanceledButton() {
        waitUtils.waitUntilClickable(canceledButton).click();
    }

    @FindBy(xpath = "//span[text()='Select a remark']/ancestor::div[contains(@class,'ant-select-selector')]//input")
    private WebElement selectARemark;

    @FindBy(xpath = "//span[text()='Select a remark']/ancestor::div[contains(@class,'ant-select-selector')]")
    private WebElement selectARemarkBox;

    public boolean isSelectARemarkDisplayed() {
        return waitUtils.isElementDisplayed(selectARemarkBox);
    }
    public void clickSelectARemarkTextfiled() {
        waitUtils.waitUntilClickable(selectARemarkBox).click();
    }
   
    public void enterRemark(String remark) {
        selectARemark.sendKeys(remark);
        selectARemark.sendKeys(Keys.ENTER);
    }

    @FindBy(xpath = "//button[@type='button']/span[text()='Confirm Cancellation']")
    private WebElement confirmCancellationButton;
    public boolean isConfirmCancellationButtonDisplayed() {
        return waitUtils.isElementDisplayed(confirmCancellationButton);
    }

    @FindBy(xpath = "//span[text()='Confirm Cancellation']/parent::button[@type='button']")
    private WebElement confirmCancellationButtonElement;
    public void clickConfirmCancellationButton() {
        waitUtils.waitUntilClickable(confirmCancellationButtonElement).click();
    }

    @FindBy(xpath = "//*[text()='Automatic Order Cancellation Ticket Raise successfully']")
    private WebElement successfulMessage;
    public boolean isSuccessfulMessageDisplayed() {
        return waitUtils.isElementDisplayed(successfulMessage);
    }

    @FindBy(xpath = "//span[text()='SLOT BOOKING']/../../div[3]/div/button//span[@aria-label='close']")
    private WebElement slotBookingCloseButton;
    public boolean isSlotBookingCloseButonDisplayed() {
        return waitUtils.isElementDisplayed(slotBookingCloseButton);
    }
    public boolean isSlotBookingCloseButonDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(slotBookingCloseButton, timeout);
    }
    public void clickSlotBookCloseButton() {
        waitUtils.forceClick(slotBookingCloseButton);
    }

    @FindBy(xpath = "//span[text()='Others']")
    private WebElement otherOption;
    public boolean isOtherOptionDisplayed() {
        return waitUtils.isElementDisplayed(otherOption);
    }
    public void clickOtherOption() {
        waitUtils.waitUntilClickable(otherOption).click();
    }

     @FindBy(xpath = "//textarea[@placeholder='can resize']")
    private WebElement canResizeTextFiled;
    public boolean isCanResizeTextFiledDisplayed() {
        return waitUtils.isElementDisplayed(canResizeTextFiled);
    }
    public void enterText(String Text) {
        waitUtils.waitUntilClickable(canResizeTextFiled).sendKeys(Text);
    }

    @FindBy(xpath = "//button[text()='Confirm Cancel']")
    private WebElement confirmCancelButton;
    public boolean isConfirmCancelButonDisplayed() {
        return waitUtils.isElementDisplayed(confirmCancelButton);
    }
    public void clickConfirmCancelButton() {
        waitUtils.waitUntilClickable(confirmCancelButton).click();
    }

    @FindBy(xpath = "//*[text()='Slot Cancelled successfully']")
    private WebElement slotCancelledMessage;
    public boolean isSlotCancelledMessageDisplayed() {
        return waitUtils.isElementDisplayed(slotCancelledMessage);
    }

}
