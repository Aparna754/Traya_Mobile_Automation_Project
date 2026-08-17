package com.company.test.tests.male;

import com.company.framework.annotations.TestDescription;
import com.company.framework.driver.DriverManager;
import com.company.framework.listeners.TestListener;
import com.company.framework.pages.male.Customer_Screen;
import com.company.framework.pages.male.LoginAndDraftpage;
import com.company.framework.pages.male.OrderCancellation_Screen;
import com.company.framework.pages.male.OrderSummary_Screen;
import com.company.framework.pages.male.Razorpay;
import com.company.framework.pages.male.ThankYou_Screen;
import com.company.framework.pages.web.Customer_Discription_Page;
import com.company.framework.pages.web.Home_Page;
import com.company.framework.pages.web.Login_Page;
import com.company.framework.pages.web.Web_Chrome_SignIn;
import com.company.test.base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.time.Duration;

@Listeners(TestListener.class)
public class Customer_OrderSummary_Test extends BaseTest {

    String mobileNumber = "9711406757";
    String otp = "123789";

    private static final Duration QUICK_MOBILE_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration QUICK_WEB_TIMEOUT = Duration.ofSeconds(8);
    private static final Duration DASHBOARD_LOAD_TIMEOUT = Duration.ofSeconds(60);
    private static final String TARGET_PRODUCT = "Defence shampoo";
    private static final String[] ALL_PRODUCTS = {"Defence shampoo", "Hair Actives Serum", "Anti-Dandruff Shampoo", "Nourish Hair Oil", "Defence Conditioner"};

    @Test(description = "Verify 1 MONTH plan defaults every product to qty 1, remove/re-add a product, and cross-check the Coins/Coupon discount breakdown", groups = {"regression"})
    @TestDescription("Login as a returning customer, select the 1 MONTH plan, verify every product line item shows quantity 1, remove a product via its delete icon and confirm it disappears, re-add it from Products you might like, then verify the Bill details discount-from-coins/coupon amounts match the Coins/Coupon savings shown above")

