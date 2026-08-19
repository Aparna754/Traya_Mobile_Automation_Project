package com.company.framework.pages.female;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

/**
 * "Order Summary" detail screen, opened from an order card on Order_History_Screen - confirmed
 * live to share the male flow's field names/text for everything except the tracking-timeline
 * steps: this account's only "Delivered"-filtered order went through an RTO (Return To Origin)
 * journey rather than a plain final "Delivered" step, so the tracking steps below are named after
 * what's actually confirmed live (Order Registered / Pickup Pending / Picked Up / In Transit /
 * Out For Delivery / Rto Marked / Rto Intransit / Rto Delivered) rather than assumed from the male
 * package's plain "Delivered" step.
 */
public class Order_Details_Screen {

    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public Order_Details_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Summary']")
    private WebElement OrderSummaryTitle;
    public boolean isOrderSummaryTitleDisplayed() {
        return waitUtils.isElementDisplayed(OrderSummaryTitle);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@text,'Order ID:')]")
    private WebElement OrderIdText;
    public boolean isOrderIdDisplayed() {
        return waitUtils.isElementDisplayed(OrderIdText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@text,'Order Status:')]")
    private WebElement OrderStatusText;
    public boolean isOrderStatusDisplayed() {
        return waitUtils.isElementDisplayed(OrderStatusText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@text,'Order Placed Date:')]")
    private WebElement OrderPlacedDateText;
    public boolean isOrderPlacedDateDisplayed() {
        return waitUtils.isElementDisplayed(OrderPlacedDateText);
    }

    // Only present on a Delivered order - a Cancelled order has no delivery date (confirmed live).
    @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@text,'Order Delivery Date:')]")
    private WebElement OrderDeliveryDateText;
    public boolean isOrderDeliveryDateDisplayed() {
        return waitUtils.isElementDisplayed(OrderDeliveryDateText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Tracking']")
    private WebElement OrderTrackingHeading;
    public boolean isOrderTrackingHeadingDisplayed() {
        return waitUtils.isElementDisplayed(OrderTrackingHeading);
    }
    public boolean isOrderTrackingHeadingDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(OrderTrackingHeading, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Registered']")
    private WebElement OrderRegisteredStep;
    public boolean isOrderRegisteredStepDisplayed() {
        return waitUtils.isElementDisplayed(OrderRegisteredStep);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Pickup Pending']")
    private WebElement PickupPendingStep;
    public boolean isPickupPendingStepDisplayed() {
        return waitUtils.isElementDisplayed(PickupPendingStep);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Picked Up']")
    private WebElement PickedUpStep;
    public boolean isPickedUpStepDisplayed() {
        return waitUtils.isElementDisplayed(PickedUpStep);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='In Transit']")
    private WebElement InTransitStep;
    public boolean isInTransitStepDisplayed() {
        return waitUtils.isElementDisplayed(InTransitStep);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Out For Delivery']")
    private WebElement OutForDeliveryStep;
    public boolean isOutForDeliveryStepDisplayed() {
        return waitUtils.isElementDisplayed(OutForDeliveryStep);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Rto Marked']")
    private WebElement RtoMarkedStep;
    public boolean isRtoMarkedStepDisplayed() {
        return waitUtils.isElementDisplayed(RtoMarkedStep);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Rto Intransit']")
    private WebElement RtoIntransitStep;
    public boolean isRtoIntransitStepDisplayed() {
        return waitUtils.isElementDisplayed(RtoIntransitStep);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Rto Delivered']")
    private WebElement RtoDeliveredStep;
    public boolean isRtoDeliveredStepDisplayed() {
        return waitUtils.isElementDisplayed(RtoDeliveredStep);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@text,'Tracking ID:')]")
    private WebElement TrackingIdText;
    public boolean isTrackingIdDisplayed() {
        return waitUtils.isElementDisplayed(TrackingIdText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Open Tracking Link']")
    private WebElement OpenTrackingLinkButton;
    public boolean isOpenTrackingLinkButtonDisplayed() {
        return waitUtils.isElementDisplayed(OpenTrackingLinkButton);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Share']")
    private WebElement ShareButton;
    public boolean isShareButtonDisplayed() {
        return waitUtils.isElementDisplayed(ShareButton);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My Kit Has Issues']")
    private WebElement MyKitHasIssuesButton;
    public boolean isMyKitHasIssuesButtonDisplayed() {
        return waitUtils.isElementDisplayed(MyKitHasIssuesButton);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='View Prescription']")
    private WebElement ViewPrescriptionButton;
    public boolean isViewPrescriptionButtonDisplayed() {
        return waitUtils.isElementDisplayed(ViewPrescriptionButton);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Download Invoice']")
    private WebElement DownloadInvoiceButton;
    public boolean isDownloadInvoiceButtonDisplayed() {
        return waitUtils.isElementDisplayed(DownloadInvoiceButton);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Details']")
    private WebElement OrderDetailsHeading;
    public boolean isOrderDetailsHeadingDisplayed() {
        return waitUtils.isElementDisplayed(OrderDetailsHeading);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Delivery Address')]")
    private WebElement DeliveryAddressHeading;
    public boolean isDeliveryAddressHeadingDisplayed() {
        return waitUtils.isElementDisplayed(DeliveryAddressHeading);
    }
    public boolean isDeliveryAddressHeadingDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(DeliveryAddressHeading, timeout);
    }

    public void swipeUpRepeatedly(int times) {
        for (int i = 0; i < times; i++) {
            Dimension size = driver.manage().window().getSize();
            int startX = size.getWidth() / 2;
            int startY = (int) (size.getHeight() * 0.7);
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

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='iconIcon']")
    private WebElement BackButton;
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
