package com.company.framework.pages.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.company.framework.utils.WebWaitUtils;
import java.time.Duration;
public class Home_Page {

    private final WebWaitUtils waitUtils;

    public Home_Page(WebDriver driver) {
        this.waitUtils = new WebWaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/div[2]/div/div/div[3]/button[text()='OK']")
    private WebElement OKButtonInWelcomePopup;
    public boolean isOkButtonDisplayed() {
        return waitUtils.isElementDisplayed(OKButtonInWelcomePopup);
    }
    public boolean isOkButtonDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(OKButtonInWelcomePopup, timeout);
    }
    public void clickOKButton() {
        waitUtils.waitUntilClickable(OKButtonInWelcomePopup).click();
    }

    @FindBy(xpath = "//*[@id='main']/div[2]/div/div[1]/div[1]/div/span[contains(text(),'Search pages, tabs')]")
    private WebElement SearchPageTextfield;
    public boolean isSearchtextfieldIsDisplayed() {
        return waitUtils.isElementDisplayed(SearchPageTextfield);
    }
    public boolean isSearchtextfieldIsDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(SearchPageTextfield, timeout);
    }
    public void clickSearchTextfield() {
        waitUtils.waitUntilClickable(SearchPageTextfield).click();
    }

    @FindBy(xpath = "//input[@class='spotlight-input']")
    private WebElement SearchTextfield;
    public boolean isSearchPageTextFieldIsDisplayed() {
        return waitUtils.isElementDisplayed(SearchTextfield);
    }
    public boolean isSearchPageTextFieldIsDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(SearchTextfield, timeout);
    }
    public void clickSearchPageTextField() {
        waitUtils.waitUntilClickable(SearchTextfield).click();
    }
    public void enterNumberInSearchPageTextField(String mobileNumber) {
        waitUtils.waitUntilClickable(SearchTextfield).sendKeys(mobileNumber);
    }

    @FindBy(xpath = "//div[@class='spotlight-item-text']/div")
    private WebElement peopleNumber;
    public boolean isPeopleNumberDisplayed() {
        return waitUtils.isElementDisplayed(peopleNumber);
    }
    public boolean isPeopleNumberDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(peopleNumber, timeout);
    }
    public void clickPeopleNumber() {
        waitUtils.waitUntilClickable(peopleNumber).click();
    }

}
