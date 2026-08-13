package com.company.framework.pages.web;

import com.company.framework.utils.WebWaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
public class Login_Page {

    private final WebWaitUtils waitUtils;

    public Login_Page(WebDriver driver) {
        this.waitUtils = new WebWaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Sign in with Google']")
    private WebElement SIGN_IN_WITH_GOOGLE_BUTTON;

    public boolean isSignInWithGoogleDisplayed() {
        return waitUtils.isElementDisplayed(SIGN_IN_WITH_GOOGLE_BUTTON);
    }
    public boolean isSignInWithGoogleDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(SIGN_IN_WITH_GOOGLE_BUTTON, timeout);
    }

    public void clickSignInWithGoogleButton() throws InterruptedException {
        waitUtils.waitUntilClickable(SIGN_IN_WITH_GOOGLE_BUTTON).click();
        Thread.sleep(2000);
    }
}
