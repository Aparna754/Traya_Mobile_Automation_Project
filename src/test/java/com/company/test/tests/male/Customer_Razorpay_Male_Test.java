package com.company.test.tests.male;

import com.company.framework.annotations.TestDescription;
import com.company.framework.driver.DriverManager;
import com.company.framework.listeners.TestListener;
import com.company.framework.pages.male.Customer_Screen;
import com.company.framework.pages.male.LoginAndDraftpage;
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
public class Customer_Razorpay_Male_Test extends BaseTest {

    String mobileNumber = "9711406757";
    String otp = "123789";

    private static final Duration QUICK_MOBILE_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration QUICK_WEB_TIMEOUT = Duration.ofSeconds(8);
    private static final Duration DASHBOARD_LOAD_TIMEOUT = Duration.ofSeconds(60);

    @Test(description = "Verify Male customer checklist home, reorder via Buy Again, Razorpay COD checkout and then Cancel the order from CRM", groups = {"regression"})
    @TestDescription("Login as a Male customer, verify the checklist home dashboard, place a reorder through Razorpay with Cash on Delivery, book a call, then cancel the order and slot booking via CRM Application")

    public void verifyReturningCustomerBuyAgainRazorpayCheckout() throws InterruptedException {

        LoginAndDraftpage loginPage = new LoginAndDraftpage(DriverManager.getDriver());
        Customer_Screen customerScreen = new Customer_Screen(DriverManager.getDriver());
        OrderSummary_Screen orderSummaryScreen = new OrderSummary_Screen(DriverManager.getDriver());
        Razorpay razorpay = new Razorpay(DriverManager.getDriver());
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
        if (customerScreen.isTodaysChecklistTextDisplayed(DASHBOARD_LOAD_TIMEOUT)==true) {

            Assert.assertTrue(customerScreen.isTodaysChecklistTextDisplayed(), "Today's Checklist text is not displayed");
            customerScreen.swipeUntilLogNowTextVisible();
            Assert.assertTrue(customerScreen.isLogNowTextDisplayed(), "Log Now text is not displayed");
            customerScreen.swipeUntilFreeAdvancedScalpTestTextVisible();
            Assert.assertTrue(customerScreen.isFreeAdvancedScalpTestTextDisplayed(), "Free Advanced Scalp Test text is not displayed");
            customerScreen.swipeUntilDietPlanTextVisible();
            Assert.assertTrue(customerScreen.isDietPlanTextDisplayed(), "Diet Plan text is not displayed");
            Assert.assertTrue(customerScreen.isBuyAgainButtonDisplayed(), "Buy Again button is not displayed");
            customerScreen.clickBuyAgainButton();
            Thread.sleep(3000);
            Assert.assertTrue(orderSummaryScreen.isOrderSummaryTextDisplayed(), "Order Summary text is not displayed");
            Assert.assertTrue(orderSummaryScreen.isPlaceAnotherOrderButtonDisplayed(), "Place another order button is not displayed");
            Assert.assertTrue(orderSummaryScreen.isViewAllProductsButtonDisplayed(), "View all products button is not displayed");
            orderSummaryScreen.clickPlaceAnotherOrderButton();
            Thread.sleep(3000);
            Assert.assertTrue(orderSummaryScreen.isChooseYourPlanTextDisplayed(), "Choose your plan text is not displayed");
            Assert.assertTrue(orderSummaryScreen.isThreeMonthsPlanDisplayed(), "3 Months plan is not displayed");
            orderSummaryScreen.clickThreeMonthsPlan();
            orderSummaryScreen.swipeUntilDiscountTextVisible();
            Assert.assertTrue(orderSummaryScreen.isDiscountTextDisplayed(), "Discount text is not displayed");
            customerScreen.swipeUntilInclusiveOfAllTaxesTextVisible();
            Assert.assertTrue(customerScreen.isInclusiveOfAllTaxesTextDisplayed(), "Inclusive of all taxes (bill details) text is not displayed");
            Assert.assertTrue(customerScreen.isCheckoutContinueButtonDisplayed(), "Continue button is not displayed");
            customerScreen.clickCheckoutContinueButton();
            Thread.sleep(5000);
            Assert.assertTrue(razorpay.isRazorpayTrustedBusinessTextDisplayed(), "Razorpay Trusted Business text is not displayed");
            Assert.assertTrue(razorpay.isPaymentOptionsTextDisplayed(), "Payment Options text is not displayed");
            Assert.assertTrue(razorpay.isAllPaymentOptionsTextDisplayed(), "All Payment Options text is not displayed");
            Assert.assertTrue(razorpay.isCardsTextDisplayed(), "Cards text is not displayed");
            Assert.assertTrue(razorpay.isCashOnDeliveryTextDisplayed(), "Cash on Delivery text is not displayed");
            Assert.assertTrue(razorpay.isNetbankingTextDisplayed(), "Netbanking text is not displayed");
            Assert.assertTrue(razorpay.isContinueButtonDisplayed(), "Continue button is not displayed");
            razorpay.clickCashOnDeliveryText();
            Thread.sleep(2000);
            Assert.assertTrue(razorpay.isContinueButtonDisplayed(), "Continue button is not displayed after selecting Cash on Delivery");
            razorpay.clickContinueButton();
            Thread.sleep(5000);
            Assert.assertTrue(razorpay.isSecuredByTextDisplayed(), "Secured by (Razorpay) text is not displayed");
            Assert.assertTrue(thankYouScreen.isThankYouTextDisplayed(), "Order placed, thanks! text is not displayed");
            Assert.assertTrue(thankYouScreen.isBookACallButtonDisplayed(), "Book a call button is not displayed");
            thankYouScreen.clickBookACallButton();
        }

        WebDriver webDriver = Web_Chrome_SignIn.createSignedInChromeDriver();
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
        webDriver.quit();
    }
}
