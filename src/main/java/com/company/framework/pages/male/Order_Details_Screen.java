package com.company.framework.pages.male;

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
 * "Order Summary" order-detail screen, reached by tapping an order card on
 * Order_History_Screen - a plain native screen (confirmed live, unlike OrderCancellation_Screen's
 * WebView) showing the order's status/dates, a 6-step tracking timeline (Delivered orders only -
 * Cancelled orders never reach a delivery date or show this timeline at all), tracking/share/
 * kit-issue/prescription/invoice actions, line items, and delivery address.
 *
 * Not to be confused with OrderSummary_Screen (cart/plan editing, reached via the "Buy Again" bottom
 * tab) or OrderCancellation_Screen (WebView-rendered, reached via the dashboard's "Your order is
 * placed/cancelled" banner) - both happen to share a similar "Order Summary" title, but this is a
 * different screen reached via a different entry point (hamburger menu -> Your Orders).
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
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OrderSummaryTitle;
    public boolean isOrderSummaryTitleDisplayed() {
        return waitUtils.isElementDisplayed(OrderSummaryTitle);
    }

    // --- Order status/dates header ---

    @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@text,'Order ID:')]")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OrderIdText;
    public boolean isOrderIdDisplayed() {
        return waitUtils.isElementDisplayed(OrderIdText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@text,'Order Status:')]")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OrderStatusText;
    public boolean isOrderStatusDisplayed() {
        return waitUtils.isElementDisplayed(OrderStatusText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@text,'Order Placed Date:')]")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OrderPlacedDateText;
    public boolean isOrderPlacedDateDisplayed() {
        return waitUtils.isElementDisplayed(OrderPlacedDateText);
    }

    // Delivered orders only - a Cancelled order's header has no delivery date line at all.
    @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@text,'Order Delivery Date:')]")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OrderDeliveryDateText;
    public boolean isOrderDeliveryDateDisplayed() {
        return waitUtils.isElementDisplayed(OrderDeliveryDateText);
    }

    // --- "Order Tracking" timeline (Delivered orders only) ---

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Tracking']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OrderTrackingHeading;
    public boolean isOrderTrackingHeadingDisplayed() {
        return waitUtils.isElementDisplayed(OrderTrackingHeading);
    }
    public boolean isOrderTrackingHeadingDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(OrderTrackingHeading, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Registered']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OrderRegisteredStep;
    public boolean isOrderRegisteredStepDisplayed() {
        return waitUtils.isElementDisplayed(OrderRegisteredStep);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Pickup Pending']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement PickupPendingStep;
    public boolean isPickupPendingStepDisplayed() {
        return waitUtils.isElementDisplayed(PickupPendingStep);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Picked Up']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement PickedUpStep;
    public boolean isPickedUpStepDisplayed() {
        return waitUtils.isElementDisplayed(PickedUpStep);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='In Transit']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement InTransitStep;
    public boolean isInTransitStepDisplayed() {
        return waitUtils.isElementDisplayed(InTransitStep);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Out For Delivery']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OutForDeliveryStep;
    public boolean isOutForDeliveryStepDisplayed() {
        return waitUtils.isElementDisplayed(OutForDeliveryStep);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Delivered']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement DeliveredStep;
    public boolean isDeliveredStepDisplayed() {
        return waitUtils.isElementDisplayed(DeliveredStep);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@text,'Tracking ID:')]")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement TrackingIdText;
    public boolean isTrackingIdDisplayed() {
        return waitUtils.isElementDisplayed(TrackingIdText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Open Tracking Link']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OpenTrackingLinkButton;
    public boolean isOpenTrackingLinkButtonDisplayed() {
        return waitUtils.isElementDisplayed(OpenTrackingLinkButton);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Share']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ShareButton;
    public boolean isShareButtonDisplayed() {
        return waitUtils.isElementDisplayed(ShareButton);
    }

    // --- Kit issue / prescription / invoice actions ---

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My Kit Has Issues']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement MyKitHasIssuesButton;
    public boolean isMyKitHasIssuesButtonDisplayed() {
        return waitUtils.isElementDisplayed(MyKitHasIssuesButton);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='View Prescription']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ViewPrescriptionButton;
    public boolean isViewPrescriptionButtonDisplayed() {
        return waitUtils.isElementDisplayed(ViewPrescriptionButton);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Download Invoice']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement DownloadInvoiceButton;
    public boolean isDownloadInvoiceButtonDisplayed() {
        return waitUtils.isElementDisplayed(DownloadInvoiceButton);
    }

    // --- Order line items / delivery address ---

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Details']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OrderDetailsHeading;
    public boolean isOrderDetailsHeadingDisplayed() {
        return waitUtils.isElementDisplayed(OrderDetailsHeading);
    }

    // Real text has a trailing space ("Delivery Address ") - contains() so that can't silently
    // break this.
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Delivery Address')]")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement DeliveryAddressHeading;
    public boolean isDeliveryAddressHeadingDisplayed() {
        return waitUtils.isElementDisplayed(DeliveryAddressHeading);
    }
    public boolean isDeliveryAddressHeadingDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(DeliveryAddressHeading, timeout);
    }

    // --- Scroll (a Delivered order's tracking timeline + actions push everything below the fold) ---
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

    // --- Leaving this screen ---
    //
    // Top-left back-navigation control is the same "iconIcon" icon-font glyph seen on
    // ThankYou_Screen (empty text, resource-id "iconIcon", clickable="false" on the node itself) -
    // exposes no usable click target of its own, so this uses the same proven W3C tap on its
    // resolved position rather than risk a silent no-op from a plain .click().
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='iconIcon']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
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
