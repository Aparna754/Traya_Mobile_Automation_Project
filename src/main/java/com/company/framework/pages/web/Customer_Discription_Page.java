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

    // Ant Design renders the actual remark <input> with opacity:0 - it's a real, interactable
    // keyboard target (typing into it is the correct way to filter/select an AntD option), but
    // it deliberately never passes a visibility check, so isDisplayed()/elementToBeClickable() on
    // it hang until they time out even once the dropdown is fully open and on-screen.
    // selectARemarkBox (the ".ant-select-selector" wrapper one level up) is the actual visible
    // control - use that for the isDisplayed/click checks, and only reach for the hidden input to
    // type into once the dropdown is already confirmed open.
    //
    // Anchored on the "Select a remark" placeholder text, NOT on the input's generated id
    // (previously hardcoded as "rc_select_0"): this ERP page also has an unrelated pagination
    // page-size <Select> elsewhere on the page, and AntD's id counter isn't guaranteed to hand
    // this remark dropdown id 0 - on a live page it was actually id "rc_select_1", with
    // "rc_select_0" belonging to that pagination control instead. A hardcoded id silently grabs
    // whichever Select happens to get that number, landing every click at the wrong screen
    // position (manifesting as an intermittent-looking ElementClickInterceptedException that no
    // amount of extra waiting fixes, since the target was simply wrong).
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

    @FindBy(xpath = "//button[@type='button']/span[text()='Confirm Cancellation']")
    private WebElement confirmCancellationButton;
    public boolean isConfirmCancellationButtonDisplayed() {
        return waitUtils.isElementDisplayed(confirmCancellationButton);
    }

    // Selenium's isEnabled() only reflects a real disabled attribute on a form control - a <span>
    // never reports disabled regardless of its parent button's state, so waiting on the span
    // above (via elementToBeClickable) returns immediately even while the actual button is still
    // disabled (per enterRemark()'s note: it stays disabled until Enter commits the selection).
    // Point at the button itself so the wait can see the real enabled state.
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
