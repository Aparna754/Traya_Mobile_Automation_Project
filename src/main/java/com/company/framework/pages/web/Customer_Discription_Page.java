package com.company.framework.pages.web;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.company.framework.utils.WebWaitUtils;

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
    public void clickCanceledButton() {
        waitUtils.waitUntilClickable(canceledButton).click();
    }

    // Ant Design renders the actual #rc_select_0 <input> with opacity:0 - it's a real,
    // interactable keyboard target (typing into it is the correct way to filter/select an
    // AntD option), but it deliberately never passes a visibility check, so isDisplayed()/
    // elementToBeClickable() on it hang until they time out even once the dropdown is fully
    // open and on-screen. selectARemarkBox (the ".ant-select-selector" wrapper one level up)
    // is the actual visible control - use that for the isDisplayed/click checks, and only
    // reach for the hidden input to type into once the dropdown is already confirmed open.
    //input[@id="rc_select_0"]---Select a remark --- TEST ORDER

    @FindBy(xpath = "//*[@id='rc_select_0']")
    private WebElement selectARemark;

    @FindBy(xpath = "//*[@id='rc_select_0']/ancestor::div[contains(@class,'ant-select-selector')]")
    private WebElement selectARemarkBox;

    public boolean isSelectARemarkDisplayed() {
        return waitUtils.isElementDisplayed(selectARemarkBox);
    }
    public void clickSelectARemarkTextfiled() {
        waitUtils.waitUntilClickable(selectARemarkBox).click();
    }
    /**
     * Typing alone only filters this AntD select's option list - it never commits a selection,
     * so the field stays empty and "Confirm Cancellation" stays disabled no matter how long you
     * wait. Enter selects the (now filtered-down-to-one) matching option, same as a real user
     * typing then pressing Enter to pick it.
     */
    public void enterRemark(String remark) {
        selectARemark.sendKeys(remark);
        selectARemark.sendKeys(Keys.ENTER);
    }

    //button[@type='button']/span[text()='Confirm Cancellation'] ---- Confirm Cancellation Button

    @FindBy(xpath = "//button[@type='button']/span[text()='Confirm Cancellation']")
    private WebElement confirmCancellationButton;
    public boolean isConfirmCancellationButtonDisplayed() {
        return waitUtils.isElementDisplayed(confirmCancellationButton);
    }
    public void clickConfirmCancellationButton() {
        waitUtils.waitUntilClickable(confirmCancellationButton).click();
    }

    //*[text()='Automatic Order Cancellation Ticket Raise successfully'] --- Automatic Order Cancellation Ticket Raise successfully message

    @FindBy(xpath = "//*[text()='Automatic Order Cancellation Ticket Raise successfully']")
    private WebElement successfulMessage;
    public boolean isSuccessfulMessageDisplayed() {
        return waitUtils.isElementDisplayed(successfulMessage);
    }

    //span[text()='SLOT BOOKING']/../../div[3]/div/button//span[@aria-label='close'] ---- SLOT BOOKING close button
    @FindBy(xpath = "//span[text()='SLOT BOOKING']/../../div[3]/div/button//span[@aria-label='close']")
    private WebElement slotBookingCloseButton;
    public boolean isSlotBookingCloseButonDisplayed() {
        return waitUtils.isElementDisplayed(slotBookingCloseButton);
    }
    public void clickSlotBookCloseButton() {
        waitUtils.forceClick(slotBookingCloseButton);
    }

    //span[text()='Others'] --- Please Enter the Reason for Slot Cancellation Other option

    @FindBy(xpath = "//span[text()='Others']")
    private WebElement otherOption;
    public boolean isOtherOptionDisplayed() {
        return waitUtils.isElementDisplayed(otherOption);
    }
    public void clickOtherOption() {
        waitUtils.waitUntilClickable(otherOption).click();
    }
    //textarea[@placeholder="can resize"] --- can resize textfield

     @FindBy(xpath = "//textarea[@placeholder='can resize']")
    private WebElement canResizeTextFiled;
    public boolean isCanResizeTextFiledDisplayed() {
        return waitUtils.isElementDisplayed(canResizeTextFiled);
    }
    public void enterText(String Text) {
        waitUtils.waitUntilClickable(canResizeTextFiled).sendKeys(Text);
    }

    //button[text()='Confirm Cancel'] --- Confirm Cancel button

    @FindBy(xpath = "//button[text()='Confirm Cancel']")
    private WebElement confirmCancelButton;
    public boolean isConfirmCancelButonDisplayed() {
        return waitUtils.isElementDisplayed(confirmCancelButton);
    }
    public void clickConfirmCancelButton() {
        waitUtils.waitUntilClickable(confirmCancelButton).click();
    }

    //*[text()='Slot Cancelled successfully'] --- Slot Cancelled successfully message
    @FindBy(xpath = "//*[text()='Slot Cancelled successfully']")
    private WebElement slotCancelledMessage;
    public boolean isSlotCancelledMessageDisplayed() {
        return waitUtils.isElementDisplayed(slotCancelledMessage);
    }

}
