package com.company.test.tests.female;

import com.company.framework.annotations.TestDescription;
import com.company.framework.driver.DriverManager;
import com.company.framework.listeners.TestListener;
import com.company.framework.pages.female.Customer_Screen;
import com.company.framework.pages.female.LoginAndDraftpage;
import com.company.framework.pages.female.OrderSummary_Screen;
import com.company.framework.pages.female.Payment;
import com.company.framework.pages.female.ThankYou_Screen;
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
public class Checkout_Female_Test extends BaseTest {

    String mobileNumber = "8892257924";
    String otp = "123789";

    private static final Duration QUICK_MOBILE_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration QUICK_WEB_TIMEOUT = Duration.ofSeconds(8);
    private static final Duration DASHBOARD_LOAD_TIMEOUT = Duration.ofSeconds(60);
    // The Razorpay SDK's own "Loading payment methods..." network call - confirmed live to
    // occasionally take longer than a fixed sleep, so this screen's elements are polled for
    // rather than assumed ready after a Thread.sleep.
    private static final Duration PAYMENT_METHODS_LOAD_TIMEOUT = Duration.ofSeconds(20);

    @Test(description = "Verify checkout screen elements for a returning female customer, complete a Cash on Delivery order, book a call, then cancel the order and slot booking via CRM Application", groups = {"regression"})
    @TestDescription("Verify the checkout screen elements, complete a Cash on Delivery order in lead user, book a call, then cancel the order and slot booking via CRM Application")
    public void verifyRazorpayCheckoutCODOrderAndCleanup() throws InterruptedException {

        LoginAndDraftpage loginPage = new LoginAndDraftpage(DriverManager.getDriver());
        Customer_Screen customerScreen = new Customer_Screen(DriverManager.getDriver());
        OrderSummary_Screen orderSummaryScreen = new OrderSummary_Screen(DriverManager.getDriver());
        Payment payment = new Payment(DriverManager.getDriver());
        ThankYou_Screen thankYouScreen = new ThankYou_Screen(DriverManager.getDriver());

        if (loginPage.isMobileNumberFieldDisplayed(QUICK_MOBILE_TIMEOUT)==true) {
            loginPage.enterMobileNumber(mobileNumber);
            loginPage.clickGetOTP();
            Assert.assertTrue(loginPage.isEnterTheOTPTextDisplayed(), "Enter the OTP text is not displayed");
            loginPage.enterOTP(otp);
            Thread.sleep(5000);
            loginPage.clickVerifyOTP();
            if (loginPage.isSkipButtonDisplayed(QUICK_MOBILE_TIMEOUT)==true) {
                loginPage.clickSkipButton();
            }
        }

        // Confirmed live: this account is a returning customer (past onboarding, hair test already
        // completed) - so the checkout is reached via the "Buy Again" -> Order Summary -> Continue
        // path, landing on the standard Razorpay SDK "Payment Method" screen, rather than the
        // fresh-lead onboarding/hair-test path.
        if (customerScreen.isTodaysChecklistTextDisplayed(DASHBOARD_LOAD_TIMEOUT)) {
            Assert.assertTrue(customerScreen.isBuyAgainButtonDisplayed(), "Buy Again button is not displayed");
            customerScreen.clickBuyAgainButton();
        }

        if (orderSummaryScreen.isOrderSummaryTextDisplayed()==true) {
            // Recently-placed-order gate - shows a "PLACE ANOTHER ORDER" button instead of the
            // plan/product content when this account has ordered again too soon.
            if (orderSummaryScreen.isPlaceAnotherOrderButtonDisplayed(QUICK_MOBILE_TIMEOUT)) {
                orderSummaryScreen.clickPlaceAnotherOrderButton();
                Thread.sleep(3000);
            }
            orderSummaryScreen.swipeUpUntilTextVisible("Continue", 10);
            Assert.assertTrue(orderSummaryScreen.isContinueButtonDisplayed(), "Continue button is not displayed");
            orderSummaryScreen.clickContinueButton();
        }

        // --- Verify the checkout (Payment Method) screen elements ---
        Assert.assertTrue(payment.isPaymentMethodHeaderDisplayed(PAYMENT_METHODS_LOAD_TIMEOUT), "Payment Method header is not displayed");
        Assert.assertTrue(payment.isCardsHeaderDisplayed(), "Cards header is not displayed");
        Assert.assertTrue(payment.isPayOnDeliveryHeaderDisplayed(), "Pay on delivery header is not displayed");
        Assert.assertTrue(payment.isCashOnDeliveryRadioOptionDisplayed(QUICK_MOBILE_TIMEOUT), "Cash on delivery option is not displayed");
        Assert.assertTrue(payment.isNetBankingHeaderDisplayed(), "Net banking header is not displayed");

        // --- Complete a Cash on Delivery order ---
        payment.clickCashOnDeliveryRadioOption();
        Assert.assertTrue(payment.isProceedToPayButtonDisplayed(), "Proceed to Pay button is not displayed after selecting Cash on delivery");
        payment.clickProceedToPayButton();

        if (payment.isCodConfirmDialogDisplayed(QUICK_MOBILE_TIMEOUT)==true) {
            payment.clickContinueWithCODButton();
        }

        Assert.assertTrue(thankYouScreen.isThankYouTextDisplayed(), "'Order placed, thanks!' text is not displayed");
        Assert.assertTrue(thankYouScreen.isBookACallButtonDisplayed(), "Book A Call button is not displayed");
        thankYouScreen.clickBookACallButton();
        // Confirmed live: this sometimes books immediately with the account's last-used slot, and
        // sometimes instead reveals a language/date/time picker (pre-filled with defaults) with its
        // own "Book A Call" submit button at the bottom - if "Call Booked" hasn't shown up yet, a
        // second tap on the (now the picker's submit) button confirms the pre-filled defaults.
        if (!thankYouScreen.isCallBookedTextDisplayed(QUICK_MOBILE_TIMEOUT) && thankYouScreen.isBookACallButtonDisplayed()) {
            thankYouScreen.clickBookACallButton();
        }
        Assert.assertTrue(thankYouScreen.isCallBookedTextDisplayed(), "Call Booked text is not displayed");
        thankYouScreen.clickCloseCallBookedModal();

        // --- Cancel both the order and the booked call slot via the CRM (ERP) web panel ---
        WebDriver webDriver = Web_Chrome_SignIn.createSignedInChromeDriver();
        try {
            Login_Page loginPageWeb = new Login_Page(webDriver);
            Home_Page homePage = new Home_Page(webDriver);
            Customer_Discription_Page customerDiscriptionPage = new Customer_Discription_Page(webDriver);
            Web_Chrome_SignIn.navigateToWelcomePage(webDriver);

            if (loginPageWeb.isSignInWithGoogleDisplayed(QUICK_WEB_TIMEOUT)==true) {
                loginPageWeb.clickSignInWithGoogleButton();
                Thread.sleep(2000);
                if (homePage.isOkButtonDisplayed(QUICK_WEB_TIMEOUT)==true) {
                    homePage.clickOKButton();
                }
            }
            Assert.assertTrue(homePage.isSearchtextfieldIsDisplayed(), "Search Textfield is not displayed");
            homePage.clickSearchTextfield();
            Assert.assertTrue(homePage.isSearchPageTextFieldIsDisplayed(), "Search Page textfield is not displayed");
            homePage.clickSearchPageTextField();
            homePage.enterNumberInSearchPageTextField(mobileNumber);

            if (homePage.isPeopleNumberDisplayed(QUICK_WEB_TIMEOUT)==true) {
                homePage.clickPeopleNumber();
            }

            if (customerDiscriptionPage.isCanceledButtonDisplayed(QUICK_WEB_TIMEOUT)==true) {
                customerDiscriptionPage.clickCanceledButton();
                Thread.sleep(2000);
                Assert.assertTrue(customerDiscriptionPage.isSelectARemarkDisplayed(), "Select A Remark is not displayed");
                customerDiscriptionPage.clickSelectARemarkTextfiled();
                customerDiscriptionPage.enterRemark("TEST ORDER");
                Assert.assertTrue(customerDiscriptionPage.isConfirmCancellationButtonDisplayed(), "Confirm Cancellation Button is not displayed");
                customerDiscriptionPage.clickConfirmCancellationButton();
                Assert.assertTrue(customerDiscriptionPage.isSuccessfulMessageDisplayed(), "Automatic Order Cancellation Ticket Raise successfully Message is not displayed");
            }

            if (customerDiscriptionPage.isSlotBookingCloseButonDisplayed(QUICK_WEB_TIMEOUT)==true) {
                customerDiscriptionPage.clickSlotBookCloseButton();
                Assert.assertTrue(customerDiscriptionPage.isOtherOptionDisplayed(), "Please Enter the Reason for Slot Cancellation Other option is not displayed");
                customerDiscriptionPage.clickOtherOption();
                Assert.assertTrue(customerDiscriptionPage.isCanResizeTextFiledDisplayed(), "can resize textfield is not displayed");
                customerDiscriptionPage.enterText("TestOrder");
                Assert.assertTrue(customerDiscriptionPage.isConfirmCancelButonDisplayed(), "Confirm Cancel button is not displayed");
                customerDiscriptionPage.clickConfirmCancelButton();
                Assert.assertTrue(customerDiscriptionPage.isSlotCancelledMessageDisplayed(), "Slot Cancelled successfully message is not displayed");
            }
        } finally {
            webDriver.quit();
        }
    }
}
