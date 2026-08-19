package com.company.framework.pages.female;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

/**
 * "Order Summary" screen, reached via Customer_Screen's "Buy Again" bottom-nav tab - confirmed
 * live (mobile 8892257924) to be structurally the same generic order-summary UI as the male flow's
 * OrderSummary_Screen (Choose your plan, product line items with delete/quantity/plus, Products you
 * might like, Discount, Bill details), duplicated here per project convention. Two confirmed
 * differences from the male version are called out at their respective methods below.
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
    private WebElement OrderSummaryText;
    public boolean isOrderSummaryTextDisplayed() {
        return waitUtils.isElementDisplayed(OrderSummaryText);
    }

    // Intermediary gate shown instead of the plan/product content when an order was placed
    // recently ("You have recently placed an order #... Click on proceed to place another
    // order.") - confirmed live to reappear after repeated Buy Again runs on the same account,
    // same as the male flow's equivalent gate.
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='PLACE ANOTHER ORDER']")
    private WebElement PlaceAnotherOrderButton;
    public boolean isPlaceAnotherOrderButtonDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(PlaceAnotherOrderButton, timeout);
    }
    public void clickPlaceAnotherOrderButton() {
        waitUtils.waitForElement(PlaceAnotherOrderButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='1 MONTH']")
    private WebElement OneMonthPlanText;
    public boolean isOneMonthPlanDisplayed() {
        return waitUtils.isElementDisplayed(OneMonthPlanText);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'1 MONTH')]")
    private WebElement OneMonthPlanCard;
    public void clickOneMonthPlan() {
        waitUtils.waitForElement(OneMonthPlanCard).click();
    }

    // Bottom checkout-bar button - leads to the Razorpay SDK "Payment Method" screen.
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Continue']")
    private WebElement ContinueButton;
    public boolean isContinueButtonDisplayed() {
        return waitUtils.isElementDisplayed(ContinueButton);
    }
    public void clickContinueButton() {
        waitUtils.waitForElement(ContinueButton).click();
    }

    // Cart line-item cards expose their full details (name, size, dosage, description, MRP,
    // discounted price, quantity) as one content-desc on the card's outer ViewGroup, starting with
    // the product name - confirmed live, same convention as the male flow.
    public boolean isProductDisplayed(String productName) {
        return isProductDisplayed(productName, Duration.ofSeconds(20));
    }

    public boolean isProductDisplayed(String productName, Duration timeout) {
        try {
            WebElement product = driver.findElement(AppiumBy.xpath(cartCardXpath(productName)));
            return waitUtils.isElementDisplayed(product, timeout);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductQuantityDisplayed(String productName, String expectedQuantity) {
        try {
            WebElement quantity = driver.findElement(AppiumBy.xpath(
                    cartCardXpath(productName) + "//android.widget.TextView[@text='" + expectedQuantity + "']"));
            return waitUtils.isElementDisplayed(quantity);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDeleteIconDisplayed(String productName) {
        try {
            WebElement deleteIcon = driver.findElement(AppiumBy.xpath(cartCardClickableIconXpath(productName, 1)));
            return waitUtils.isElementDisplayed(deleteIcon);
        } catch (Exception e) {
            return false;
        }
    }
    public void clickDeleteIcon(String productName) {
        WebElement deleteIcon = driver.findElement(AppiumBy.xpath(cartCardClickableIconXpath(productName, 1)));
        waitUtils.waitForElement(deleteIcon).click();
    }

    public boolean isPlusIconDisplayed(String productName) {
        try {
            WebElement plusIcon = driver.findElement(AppiumBy.xpath(cartCardClickableIconXpath(productName, 2)));
            return waitUtils.isElementDisplayed(plusIcon);
        } catch (Exception e) {
            return false;
        }
    }

    private String cartCardXpath(String productName) {
        return "//android.view.ViewGroup[starts-with(@content-desc,'" + productName + ",')]";
    }
    private String cartCardClickableIconXpath(String productName, int index) {
        return cartCardXpath(productName) + "//android.view.ViewGroup[@clickable='true'][" + index + "]";
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Remove']")
    private WebElement RemoveConfirmationButton;
    public boolean isRemoveConfirmationButtonDisplayed() {
        return waitUtils.isElementDisplayed(RemoveConfirmationButton);
    }
    public void clickRemoveConfirmationButton() {
        waitUtils.waitForElement(RemoveConfirmationButton).click();
    }

    // "Products you might like" cards have no accessible heading text of their own (confirmed live
    // - unlike the male flow's plain "Products you might like" TextView, this section's title
    // renders without an a11y-exposed label here), so callers should scroll using the "Add To Cart"
    // text as the anchor instead of a section heading.
    //
    // Each recommendation card's own content-desc is "<size>, <ProductName>, <MRP>, <discPrice>"
    // (name is NOT the leading token here, unlike the cart cards above) - contains() is used rather
    // than starts-with() for that reason.
    //
    // CONFIRMED LIVE QUIRK: tapping ANY recommendation card's "Add To Cart" button always adds the
    // same fixed bonus item (observed: "Defence Conditioner", flagged "NEWLY ADDED" in its own
    // content-desc) regardless of which card's button was tapped - verified by tapping both the
    // "Nourish Hair Oil" and "Anti Dandruff Solution" cards' buttons and getting the identical
    // result both times. So re-adding a specific removed product from this section cannot be
    // verified by asserting that exact product's name reappears - isNewlyAddedFlagDisplayed() below
    // is the correct post-condition to check instead.
    public boolean isAddToCartButtonDisplayed(String productName) {
        try {
            WebElement addToCart = driver.findElement(AppiumBy.xpath(addToCartXpath(productName)));
            return waitUtils.isElementDisplayed(addToCart);
        } catch (Exception e) {
            return false;
        }
    }
    public void clickAddToCartButton(String productName) {
        WebElement addToCart = driver.findElement(AppiumBy.xpath(addToCartXpath(productName)));
        waitUtils.waitForElement(addToCart).click();
    }
    private String addToCartXpath(String productName) {
        return "//android.view.ViewGroup[contains(@content-desc,'" + productName + "')]"
                + "//android.view.ViewGroup[@content-desc='Add To Cart']";
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'NEWLY ADDED')]")
    private WebElement NewlyAddedFlag;
    public boolean isNewlyAddedFlagDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(NewlyAddedFlag, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Discount']")
    private WebElement DiscountHeaderText;
    public boolean isDiscountHeaderDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(DiscountHeaderText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'coins applied')]")
    private WebElement CoinsAppliedText;
    public boolean isCoinsAppliedTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(CoinsAppliedText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Coupon applied')]")
    private WebElement CouponAppliedText;
    public boolean isCouponAppliedTextDisplayed() {
        return waitUtils.isElementDisplayed(CouponAppliedText);
    }

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
    public boolean isCouponRemoveButtonDisplayed(boolean coinsAlsoApplied) {
        return isRemoveButtonDisplayedAt(coinsAlsoApplied ? 2 : 1);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Bill details']")
    private WebElement BillDetailsHeaderText;
    public boolean isBillDetailsHeaderDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(BillDetailsHeaderText, timeout);
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

    private String getSavedAmountTextAt(int index) {
        return driver.findElement(AppiumBy.xpath("(//android.widget.TextView[contains(@text,'You saved')])[" + index + "]")).getText();
    }
    public String getCoinsSavedAmountText() {
        return getSavedAmountTextAt(1);
    }
    public String getCouponSavedAmountText(boolean coinsAlsoApplied) {
        return getSavedAmountTextAt(coinsAlsoApplied ? 2 : 1);
    }

    public String getDiscountFromCoinsAmountText() {
        return driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Discount from coins']/following::android.widget.TextView[starts-with(@text,'-')][1]")).getText();
    }
    public String getDiscountFromCouponAmountText() {
        return driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Discount from coupon']/following::android.widget.TextView[starts-with(@text,'-')][1]")).getText();
    }

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
