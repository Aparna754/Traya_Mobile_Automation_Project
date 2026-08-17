package com.company.framework.pages.male;

import com.company.framework.utils.SwipeUtils;
import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

/**
 * Order Summary screen, reached via the "Buy Again" bottom nav tab - shows the most recent order
 * and offers to reorder it ("PLACE ANOTHER ORDER") or browse the full catalog ("View All
 * Products"), plus the plan-selection elements from the "Choose your plan" step that follows.
 */
public class OrderSummary_Screen {

    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public OrderSummary_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Summary']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OrderSummaryText;
    public boolean isOrderSummaryTextDisplayed() {
        return waitUtils.isElementDisplayed(OrderSummaryText);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='PLACE ANOTHER ORDER']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement PlaceAnotherOrderButton;
    public boolean isPlaceAnotherOrderButtonDisplayed() {
        return waitUtils.isElementDisplayed(PlaceAnotherOrderButton);
    }
    public boolean isPlaceAnotherOrderButtonDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(PlaceAnotherOrderButton, timeout);
    }
    public void clickPlaceAnotherOrderButton() {
        waitUtils.waitForElement(PlaceAnotherOrderButton).click();
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='View All Products']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ViewAllProductsButton;
    public boolean isViewAllProductsButtonDisplayed() {
        return waitUtils.isElementDisplayed(ViewAllProductsButton);
    }

    // --- "Choose your plan" checkout screen (reached via "Place Another Order") ---

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Choose your plan']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ChooseYourPlanText;
    public boolean isChooseYourPlanTextDisplayed() {
        return waitUtils.isElementDisplayed(ChooseYourPlanText);
    }

    // "3 MONTHS" plan card - text for the display check, and the clickable card container
    // (content-desc groups "3 MONTHS, <discount>%, <MRP>, <price>") for the click. contains(),
    // not exact match on content-desc - the MRP/discounted price are subject to the same kind of
    // run-to-run drift already seen on this screen's discount-days text.
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='3 MONTHS']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ThreeMonthsPlanText;
    public boolean isThreeMonthsPlanDisplayed() {
        return waitUtils.isElementDisplayed(ThreeMonthsPlanText);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'3 MONTHS')]")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ThreeMonthsPlanCard;
    public void clickThreeMonthsPlan() {
        waitUtils.waitForElement(ThreeMonthsPlanCard).click();
    }

    // Live-verified real text on this screen. contains(), not exact match - the day count in
    // "ordering in N days" is dynamic (seen both "40 days" and "45 days" across runs).
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Special Discount for ordering in')]")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement DiscountText;
    public boolean isDiscountTextDisplayed() {
        return waitUtils.isElementDisplayed(DiscountText);
    }
    public void swipeUntilDiscountTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, DiscountText);
    }

    // "1 MONTH" plan card - same structure as the "3 MONTHS" card above.
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='1 MONTH']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OneMonthPlanText;
    public boolean isOneMonthPlanDisplayed() {
        return waitUtils.isElementDisplayed(OneMonthPlanText);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'1 MONTH')]")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement OneMonthPlanCard;
    public void clickOneMonthPlan() {
        waitUtils.waitForElement(OneMonthPlanCard).click();
    }

    // --- Per-product row controls (product cards after selecting a plan) ---
    //
    // Each product's outer card is a single android.view.ViewGroup whose content-desc groups its
    // full label ("<name>, <size>, <frequency>, <MRP>, <price>, <qty>"), matched here with
    // starts-with() rather than contains() on purpose: once a product is removed, it reappears as
    // a "Products you might like" recommendation card whose content-desc puts the size FIRST and
    // the name second ("<size>, <name>, <MRP>, <price>[, Add To Cart]") - contains() would match
    // both simultaneously (confirmed live: it kept reporting a just-removed product as still
    // "displayed" because its recommendation card is real and present in the DOM), so anchoring
    // on the name being the very first token is what actually disambiguates "in my order" from
    // "recommended to me". Inside the main-list card there are exactly two clickable descendants
    // in document order: the quantity-decrement control (index 1 - this becomes the
    // delete/dustbin action once quantity is already 1, since decrementing further removes the
    // item) and the quantity-increment "+" control (index 2). Neither icon exposes text/
    // content-desc of its own (pure SVG), so they're only addressable by this relative position.

    public boolean isProductDisplayed(String productName) {
        return isProductDisplayed(productName, Duration.ofSeconds(20));
    }
    // WaitUtils.isElementDisplayed() only catches exceptions from the wait itself - it still
    // needs an already-resolved WebElement. driver.findElement() throws immediately (no wait,
    // no catch) when nothing matches at all, which is exactly the expected case right after a
    // product is removed - so that lookup needs its own try/catch here to behave as a genuine
    // boolean probe instead of throwing.
    public boolean isProductDisplayed(String productName, Duration timeout) {
        try {
            WebElement product = driver.findElement(AppiumBy.xpath(productCardXpath(productName)));
            return waitUtils.isElementDisplayed(product, timeout);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductQuantityDisplayed(String productName, String expectedQuantity) {
        try {
            WebElement quantity = driver.findElement(AppiumBy.xpath(
                    productCardXpath(productName) + "//android.widget.TextView[@text='" + expectedQuantity + "']"));
            return waitUtils.isElementDisplayed(quantity);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDeleteIconDisplayed(String productName) {
        try {
            WebElement deleteIcon = driver.findElement(AppiumBy.xpath(productClickableIconXpath(productName, 1)));
            return waitUtils.isElementDisplayed(deleteIcon);
        } catch (Exception e) {
            return false;
        }
    }
    public void clickDeleteIcon(String productName) {
        WebElement deleteIcon = driver.findElement(AppiumBy.xpath(productClickableIconXpath(productName, 1)));
        waitUtils.waitForElement(deleteIcon).click();
    }

    public boolean isPlusIconDisplayed(String productName) {
        try {
            WebElement plusIcon = driver.findElement(AppiumBy.xpath(productClickableIconXpath(productName, 2)));
            return waitUtils.isElementDisplayed(plusIcon);
        } catch (Exception e) {
            return false;
        }
    }

    private String productCardXpath(String productName) {
        return "//android.view.ViewGroup[starts-with(@content-desc,'" + productName + ",')]";
    }
    private String productClickableIconXpath(String productName, int index) {
        return productCardXpath(productName) + "//android.view.ViewGroup[@clickable='true'][" + index + "]";
    }

    // --- "Remove this item?" confirmation bottom sheet (opened by the delete icon above) ---

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Remove']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement RemoveConfirmationButton;
    public boolean isRemoveConfirmationButtonDisplayed() {
        return waitUtils.isElementDisplayed(RemoveConfirmationButton);
    }
    public void clickRemoveConfirmationButton() {
        waitUtils.waitForElement(RemoveConfirmationButton).click();
    }

    // --- "Products you might like" recommendation carousel (appears after removing an item) ---

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Products you might like']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ProductsYouMightLikeText;
    public boolean isProductsYouMightLikeTextDisplayed() {
        return waitUtils.isElementDisplayed(ProductsYouMightLikeText);
    }

    public boolean isAddToCartButtonDisplayed(String productName) {
        try {
            WebElement addToCart = driver.findElement(AppiumBy.xpath(addToCartXpath(productName)));
            return waitUtils.isElementDisplayed(addToCart);
        } catch (Exception e) {
            return false;
        }
    }
    // The recommendation card's outer container is clickable but tapping its geometric center
    // lands on the product image/description, not the button. Tapping an ancestor::[1] clickable
    // ViewGroup relative to the "Add To Cart" text was tried and didn't work either - Appium's
    // XPath engine likely doesn't resolve that reverse-axis predicate to the nearest ancestor the
    // way standard XPath does, so it silently lands on the wrong (outer) container instead of the
    // small wrapper around just that text. Tapping the "Add To Cart" text's own bounds directly is
    // what actually works, confirmed live via adb: real touch delivery is coordinate-based, not
    // gated by that specific node's own clickable="false" accessibility flag - the tap still
    // reaches whatever native view is really drawn at that point on screen. A plain
    // WebElement.click() and Appium's "mobile: clickGesture" were both silent no-ops here even at
    // the correct coordinates; only a genuine W3C touch sequence (matching what worked for
    // scrolling this same screen) actually registers.
    public void clickAddToCartButton(String productName) {
        WebElement addToCartText = driver.findElement(AppiumBy.xpath(addToCartXpath(productName)));
        waitUtils.waitForElement(addToCartText);
        org.openqa.selenium.Rectangle rect = addToCartText.getRect();
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
    // Independent of productCardXpath() on purpose: the recommendation card's content-desc puts
    // the size first and the product name second ("<size>, <name>, ..."), the opposite order of
    // the main-list card productCardXpath() matches - a plain contains() is fine here since only
    // a recommendation card ever contains "Add To Cart" as a descendant, so there's no risk of
    // this accidentally matching a main-list row.
    private String addToCartXpath(String productName) {
        return "//android.view.ViewGroup[contains(@content-desc,'" + productName + "')]"
                + "//android.widget.TextView[@text='Add To Cart']";
    }

    // --- "Saved ... with coins and coupon!" expandable row -> Discount / Bill details breakdown ---

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Saved') and contains(@text,'coins and coupon')]")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement SavedWithCoinsAndCouponRow;
    public boolean isSavedWithCoinsAndCouponRowDisplayed() {
        return waitUtils.isElementDisplayed(SavedWithCoinsAndCouponRow);
    }
    public void clickSavedWithCoinsAndCouponRow() {
        waitUtils.waitForElement(SavedWithCoinsAndCouponRow).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Discount']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement DiscountHeaderText;
    public boolean isDiscountHeaderDisplayed() {
        return waitUtils.isElementDisplayed(DiscountHeaderText);
    }
    public boolean isDiscountHeaderDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(DiscountHeaderText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'coins applied')]")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement CoinsAppliedText;
    public boolean isCoinsAppliedTextDisplayed() {
        return waitUtils.isElementDisplayed(CoinsAppliedText);
    }
    public boolean isCoinsAppliedTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(CoinsAppliedText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Coupon applied')]")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement CouponAppliedText;
    public boolean isCouponAppliedTextDisplayed() {
        return waitUtils.isElementDisplayed(CouponAppliedText);
    }

    // Coins' and coupon's own "Remove" buttons - indexed since both share the exact text "Remove"
    // (also reused, unambiguously, by the earlier delete-confirmation sheet, which is long closed
    // by the time this section is visible). Coins may not auto-apply at all if the account's coin
    // balance is too low (e.g. depleted by repeated COD-order+cancel runs, which don't refund
    // coins) - when that happens the coupon's own "Remove" is the ONLY one in the tree and shifts
    // from index 2 down to index 1, so callers that already know whether coins applied should use
    // the boolean-aware overload below instead of assuming index 2 always means "coupon".
    private boolean isRemoveButtonDisplayedAt(int index) {
        try {
            WebElement removeButton = driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text='Remove'])[" + index + "]"));
            return waitUtils.isElementDisplayed(removeButton);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isCoinsRemoveButtonDisplayed() {
        return isRemoveButtonDisplayedAt(1);
    }
    public boolean isCouponRemoveButtonDisplayed() {
        return isRemoveButtonDisplayedAt(2);
    }
    public boolean isCouponRemoveButtonDisplayed(boolean coinsAlsoApplied) {
        return isRemoveButtonDisplayedAt(coinsAlsoApplied ? 2 : 1);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Bill details']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement BillDetailsHeaderText;
    public boolean isBillDetailsHeaderDisplayed() {
        return waitUtils.isElementDisplayed(BillDetailsHeaderText);
    }
    public boolean isBillDetailsHeaderDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(BillDetailsHeaderText, timeout);
    }

    // The "Bill details" header can report displayed before its line items (Total MRP, Discount
    // from coins/coupon, Amount to be paid, ...) actually mount - confirmed live: a page-source
    // dump taken right after the header assertion passed (plus a 1s sleep) still had no
    // "Discount from coins" node anywhere in the tree at all, not just off-screen.
    // WaitUtils.isElementDisplayed() can't help here - it only polls an already-resolved
    // WebElement's visibility, and driver.findElement() throws immediately (no retry) when the
    // node doesn't exist yet at all. ExpectedConditions.visibilityOfElementLocated(By) is the one
    // that actually re-queries the locator on every poll, so it's the only thing here that can
    // wait through "hasn't mounted yet" rather than just "mounted but hidden".
    public boolean isDiscountFromCoinsLineItemDisplayed() {
        return isDiscountFromCoinsLineItemDisplayed(Duration.ofSeconds(20));
    }
    public boolean isDiscountFromCoinsLineItemDisplayed(Duration timeout) {
        try {
            new org.openqa.selenium.support.ui.WebDriverWait(driver, timeout)
                    .until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(
                            AppiumBy.xpath("//android.widget.TextView[@text='Discount from coins']")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // "You saved ₹N" appears twice when both coins and coupon are applied - coins' line first,
    // coupon's line second (document order). When coins didn't auto-apply (see the "Remove"
    // button comment above for why), coupon's is the only one left and shifts down to index 1 -
    // callers that already know whether coins applied should use the boolean-aware overload below.
    private String getSavedAmountTextAt(int index) {
        return driver.findElement(AppiumBy.xpath("(//android.widget.TextView[contains(@text,'You saved')])[" + index + "]")).getText();
    }
    public String getCoinsSavedAmountText() {
        return getSavedAmountTextAt(1);
    }
    public String getCouponSavedAmountText() {
        return getSavedAmountTextAt(2);
    }
    public String getCouponSavedAmountText(boolean coinsAlsoApplied) {
        return getSavedAmountTextAt(coinsAlsoApplied ? 2 : 1);
    }

    // "Discount from coins"/"Discount from coupon" are label TextViews followed by their "-₹N"
    // value. following:: rather than the stricter following-sibling:: - re-adding a product via
    // Add To Cart forces this whole row to re-render, and the label/value pairing wasn't always a
    // direct sibling relationship afterward (an intermediate row wrapper sometimes appears), so
    // this is scoped to "the next '-₹...' amount anywhere after the label" instead of assuming
    // exactly which structural level it sits at.
    public String getDiscountFromCoinsAmountText() {
        return driver.findElement(AppiumBy.xpath(
                "//android.widget.TextView[@text='Discount from coins']/following::android.widget.TextView[starts-with(@text,'-')][1]")).getText();
    }
    public String getDiscountFromCouponAmountText() {
        return driver.findElement(AppiumBy.xpath(
                "//android.widget.TextView[@text='Discount from coupon']/following::android.widget.TextView[starts-with(@text,'-')][1]")).getText();
    }

    // --- Reliable scroll for this screen ---
    //
    // isDisplayed() reports a false positive for elements on this screen well before they're
    // genuinely scrolled into view (confirmed live: a screenshot taken the instant isDisplayed()
    // returned true for an element still showed the very top of the screen). That silently
    // breaks SwipeUtils.swipeUntilVisible()'s stopping condition here specifically - it exits
    // immediately without ever scrolling. Appium's "mobile: scrollGesture" (what SwipeUtils uses)
    // was also confirmed to no-op entirely on this screen even called directly and repeatedly,
    // while a genuine touch sequence (adb shell input swipe, and this W3C Actions equivalent)
    // does scroll it. Use this instead of SwipeUtils for anything below the first screenful here.
    public void swipeUpRepeatedly(int times) {
        for (int i = 0; i < times; i++) {
            org.openqa.selenium.Dimension size = driver.manage().window().getSize();
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

    // A fixed swipe count is fragile (too few leaves the target above the fold, too many
    // overshoots past it - confirmed live: 3 swipes wasn't enough for isDisplayed() to see
    // "Products you might like", 5 swiped straight past it to the Discount section below).
    // isDisplayed() itself is reliable here once real scrolling has happened (unlike the
    // checklist screen elsewhere in this app) - so swipe one step at a time and stop as soon as
    // the target genuinely reports displayed, instead of guessing a single swipe count up front.
    public void swipeUpUntilTextVisible(String text, int maxSwipes) {
        for (int i = 0; i < maxSwipes; i++) {
            try {
                WebElement el = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + text + "']"));
                if (waitUtils.isElementDisplayed(el, Duration.ofSeconds(2))) {
                    return;
                }
            } catch (Exception e) {
                // not in the tree yet at all - keep swiping
            }
            swipeUpRepeatedly(1);
        }
    }
}
