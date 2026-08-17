package com.company.framework.pages.male;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.time.Duration;
import java.util.List;

public class ThankYou_Screen {

    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public ThankYou_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order placed, thanks!']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ThankYouText;
    public boolean isThankYouTextDisplayed() {
        return waitUtils.isElementDisplayed(ThankYouText);
    }
    public boolean isThankYouTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(ThankYouText, timeout);
    }

     @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Book A Call']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement BookACallToggle_button;
    public boolean isBookACallToggle_buttonDisplayed() {
        return waitUtils.isElementDisplayed(BookACallToggle_button);
    }
    public void clickBookACallToggle_button() {
        waitUtils.waitForElement(BookACallToggle_button).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Book A Call']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement BookACallButton;
    public boolean isBookACallButtonDisplayed() {
        return waitUtils.isElementDisplayed(BookACallButton);
    }
    public void clickBookACallButton() {
        waitUtils.waitForElement(BookACallButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Call Booked']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement CallBookedText;
    public boolean isCallBookedTextDisplayed() {
        return waitUtils.isElementDisplayed(CallBookedText);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Okay']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OkayButton;
    public boolean isOkayButtonDisplayed() {
        return waitUtils.isElementDisplayed(OkayButton);
    }
    public void clickOkayButton() {
        waitUtils.waitForElement(OkayButton).click();
    }

    // Top-left back-navigation control - a pure SVG/icon-font glyph (empty text, resource-id
    // "iconIcon", clickable="false" on the node itself) at the very top of the viewport, right at
    // statBarHeight. Same situation as the recommendation card's "Add To Cart" text elsewhere in
    // this app - the node exposes no usable click target of its own, but the pixel position it's
    // drawn at is real and receives touch regardless, confirmed live via a genuine W3C tap.
    @AndroidFindBy(xpath = "(//*[@resource-id='iconIcon'])[1]")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement BackButton;
    public boolean isBackButtonDisplayed() {
        return waitUtils.isElementDisplayed(BackButton);
    }
    public boolean isBackButtonDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(BackButton, timeout);
    }
    public void clickBackButton() {
        waitUtils.waitForElement(BackButton);
        Rectangle rect = BackButton.getRect();
        w3cTap(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);
    }

    private void w3cTap(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 0);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(100)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(tap));
    }
}
