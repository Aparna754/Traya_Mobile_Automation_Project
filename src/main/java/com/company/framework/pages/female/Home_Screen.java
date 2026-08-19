package com.company.framework.pages.female;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

/**
 * Female home dashboard - for an account that has already completed the hair test, this shows a
 * "Hair Analysis Report" hero (Book A Call/Chat With Us, root-cause summary, customised plan)
 * rather than the male flow's "Today's Checklist". "Take Hair Test Again" sits further down the
 * page (confirmed live: requires several swipes past marketing content to reach).
 */
public class Home_Screen {

    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public Home_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Two observed labels for the same action, depending on account state: "Take Hair Test Again"
    // once a report exists, or "Complete The Hair Test™" if the account's latest attempt was
    // abandoned mid-quiz (confirmed live: exiting a retake partway through reverts to this label
    // instead of restoring the earlier completed report). Matched by exact text rather than
    // contains() - the bottom nav also has a plain "Hair Test" tab that a substring match would
    // false-positive on.
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Take Hair Test Again' or @text='Complete The Hair Test™']")
    private WebElement TakeHairTestAgainText;
    public boolean isTakeHairTestAgainDisplayed() {
        return waitUtils.isElementDisplayed(TakeHairTestAgainText);
    }
    public boolean isTakeHairTestAgainDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(TakeHairTestAgainText, timeout);
    }
    public void clickTakeHairTestAgain() {
        waitUtils.waitForElement(TakeHairTestAgainText).click();
    }

    // "Take Hair Test Again" is well below the fold - repeatedly swipe up until it reports
    // genuinely displayed rather than guessing a fixed swipe count (same rationale as
    // OrderSummary_Screen.swipeUpUntilTextVisible in the male package).
    public void swipeUntilTakeHairTestAgainVisible(int maxSwipes) {
        for (int i = 0; i < maxSwipes; i++) {
            if (isTakeHairTestAgainDisplayed(Duration.ofSeconds(2))) {
                return;
            }
            swipeUp();
        }
    }

    private void swipeUp() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = (int) (size.getHeight() * 0.75);
        int endY = (int) (size.getHeight() * 0.3);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(swipe));
    }
}
