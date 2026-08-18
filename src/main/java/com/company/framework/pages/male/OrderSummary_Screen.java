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

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Choose your plan']")
//  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ChooseYourPlanText;
    public boolean isChooseYourPlanTextDisplayed() {
        return waitUtils.isElementDisplayed(ChooseYourPlanText);
    }

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

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Special Discount for ordering in')]")
//  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement DiscountText;
    public boolean isDiscountTextDisplayed() {
        return waitUtils.isElementDisplayed(DiscountText);
    }
    public void swipeUntilDiscountTextVisible() {
        SwipeUtils.swipeUntilVisible(driver, DiscountText);
    }

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

    public boolean isProductDisplayed(String productName) {
        return isProductDisplayed(productName, Duration.ofSeconds(20));
    }

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

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Remove']")
//  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement RemoveConfirmationButton;
    public boolean isRemoveConfirmationButtonDisplayed() {
        return waitUtils.isElementDisplayed(RemoveConfirmationButton);
    }
    public void clickRemoveConfirmationButton() {
        waitUtils.waitForElement(RemoveConfirmationButton).click();
    }

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

    private String addToCartXpath(String productName) {
        return "//android.view.ViewGroup[contains(@content-desc,'" + productName + "')]"
                + "//android.widget.TextView[@text='Add To Cart']";
    }

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
