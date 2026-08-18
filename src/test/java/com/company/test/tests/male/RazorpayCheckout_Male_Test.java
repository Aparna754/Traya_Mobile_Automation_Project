package com.company.test.tests.male;

import com.company.framework.annotations.TestDescription;
import com.company.framework.driver.DriverManager;
import com.company.framework.listeners.TestListener;
import com.company.framework.pages.male.Assessment_Report_Screen;
import com.company.framework.pages.male.Checkout_Screen;
import com.company.framework.pages.male.HairTest_Screen;
import com.company.framework.pages.male.Lead_Screen;
import com.company.framework.pages.male.LoginAndDraftpage;
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
public class RazorpayCheckout_Male_Test extends BaseTest {

    String mobileNumber = "9334600822";
    String name = "Aman";
    String age = "25";
    String otp = "123789";

    private static final Duration QUICK_MOBILE_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration QUICK_WEB_TIMEOUT = Duration.ofSeconds(8);

    @Test(description = "Verify Razorpay checkout for lead user, COD order placement and cleanup via CRM Application", groups = {"regression"})
    @TestDescription("Verify the Razorpay checkout screen elements, complete a Cash on Delivery order in lead user, book a call, then cancel the order and slot booking via CRM Application")

    public void verifyRazorpayTrustedBusinessBadgeIsDisplayed() throws InterruptedException {

        LoginAndDraftpage loginPage = new LoginAndDraftpage(DriverManager.getDriver());
        Lead_Screen leadScreen = new Lead_Screen(DriverManager.getDriver());
        HairTest_Screen hairTestScreen = new HairTest_Screen(DriverManager.getDriver());
        Assessment_Report_Screen assessmentReportScreen = new Assessment_Report_Screen(DriverManager.getDriver());
        Checkout_Screen checkoutScreen = new Checkout_Screen(DriverManager.getDriver());
        Razorpay razorpay = new Razorpay(DriverManager.getDriver());
        ThankYou_Screen thankYouScreen = new ThankYou_Screen(DriverManager.getDriver());

        if(loginPage.isMobileNumberFieldDisplayed(QUICK_MOBILE_TIMEOUT)==true) {
            loginPage.enterMobileNumber(mobileNumber);
            loginPage.clickGetOTP();
        }
        if(loginPage.isLetsGetStartedDisplayed(QUICK_MOBILE_TIMEOUT)==true) {
            loginPage.enterName(name);
            loginPage.enterAge(age);
            loginPage.selectMale();
            loginPage.clickContinue();
        }
        else if(loginPage.isVerifyOTPTextDisplayed(QUICK_MOBILE_TIMEOUT)==true) {
            Assert.assertTrue(loginPage.isEnterTheOTPTextDisplayed(), "Enter the OTP text is not displayed");
            loginPage.enterOTP(otp);
            Thread.sleep(5000);
            loginPage.clickVerifyOTP();
            if (loginPage.isSkipButtonDisplayed(QUICK_MOBILE_TIMEOUT)==true) {
                loginPage.clickSkipButton();
            }
        }
        if(leadScreen.isKnowTheRootCauseOfYourHairLossTextDisplayed(QUICK_MOBILE_TIMEOUT)==true) {
            Assert.assertTrue(leadScreen.isTrayaHeroesTextDisplayed(), "Traya heroes text is not displayed");
            leadScreen.swipeUntilWhatCausesHairLossTextVisible();
            Assert.assertTrue(leadScreen.isWhatCausesHairLossTextDisplayed(), "What causes hair loss text is not displayed");
            leadScreen.swipeUntilHowTrayaWorksTextVisible();
            Assert.assertTrue(leadScreen.isHowTrayaWorksTextDisplayed(), "How Traya works text is not displayed");
            leadScreen.swipeUntilTrayaPlanIncludesTextVisible();
            Assert.assertTrue(leadScreen.isTrayaPlanIncludesTextDisplayed(), "Traya plan includes text is not displayed");
            leadScreen.swipeUntilNeedHelpTextVisible();
            Assert.assertTrue(leadScreen.isNeedHelpTextDisplayed(), "Need help text is not displayed");
            leadScreen.swipeUntilMeetOurTeamOfDoctorsTextVisible();
            Assert.assertTrue(leadScreen.isMeetOurTeamOfDoctorsTextDisplayed(), "Meet our team of doctors text is not displayed");
            leadScreen.swipeUntilGoogleReviewsAndRatingsTextVisible();
            Assert.assertTrue(leadScreen.isGoogleReviewsAndRatingsTextDisplayed(), "Google reviews and ratings text is not displayed");
            leadScreen.clickTakeTheHairTest() ;
        }
        if(leadScreen.isTestCompletedTodayTextDisplayed(QUICK_MOBILE_TIMEOUT)==true) {
            leadScreen.swipeUntilWhoMatchesYourProfileTextVisible();
            Assert.assertTrue(leadScreen.isWhoMatchesYourProfileTextDisplayed(), "Who matches your profile text is not displayed");
            leadScreen.swipeUntilTakeHairTestAgainTextVisible();
            Assert.assertTrue(leadScreen.isTakeHairTestAgainTextDisplayed(), "Take hair test again text is not displayed");
            leadScreen.clickTakeHairTestAgainText();
        }
        if(hairTestScreen.isHairLossTextDisplayed(QUICK_MOBILE_TIMEOUT)==true) {
            Assert.assertTrue(hairTestScreen.isWhichImageBestDescribesYourHairLossTextDisplayed(), "Which image best describes your hair loss text is not displayed");
            Assert.assertTrue(hairTestScreen.isStage1TextOptionDisplayed(), "Stage 1 text option is not displayed");
            hairTestScreen.clickStage1Option();
            Assert.assertTrue(hairTestScreen.isNoneTextOptionDisplayed(), "None text option is not displayed");
            hairTestScreen.clickNoneTextOption();
            Thread.sleep(2000);
            Assert.assertTrue(hairTestScreen.isNoneTextOptionDisplayed(), "None text option is not displayed");
            hairTestScreen.clickNoneTextOption();
            Assert.assertTrue(hairTestScreen.isSevereIllnessTextOptionDisplayed(), "Severe illness text option is not displayed");
            hairTestScreen.clickSevereIllnessTextOption();
            Assert.assertTrue(hairTestScreen.isNextButtonDisplayed(), "Next button is not displayed");
            hairTestScreen.clickNextButton();
            Assert.assertTrue(hairTestScreen.isNoTextOptionDisplayed(), "No text option is not displayed");
            hairTestScreen.clickNoTextOption();
            Assert.assertTrue(hairTestScreen.isVeryPeacefullyFor6To8HoursTextOptionDisplayed(), "Very peacefully for 6 to 8 hours text option is not displayed");
            hairTestScreen.clickVeryPeacefullyFor6To8HoursTextOption();
            Assert.assertTrue(hairTestScreen.isNoneTextOptionDisplayed(), "None text option is not displayed");
            hairTestScreen.clickNoneTextOption();
            Assert.assertTrue(hairTestScreen.isNoRarelyTextOptionDisplayed(), "No rarely text option is not displayed");
            hairTestScreen.clickNoRarelyTextOption();
            Assert.assertTrue(hairTestScreen.isYesTextOptionDisplayed(), "Yes text option is not displayed");
            hairTestScreen.clickYesTextOption();
            Assert.assertTrue(hairTestScreen.isAlwaysHighTextOptionDisplayed(), "Always high text option is not displayed");
            hairTestScreen.clickAlwaysHighTextOption();
            Assert.assertTrue(hairTestScreen.isNoTextOptionDisplayed(), "No text option is not displayed");
            hairTestScreen.clickNoTextOption();
            Assert.assertTrue(hairTestScreen.isNoneTextOptionDisplayed(), "None text option is not displayed");
            hairTestScreen.clickNoneTextOption();
            Assert.assertTrue(hairTestScreen.isTakeScalpPhotoButtonDisplayed(), "Take scalp photo button is not displayed");
            hairTestScreen.clickTakeScalpPhotoButton();
            if(hairTestScreen.isContinueButtonDisplayed(QUICK_MOBILE_TIMEOUT)==true) {
                hairTestScreen.clickContinueButton();
            }
            Assert.assertTrue(hairTestScreen.isImageCaptureButtonDisplayed(), "Image capture button is not displayed");
            hairTestScreen.clickImageCaptureButton();
            Assert.assertTrue(hairTestScreen.isTickImagebuttonDisplayed(), "Tick image button is not displayed");
            hairTestScreen.clickTickImagebutton();
            Assert.assertTrue(assessmentReportScreen.isAssessmentReportTextDisplayed(), "Assessment report text is not displayed");
            assessmentReportScreen.swipeUntilYourhairlossrootcausesTextVisible();
            Assert.assertTrue(assessmentReportScreen.isYourHairLossRootCausesTextDisplayed(), "Your hair loss root causes text is not displayed");
            assessmentReportScreen.swipeUntilWhoMatchesYourProfileTextVisible();
            Assert.assertTrue(assessmentReportScreen.isWhoMatchesYourProfileTextDisplayed(), "Who matches your profile text is not displayed");
            assessmentReportScreen.swipeUntilFreeAddOnsTextVisible();
            Assert.assertTrue(assessmentReportScreen.isFreeAddOnsTextDisplayed(), "Free add-ons text is not displayed");
            Assert.assertTrue(assessmentReportScreen.isBuyNowButtonDisplayed(), "Buy now button is not displayed");
            assessmentReportScreen.clickBuyNowButton();
            if(checkoutScreen.isAlreadyPresentAddressDisplayed(QUICK_MOBILE_TIMEOUT)==true) {
                checkoutScreen.clickAlreadyPresentAddress();
                Assert.assertTrue(checkoutScreen.isProceedToPayButtonDisplayed(), "Proceed to pay button is not displayed");
                checkoutScreen.clickProceedToPayButton();
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
                Assert.assertTrue(thankYouScreen.isBookACallButtonDisplayed(), "Book a call button is not displayed");
                thankYouScreen.clickBookACallButton();
                Assert.assertTrue(thankYouScreen.isCallBookedTextDisplayed(), "Call booked text is not displayed");
                Assert.assertTrue(thankYouScreen.isOkayButtonDisplayed(), "Okay button is not displayed");
                thankYouScreen.clickOkayButton();
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
}
