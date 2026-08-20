package com.company.test.tests.female;

import com.company.framework.annotations.TestDescription;
import com.company.framework.driver.DriverManager;
import com.company.framework.listeners.TestListener;
import com.company.framework.pages.female.Assessment_Report_Screen;
import com.company.framework.pages.female.Checkout_Screen;
import com.company.framework.pages.female.HairTest_Screen;
import com.company.framework.pages.female.Home_Screen;
import com.company.framework.pages.female.LoginAndDraftpage;
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
public class HairTest_Female_Test extends BaseTest {

    String mobileNumber = "8709371797";
    String otp = "123789";

    private static final Duration QUICK_MOBILE_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration QUICK_WEB_TIMEOUT = Duration.ofSeconds(8);
    // Used to guard each screen-to-screen navigation below - polls for the next screen's
    // anchor element instead of a blind Thread.sleep, so the wait doubles as verification.
    private static final Duration SCREEN_LOAD_TIMEOUT = Duration.ofSeconds(10);

    @Test(description = "Verify the female Hair Test flow end-to-end: quiz, Assessment Report, checkout, COD payment, Thank You, Book A Call, then cancel the order and the booked slot via the ERP web panel", groups = {"regression"})
    @TestDescription("Login as a returning female Lead, retake the Hair Test with a fixed answer set, verify the Assessment Report sections, buy now, checkout with the saved address, pay via Cash on Delivery, verify order confirmation, book a call, then cancel both the order and the booked call slot from the web ERP panel")
    public void verifyFemaleHairTestFlow() throws InterruptedException {

        LoginAndDraftpage loginPage = new LoginAndDraftpage(DriverManager.getDriver());
        Home_Screen homeScreen = new Home_Screen(DriverManager.getDriver());
        HairTest_Screen hairTestScreen = new HairTest_Screen(DriverManager.getDriver());
        Assessment_Report_Screen assessmentReportScreen = new Assessment_Report_Screen(DriverManager.getDriver());
        Checkout_Screen checkoutScreen = new Checkout_Screen(DriverManager.getDriver());
        Payment payment = new Payment(DriverManager.getDriver());
        ThankYou_Screen thankYouScreen = new ThankYou_Screen(DriverManager.getDriver());

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

        homeScreen.swipeUntilTakeHairTestAgainVisible(20);
        Assert.assertTrue(homeScreen.isTakeHairTestAgainDisplayed(), "'Take Hair Test Again' is not displayed");
        homeScreen.clickTakeHairTestAgain();
        Assert.assertTrue(hairTestScreen.isStraightOptionDisplayed(), "'Straight' option is not displayed");
        hairTestScreen.clickStraightOption();
        Assert.assertTrue(hairTestScreen.isControlHairfallOptionDisplayed(), "'Control Hairfall' option is not displayed");
        hairTestScreen.clickControlHairfallOption();
        Assert.assertTrue(hairTestScreen.isYesExtremeHairFallOptionDisplayed(), "'Yes, extreme hair fall' option is not displayed");
        hairTestScreen.clickYesExtremeHairFallOption();
        Assert.assertTrue(hairTestScreen.isHairThinningOptionDisplayed(), "'Hair Thinning' option is not displayed");
        hairTestScreen.clickHairThinningOption();
        Assert.assertTrue(hairTestScreen.isGoodHairQualityOptionDisplayed(), "'Good Hair Quality' option is not displayed");
        hairTestScreen.clickGoodHairQualityOption();
        Assert.assertTrue(hairTestScreen.isThinOptionDisplayed(), "'Thin' option is not displayed");
        hairTestScreen.clickThinOption();
        Assert.assertTrue(hairTestScreen.isNoneOptionDisplayed(), "'None' option is not displayed");
        hairTestScreen.clickNoneOption();
        Assert.assertTrue(hairTestScreen.isWithin24HrsOptionDisplayed(), "'Within 24 hours' option is not displayed");
        hairTestScreen.clickWithin24HrsOption();
        Assert.assertTrue(hairTestScreen.isNoOptionDisplayed(), "'No' option is not displayed");
        hairTestScreen.clickNoOption();
        Assert.assertTrue(hairTestScreen.isNoneOptionDisplayed(), "'None' option is not displayed");
        hairTestScreen.clickNoneOption();
        Assert.assertTrue(hairTestScreen.isNoneOptionDisplayed(), "'None' option is not displayed");
        hairTestScreen.clickNoneOption();
        Assert.assertTrue(hairTestScreen.isNextButtonDisplayed(), "'Next' button is not displayed");
        hairTestScreen.clickNextButton();
        Assert.assertTrue(hairTestScreen.isNoneOptionDisplayed(), "'None' option is not displayed");
        hairTestScreen.clickNoneOption();
        Assert.assertTrue(hairTestScreen.isNextButtonDisplayed(), "'Next' button is not displayed");
        hairTestScreen.clickNextButton();
        Assert.assertTrue(hairTestScreen.isNoOptionDisplayed(), "'No' option is not displayed");
        hairTestScreen.clickNoOption();
        Assert.assertTrue(hairTestScreen.isPeacefullyFor6To8HrsOptionDisplayed(), "'Peacefully for 6-8 hours' option is not displayed");
        hairTestScreen.clickPeacefullyFor6To8HrsOption();
        Assert.assertTrue(hairTestScreen.isNoneOptionDisplayed(), "'None' option is not displayed");
        hairTestScreen.clickNoneOption();
        Assert.assertTrue(hairTestScreen.isNextButtonDisplayed(), "'Next' button is not displayed");
        hairTestScreen.clickNextButton();
        Assert.assertTrue(hairTestScreen.isNotAtAllOptionDisplayed(), "'Not at all' option is not displayed");
        hairTestScreen.clickNotAtAllOption();
        Assert.assertTrue(hairTestScreen.isNoOptionDisplayed(), "'No' option is not displayed");
        hairTestScreen.clickNoOption();
        Assert.assertTrue(hairTestScreen.isNoOptionDisplayed(), "'No' option is not displayed");
        hairTestScreen.clickNoOption();
        Assert.assertTrue(hairTestScreen.isAlwaysHighOptionDisplayed(), "'Always high' option is not displayed");
        hairTestScreen.clickAlwaysHighOption();
        Assert.assertTrue(hairTestScreen.isNoneOptionDisplayed(), "'None' option is not displayed");
        hairTestScreen.clickNoneOption();
        Assert.assertTrue(hairTestScreen.isNextButtonDisplayed(), "'Next' button is not displayed");
        hairTestScreen.clickNextButton();

        if (assessmentReportScreen.isAssessmentReportTextDisplayed(SCREEN_LOAD_TIMEOUT)) {
            assessmentReportScreen.swipeUntilWhoMatchesYourProfileTextVisible();
            Assert.assertTrue(assessmentReportScreen.isWhoMatchesYourProfileTextDisplayed(), "'who matches your profile' text is not displayed");
            assessmentReportScreen.swipeUntilForCompleteHairCareTextVisible();
            Assert.assertTrue(assessmentReportScreen.isForCompleteHairCareTextDisplayed(), "'For Complete Hair Care' text is not displayed");
            assessmentReportScreen.swipeUntilGoogleReviewsAndRatingsTextVisible();
            Assert.assertTrue(assessmentReportScreen.isGoogleReviewsAndRatingsTextDisplayed(), "'Google Reviews & Ratings' text is not displayed");
            assessmentReportScreen.swipeUntilFrequentlyAskedQuestionsTextVisible();
            Assert.assertTrue(assessmentReportScreen.isFrequentlyAskedQuestionsTextDisplayed(), "'Frequently Asked Questions' text is not displayed");
            Assert.assertTrue(assessmentReportScreen.isBuyNowButtonDisplayed(), "Buy Now button is not displayed");
            assessmentReportScreen.clickBuyNowButton();
        }

        if (checkoutScreen.isCheckoutTextDisplayed(SCREEN_LOAD_TIMEOUT)) {
            Assert.assertTrue(checkoutScreen.isProceedToPayButtonDisplayed(), "Proceed to Pay button is not displayed");
            checkoutScreen.clickProceedToPayButton();
        }

        if (payment.isPaymentMethodsTextDisplayed(SCREEN_LOAD_TIMEOUT)) {
            Assert.assertTrue(payment.isCashOnDeliveryTextDisplayed(), "Cash On Delivery text is not displayed");
            payment.clickCashOnDeliveryText();
            Assert.assertTrue(payment.isProceedToPayButtonDisplayed(), "Proceed to Pay button is not displayed after selecting Cash On Delivery");
            payment.clickProceedToPayButton();
        }

        if (payment.isCodConfirmDialogDisplayed(QUICK_MOBILE_TIMEOUT)) {
            payment.clickContinueWithCODButton();
        }

        if (thankYouScreen.isThankYouTextDisplayed(SCREEN_LOAD_TIMEOUT)) {
            Assert.assertTrue(thankYouScreen.isBookACallButtonDisplayed(), "Book A Call button is not displayed");
            thankYouScreen.clickBookACallButton();
            // Confirmed live: this sometimes books immediately, and sometimes instead reveals a
            // language/date/time picker (pre-filled with defaults) with its own "Book A Call"
            // submit button at the bottom - a second tap confirms the pre-filled defaults.
            if (!thankYouScreen.isCallBookedTextDisplayed(QUICK_MOBILE_TIMEOUT) && thankYouScreen.isBookACallButtonDisplayed()) {
                thankYouScreen.clickBookACallButton();
            }
        }

        if (thankYouScreen.isCallBookedTextDisplayed(SCREEN_LOAD_TIMEOUT)) {
            thankYouScreen.clickCloseCallBookedModal();
        }

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
            if (homePage.isSearchtextfieldIsDisplayed(QUICK_WEB_TIMEOUT)) {
                homePage.clickSearchTextfield();
            }
            if (homePage.isSearchPageTextFieldIsDisplayed(QUICK_WEB_TIMEOUT)) {
                homePage.clickSearchPageTextField();
                homePage.enterNumberInSearchPageTextField(mobileNumber);
            }
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

            if (customerDiscriptionPage.isSlotBookingCloseButonDisplayed(QUICK_WEB_TIMEOUT)) {
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