    public void verifyOrderSummaryProductEditingAndDiscountBreakdown() throws InterruptedException {

        LoginAndDraftpage loginPage = new LoginAndDraftpage(DriverManager.getDriver());
        Customer_Screen customerScreen = new Customer_Screen(DriverManager.getDriver());
        OrderSummary_Screen orderSummaryScreen = new OrderSummary_Screen(DriverManager.getDriver());
        Razorpay razorpay = new Razorpay(DriverManager.getDriver());
        ThankYou_Screen thankYouScreen = new ThankYou_Screen(DriverManager.getDriver());
        OrderCancellation_Screen orderCancellationScreen = new OrderCancellation_Screen(DriverManager.getDriver());

        if (loginPage.isMobileNumberFieldDisplayed(QUICK_MOBILE_TIMEOUT)) {
            loginPage.enterMobileNumber(mobileNumber);
            loginPage.clickGetOTP();
            Assert.assertTrue(loginPage.isEnterTheOTPTextDisplayed(), "Enter the OTP text is not displayed");
            loginPage.enterOTP(otp);
            Thread.sleep(5000);
            loginPage.clickVerifyOTP();
            if (loginPage.isSkipButtonDisplayed(QUICK_MOBILE_TIMEOUT)) {
                loginPage.clickSkipButton();
            }
        }

        Assert.assertTrue(customerScreen.isTodaysChecklistTextDisplayed(DASHBOARD_LOAD_TIMEOUT), "Today's Checklist text is not displayed");
        Assert.assertTrue(customerScreen.isBuyAgainButtonDisplayed(), "Buy Again button is not displayed");
        customerScreen.clickBuyAgainButton();
        Thread.sleep(3000);
        Assert.assertTrue(orderSummaryScreen.isOrderSummaryTextDisplayed(), "Order Summary text is not displayed");
        if (orderSummaryScreen.isPlaceAnotherOrderButtonDisplayed(QUICK_MOBILE_TIMEOUT)==true) {
            orderSummaryScreen.clickPlaceAnotherOrderButton();
            Thread.sleep(3000);
        }
        Assert.assertTrue(orderSummaryScreen.isOrderSummaryTextDisplayed(), "Order Summary text is not displayed");
        Assert.assertTrue(orderSummaryScreen.isOneMonthPlanDisplayed(), "1 MONTH plan is not displayed");
        orderSummaryScreen.clickOneMonthPlan();
        Thread.sleep(2000);
        for (String product : ALL_PRODUCTS) {
            Assert.assertTrue(orderSummaryScreen.isProductQuantityDisplayed(product, "1"), product + " does not show quantity 1");
        }
        Assert.assertTrue(orderSummaryScreen.isDeleteIconDisplayed(TARGET_PRODUCT), "Delete (dustbin) icon is not displayed for " + TARGET_PRODUCT);
        Assert.assertTrue(orderSummaryScreen.isPlusIconDisplayed(TARGET_PRODUCT), "'+' icon is not displayed for " + TARGET_PRODUCT);
        orderSummaryScreen.clickDeleteIcon(TARGET_PRODUCT);
        Thread.sleep(1500);
        Assert.assertTrue(orderSummaryScreen.isRemoveConfirmationButtonDisplayed(), "Remove confirmation button is not displayed");
        orderSummaryScreen.clickRemoveConfirmationButton();
        Thread.sleep(2000);
        Assert.assertFalse(orderSummaryScreen.isProductDisplayed(TARGET_PRODUCT, QUICK_MOBILE_TIMEOUT), TARGET_PRODUCT + " is still displayed after removal");
        orderSummaryScreen.swipeUpUntilTextVisible("Products you might like", 8);
        Assert.assertTrue(orderSummaryScreen.isProductsYouMightLikeTextDisplayed(), "Products you might like text is not displayed");
        orderSummaryScreen.swipeUpUntilTextVisible("Add To Cart", 4);
        Assert.assertTrue(orderSummaryScreen.isAddToCartButtonDisplayed(TARGET_PRODUCT), "Add To Cart button is not displayed for " + TARGET_PRODUCT);
        orderSummaryScreen.swipeUpRepeatedly(1);
        boolean reappeared = false;
        for (int attempt = 1; attempt <= 3 && !reappeared; attempt++) {
            orderSummaryScreen.clickAddToCartButton(TARGET_PRODUCT);
            Thread.sleep(2000);
            reappeared = orderSummaryScreen.isProductDisplayed(TARGET_PRODUCT, QUICK_MOBILE_TIMEOUT);
        }
        Assert.assertTrue(reappeared, TARGET_PRODUCT + " did not reappear in the product list after Add To Cart");
        orderSummaryScreen.swipeUpUntilTextVisible("Discount", 6);
        Assert.assertTrue(orderSummaryScreen.isDiscountHeaderDisplayed(QUICK_MOBILE_TIMEOUT), "Discount header is not displayed");
        boolean coinsApplied = orderSummaryScreen.isCoinsAppliedTextDisplayed(QUICK_MOBILE_TIMEOUT);
        if (coinsApplied) {
            Assert.assertTrue(orderSummaryScreen.isCoinsRemoveButtonDisplayed(), "Coins Remove button is not displayed");
        }
        Assert.assertTrue(orderSummaryScreen.isCouponAppliedTextDisplayed(), "Coupon applied text is not displayed");
        Assert.assertTrue(orderSummaryScreen.isCouponRemoveButtonDisplayed(coinsApplied), "Coupon Remove button is not displayed");
        orderSummaryScreen.swipeUpUntilTextVisible("Bill details", 6);
        Assert.assertTrue(orderSummaryScreen.isBillDetailsHeaderDisplayed(QUICK_MOBILE_TIMEOUT), "Bill details header is not displayed");
        if (coinsApplied) {
            orderSummaryScreen.swipeUpUntilTextVisible("Discount from coins", 4);
            Assert.assertTrue(orderSummaryScreen.isDiscountFromCoinsLineItemDisplayed(QUICK_MOBILE_TIMEOUT), "'Discount from coins' line item is not displayed");
            String coinsSavedAmount = extractAmount(orderSummaryScreen.getCoinsSavedAmountText());
            String discountFromCoinsAmount = extractAmount(orderSummaryScreen.getDiscountFromCoinsAmountText());
            Assert.assertEquals(discountFromCoinsAmount, coinsSavedAmount,"Bill details 'Discount from coins' (" + discountFromCoinsAmount + ") does not match the Coins savings shown above (" + coinsSavedAmount + ")");
        }
        String couponSavedAmount = extractAmount(orderSummaryScreen.getCouponSavedAmountText(coinsApplied));
        String discountFromCouponAmount = extractAmount(orderSummaryScreen.getDiscountFromCouponAmountText());
        Assert.assertEquals(discountFromCouponAmount, couponSavedAmount,"Bill details 'Discount from coupon' (" + discountFromCouponAmount + ") does not match the Coupon savings shown above (" + couponSavedAmount + ")");
        Assert.assertTrue(customerScreen.isCheckoutContinueButtonDisplayed(), "Continue button is not displayed");
        customerScreen.clickCheckoutContinueButton();
        Thread.sleep(5000);
        Assert.assertTrue(razorpay.isRazorpayTrustedBusinessTextDisplayed(), "Razorpay Trusted Business text is not displayed");
        Assert.assertTrue(razorpay.isCashOnDeliveryTextDisplayed(), "Cash on Delivery text is not displayed");
        razorpay.clickCashOnDeliveryText();
        Thread.sleep(2000);
        Assert.assertTrue(razorpay.isContinueButtonDisplayed(), "Continue button is not displayed after selecting Cash on Delivery");
        razorpay.clickContinueButton();
        if (razorpay.isCodConfirmDialogDisplayed(Duration.ofSeconds(6))) {
            razorpay.clickContinueWithCODButton();
        }
        Assert.assertTrue(razorpay.isOrderConfirmedTextDisplayed(Duration.ofSeconds(20)), "Order Confirmed (payment) screen is not displayed");
        if (thankYouScreen.isThankYouTextDisplayed(QUICK_MOBILE_TIMEOUT)) {
            thankYouScreen.clickBackButton();
        } else {
            Assert.assertTrue(thankYouScreen.isBackButtonDisplayed(QUICK_MOBILE_TIMEOUT), "Neither 'Order placed, thanks!' text nor the Go Back button is displayed");
            thankYouScreen.clickBackButton();
        }
        Thread.sleep(3000);
        if (!customerScreen.isYourOrderIsPlacedTextDisplayed(QUICK_MOBILE_TIMEOUT) && customerScreen.isDismissBannerButtonDisplayed(QUICK_MOBILE_TIMEOUT)) {
            customerScreen.clickDismissBannerButton();
            Thread.sleep(2000);
        }
        customerScreen.refreshScreen();
        Assert.assertTrue(customerScreen.isYourOrderIsPlacedTextDisplayed(Duration.ofSeconds(15)), "'Your order is placed' text is not displayed");
        customerScreen.clickYourOrderIsPlacedText();
        Thread.sleep(3000);
        Assert.assertTrue(orderCancellationScreen.isCancelOrderButtonDisplayed(), "Cancel Order button is not displayed");
        orderCancellationScreen.clickCancelOrderButton();
        Thread.sleep(2000);
        Assert.assertTrue(orderCancellationScreen.isForgotToApplyDiscountOrCoinsReasonDisplayed(), "'Forgot to apply discount/coins' reason option is not displayed");
        orderCancellationScreen.clickForgotToApplyDiscountOrCoinsReason();
        Thread.sleep(1000);
        Assert.assertTrue(orderCancellationScreen.isConfirmCancelOrderButtonDisplayed(), "Confirm Cancel Order button is not displayed");
        orderCancellationScreen.clickConfirmCancelOrderButton();
        Assert.assertTrue(orderCancellationScreen.isOrderCancelSuccessMessageDisplayed(Duration.ofSeconds(10)), "'Order Cancel Requested Successfully' message is not displayed");
        orderCancellationScreen.goBack();
        customerScreen.refreshScreen();
        Thread.sleep(3000);
        if (customerScreen.isYourOrderIsCancelledTextDisplayed(Duration.ofSeconds(10))==false) {
            cancelOrderViaErp();
        }
    }
    private void cancelOrderViaErp() throws InterruptedException {
        WebDriver webDriver = Web_Chrome_SignIn.createSignedInChromeDriver();
        try {
            Login_Page loginPageWeb = new Login_Page(webDriver);
            Home_Page homePage = new Home_Page(webDriver);
            Customer_Discription_Page customerDiscriptionPage = new Customer_Discription_Page(webDriver);
            Web_Chrome_SignIn.navigateToWelcomePage(webDriver);

            if (loginPageWeb.isSignInWithGoogleDisplayed(QUICK_WEB_TIMEOUT)) {
                loginPageWeb.clickSignInWithGoogleButton();
                Thread.sleep(2000);
                if (homePage.isOkButtonDisplayed(QUICK_WEB_TIMEOUT)) {
                    homePage.clickOKButton();
                }
            }
            Assert.assertTrue(homePage.isSearchtextfieldIsDisplayed(), "Search Textfield is not displayed");
            homePage.clickSearchTextfield();
            Assert.assertTrue(homePage.isSearchPageTextFieldIsDisplayed(), "Search Page textfield is not displayed");
            homePage.clickSearchPageTextField();
            homePage.enterNumberInSearchPageTextField(mobileNumber);

            if (homePage.isPeopleNumberDisplayed(QUICK_WEB_TIMEOUT)) {
                homePage.clickPeopleNumber();
            }

            if (customerDiscriptionPage.isCanceledButtonDisplayed(QUICK_WEB_TIMEOUT)) {
                customerDiscriptionPage.clickCanceledButton();
                Thread.sleep(2000);
                Assert.assertTrue(customerDiscriptionPage.isSelectARemarkDisplayed(), "Select A Remark is not displayed");
                customerDiscriptionPage.clickSelectARemarkTextfiled();
                customerDiscriptionPage.enterRemark("TEST ORDER");
                Assert.assertTrue(customerDiscriptionPage.isConfirmCancellationButtonDisplayed(), "Confirm Cancellation Button is not displayed");
                customerDiscriptionPage.clickConfirmCancellationButton();
                Assert.assertTrue(customerDiscriptionPage.isSuccessfulMessageDisplayed(), "Automatic Order Cancellation Ticket Raise successfully Message is not displayed");
            }
        } finally {
            webDriver.quit();
        }
    }
    private String extractAmount(String text) {
        return text.replaceAll("[^0-9.]", "");
    }
}
