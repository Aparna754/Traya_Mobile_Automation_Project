package com.company.test.tests.male;

import com.company.framework.annotations.TestDescription;
import com.company.framework.driver.DriverManager;
import com.company.framework.listeners.TestListener;
import com.company.framework.pages.male.Assessment_Report_Screen;
import com.company.framework.pages.male.Checkout_Screen;
import com.company.framework.pages.male.Customer_Hamberger_Screen;
import com.company.framework.pages.male.Customer_Screen;
import com.company.framework.pages.male.HairTest_Screen;
import com.company.framework.pages.male.Lead_Screen;
//import com.company.framework.pages.male.Lead_You_Screen;
import com.company.framework.pages.male.LoginAndDraftpage;
import com.company.framework.pages.male.Payment_Screen;
import com.company.framework.pages.male.ThankYou_Screen;
import com.company.framework.pages.web.Customer_Discription_Page;
import com.company.framework.pages.web.Home_Page;
import com.company.framework.pages.web.Login_Page;
import com.company.framework.pages.web.Web_Chrome_SignIn;
import com.company.framework.stepgroups.ClickAtCoordinates;
import com.company.test.base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class HairTest_Stage3_Male_Test extends BaseTest {

    String mobileNumber = "9334600822";
    String name = "Aman";
    String age = "25";
    String otp = "123789";
    String pincode = "560078";
    String location = "JP Nagar";
    String address = "Test Building 1234";

    @Test(description = "Verify Male User Can Fill All Details With Stage3", groups = {"regression"})
    @TestDescription("Verify the user can fill all the details with stage3 and proceed to payment")

    public void verifyMaleUserCanFillAllDetailsWithStage3() throws InterruptedException {

        ClickAtCoordinates clickAtCoordinates = new ClickAtCoordinates();
        LoginAndDraftpage loginPage = new LoginAndDraftpage(DriverManager.getDriver());
        Lead_Screen leadScreen = new Lead_Screen(DriverManager.getDriver());
        HairTest_Screen hairTestScreen = new HairTest_Screen(DriverManager.getDriver());
        Assessment_Report_Screen assessmentReportScreen = new Assessment_Report_Screen(DriverManager.getDriver());
        Checkout_Screen checkoutScreen = new Checkout_Screen(DriverManager.getDriver());
        Payment_Screen paymentScreen = new Payment_Screen(DriverManager.getDriver());
        ThankYou_Screen thankYouScreen = new ThankYou_Screen(DriverManager.getDriver());
        Customer_Screen customerScreen = new Customer_Screen(DriverManager.getDriver());
        Customer_Hamberger_Screen customerHambergerScreen = new Customer_Hamberger_Screen(DriverManager.getDriver());
      //  Lead_You_Screen leadYouScreen = new Lead_You_Screen(DriverManager.getDriver());
        


        if(loginPage.isMobileNumberFieldDisplayed()==true) {
            Assert.assertTrue(loginPage.isMobileNumberFieldDisplayed(), "Mobile number field is not displayed");
            loginPage.enterMobileNumber(mobileNumber);
            loginPage.clickGetOTP();  
        }

        if(loginPage.isLetsGetStartedDisplayed()==true) {
            Assert.assertTrue(loginPage.isLetsGetStartedDisplayed(), "Let's get started text is not displayed");
            loginPage.enterName(name);
            loginPage.enterAge(age);
            loginPage.selectMale();
            loginPage.clickContinue();
        }

        else if(loginPage.isVerifyOTPTextDisplayed()==true) {
            Assert.assertTrue(loginPage.isVerifyOTPTextDisplayed(), "Verify OTP text is not displayed");
            Assert.assertTrue(loginPage.isEnterTheOTPTextDisplayed(), "Enter the OTP text is not displayed");
            loginPage.enterOTP(otp);
            Thread.sleep(5000);
            loginPage.clickVerifyOTP();
            loginPage.clickSkipButton();
        }

        if(leadScreen.isKnowTheRootCauseOfYourHairLossTextDisplayed()==true) {
            Assert.assertTrue(leadScreen.isKnowTheRootCauseOfYourHairLossTextDisplayed(), "Know the root cause of your hair loss text is not displayed");
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
        if(leadScreen.isTestCompletedTodayTextDisplayed()==true) {
            Assert.assertTrue(leadScreen.isTestCompletedTodayTextDisplayed(), "Test completed today text is not displayed");
            leadScreen.swipeUntilWhoMatchesYourProfileTextVisible();
            Assert.assertTrue(leadScreen.isWhoMatchesYourProfileTextDisplayed(), "Who matches your profile text is not displayed");
            leadScreen.swipeUntilTakeHairTestAgainTextVisible();
            Assert.assertTrue(leadScreen.isTakeHairTestAgainTextDisplayed(), "Take hair test again text is not displayed");
            leadScreen.clickTakeHairTestAgainText();
        }   

        if(hairTestScreen.isHairLossTextDisplayed()==true) {
            Assert.assertTrue(hairTestScreen.isHairLossTextDisplayed(), "Hair loss text is not displayed");
            Assert.assertTrue(hairTestScreen.isWhichImageBestDescribesYourHairLossTextDisplayed(), "Which image best describes your hair loss text is not displayed");
            Assert.assertTrue(hairTestScreen.isStage3TextOptionDisplayed(), "Stage 3 text option is not displayed");
            hairTestScreen.clickStage3Option();
            Assert.assertTrue(hairTestScreen.isFrontOnlyTextOptionDisplayed(), "Front only text option is not displayed");
            hairTestScreen.clickFrontOnlyTextOption();
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
            if(hairTestScreen.isContinueButtonDisplayed()==true) {
                Assert.assertTrue(hairTestScreen.isContinueButtonDisplayed(), "Continue button is not displayed");
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
            if(checkoutScreen.isAlreadyPresentAddressDisplayed()==true) {
                checkoutScreen.clickAlreadyPresentAddress();
                Assert.assertTrue(checkoutScreen.isProceedToPayButtonDisplayed(), "Proceed to pay button is not displayed");
                checkoutScreen.clickProceedToPayButton();
                Thread.sleep(5000);
              //  clickAtCoordinates.clickAtCoordinates(755, 2680);// Coninue button
                clickAtCoordinates.clickAtCoordinates(640, 1111); // Cash on delivery button
                Thread.sleep(2000);
                Assert.assertTrue(paymentScreen.isProceedToPayButtonDisplayed(), "Proceed to pay button is not displayed");
                paymentScreen.clickProceedToPayButton();
                Thread.sleep(5000);
                Assert.assertTrue(thankYouScreen.isBookACallButtonDisplayed(), "Book a call button is not displayed");
                thankYouScreen.clickBookACallButton();
                Assert.assertTrue(thankYouScreen.isCallBookedTextDisplayed(), "Call booked text is not displayed");
                Assert.assertTrue(thankYouScreen.isOkayButtonDisplayed(), "Okay button is not displayed");
                thankYouScreen.clickOkayButton();
                Assert.assertTrue(customerScreen.isYourOrderIsPlacedTextDisplayed(), "Okay button is not displayed");
                Assert.assertTrue(customerScreen.isHamburgerMenuButtonDisplayed(), "Okay button is not displayed");
                customerScreen.clickHamburgerMenuButton();
                Assert.assertTrue(customerHambergerScreen.isLogoutButtonDisplayed(),"Logout Button is not displayed");
                customerHambergerScreen.clickLogoutButton();
                Assert.assertTrue(customerHambergerScreen.isLogoutToggle_buttonDisplayed(),"Logout  Toggle Button is not displayed");
                customerHambergerScreen.clickLogoutToggle_button();
        }
            else {
                Assert.assertTrue(checkoutScreen.isAddNewAddressButtonDisplayed(), "Add new address button is not displayed");
                checkoutScreen.clickAddNewAddressButton();
                Assert.assertTrue(checkoutScreen.isAddressDetailsTextDisplayed(), "Address details text is not displayed");
                Assert.assertTrue(checkoutScreen.isCountryRegionTextDisplayed(), "Country/region text is not displayed");
                Assert.assertTrue(checkoutScreen.isHouseNoBuildingRoadAreaTextDisplayed(), "House no./building/road/area text is not displayed");
                Assert.assertTrue(checkoutScreen.isLocalityTextDisplayed(), "Locality text is not displayed");
                Assert.assertTrue(checkoutScreen.isPincodeTextDisplayed(), "Pincode text is not displayed");
                Assert.assertTrue(checkoutScreen.isCityTextDisplayed(), "City text is not displayed");
                Assert.assertTrue(checkoutScreen.isStateTextDisplayed(), "State text is not displayed");
                Assert.assertTrue(checkoutScreen.isAddressTypeTextDisplayed(), "Address type text is not displayed");
                Assert.assertTrue(checkoutScreen.isHomeButtonDisplayed(), "Home button is not displayed");
                Assert.assertTrue(checkoutScreen.isWorkButtonDisplayed(), "Work button is not displayed");
                Assert.assertTrue(checkoutScreen.isOtherButtonDisplayed(), "Other button is not displayed");
                Assert.assertTrue(checkoutScreen.isSaveAndPayButtonDisplayed(), "Save and pay button is not displayed");
                Assert.assertTrue(checkoutScreen.isPincodeTextFieldDisplayed(), "Pincode text field is not displayed");
                checkoutScreen.enterPincode(pincode);
                Assert.assertTrue(checkoutScreen.isLocalityTextFieldDisplayed(), "Locality text field is not displayed");
                checkoutScreen.enterLocality(location);
                Assert.assertTrue(checkoutScreen.isAddressTextFieldDisplayed(), "Address text field is not displayed");
                checkoutScreen.enterAddress(address);
                checkoutScreen.clickWorkButton();
                checkoutScreen.clickSaveAndPayButton();
                Thread.sleep(5000);
              //  clickAtCoordinates.clickAtCoordinates(755, 2680);// Coninue button
                clickAtCoordinates.clickAtCoordinates(640, 1111); // Cash on delivery button
                Thread.sleep(2000);
                Assert.assertTrue(paymentScreen.isProceedToPayButtonDisplayed(), "Proceed to pay button is not displayed");
                paymentScreen.clickProceedToPayButton();
                Thread.sleep(5000);
                Assert.assertTrue(thankYouScreen.isBookACallButtonDisplayed(), "Book a call button is not displayed");
                thankYouScreen.clickBookACallButton();
                Assert.assertTrue(thankYouScreen.isCallBookedTextDisplayed(), "Call booked text is not displayed");
                Assert.assertTrue(thankYouScreen.isOkayButtonDisplayed(), "Okay button is not displayed");
                thankYouScreen.clickOkayButton();
                Assert.assertTrue(customerScreen.isYourOrderIsPlacedTextDisplayed(), "Okay button is not displayed");
                Assert.assertTrue(customerScreen.isHamburgerMenuButtonDisplayed(), "Okay button is not displayed");
                customerScreen.clickHamburgerMenuButton();
                Assert.assertTrue(customerHambergerScreen.isLogoutButtonDisplayed(),"Logout Button is not displayed");
                customerHambergerScreen.clickLogoutButton();
                Assert.assertTrue(customerHambergerScreen.isLogoutToggle_buttonDisplayed(),"Logout  Toggle Button is not displayed");
                customerHambergerScreen.clickLogoutToggle_button();
            }
        }
        WebDriver webDriver = Web_Chrome_SignIn.createSignedInChromeDriver();
        Login_Page loginPageWeb = new Login_Page(webDriver);
        Home_Page homePage  = new Home_Page(webDriver);
        Customer_Discription_Page customerDiscriptionPage = new Customer_Discription_Page(webDriver);
        Web_Chrome_SignIn.navigateToWelcomePage(webDriver);

        if (loginPageWeb.isSignInWithGoogleDisplayed()==true) {
            Assert.assertTrue(loginPageWeb.isSignInWithGoogleDisplayed(), "Sign in with Google button is not displayed");
            loginPageWeb.clickSignInWithGoogleButton();
            Thread.sleep(2000);
            if (homePage.isOkButtonDisplayed()==true) {
                Assert.assertTrue(homePage.isOkButtonDisplayed(), "OK Buton is not displayed");
                homePage.clickOKButton();
            }
        }
        Assert.assertTrue(homePage.isSearchtextfieldIsDisplayed(), "Search Textfield is not displayed");
        homePage.clickSearchTextfield();
        Assert.assertTrue(homePage.isSearchPageTextFieldIsDisplayed(), "Search Page textfield is not displayed");
        homePage.clickSearchPageTextField();
        homePage.enterNumberInSearchPageTextField(mobileNumber);

        if (homePage.isPeopleNumberDisplayed()==true) {
            Assert.assertTrue(homePage.isPeopleNumberDisplayed(), "People Number is not displayed");
            homePage.clickPeopleNumber();
        }

        if (customerDiscriptionPage.isCanceledButtonDisplayed()==true){
            Assert.assertTrue(customerDiscriptionPage.isCanceledButtonDisplayed(), "Canceled button is not displayed");
            customerDiscriptionPage.clickCanceledButton();
            Thread.sleep(2000);
            Assert.assertTrue(customerDiscriptionPage.isSelectARemarkDisplayed(), "Select A Remark is not displayed");
            customerDiscriptionPage.clickSelectARemarkTextfiled();
            customerDiscriptionPage.enterRemark("TEST ORDER");
            Assert.assertTrue(customerDiscriptionPage.isConfirmCancellationButtonDisplayed(), "Confirm Cancellation Button is not displayed");
            customerDiscriptionPage.clickConfirmCancellationButton();
            Assert.assertTrue(customerDiscriptionPage.isSuccessfulMessageDisplayed(), "Automatic Order Cancellation Ticket Raise successfully Message is not displayed");
        }
        
        if (customerDiscriptionPage.isSlotBookingCloseButonDisplayed()==true) {
            Assert.assertTrue(customerDiscriptionPage.isSlotBookingCloseButonDisplayed(), "SLOT BOOKING close button is not displayed");
            customerDiscriptionPage.clickSlotBookCloseButton();
            Assert.assertTrue(customerDiscriptionPage.isOtherOptionDisplayed(), "Please Enter the Reason for Slot Cancellation Other option is not displayed");
            customerDiscriptionPage.clickOtherOption();
            Assert.assertTrue(customerDiscriptionPage.isCanResizeTextFiledDisplayed(), "can resize textfield is not displayed");
            customerDiscriptionPage.enterText("TestOrder");
            Assert.assertTrue(customerDiscriptionPage.isConfirmCancelButonDisplayed(), "Confirm Cancel button is not displayed");
            customerDiscriptionPage.clickConfirmCancelButton();
            Assert.assertTrue(customerDiscriptionPage.isSlotCancelledMessageDisplayed(), "Slot Cancelled successfully message is not displayed");
        }
    }
}


