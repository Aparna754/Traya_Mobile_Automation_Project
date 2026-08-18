package com.company.test.tests.male;

import com.company.framework.annotations.TestDescription;
import com.company.framework.driver.DriverManager;
import com.company.framework.listeners.TestListener;
import com.company.framework.pages.male.Assessment_Report_Screen;
import com.company.framework.pages.male.HairTest_Screen;
import com.company.framework.pages.male.Lead_Screen;
import com.company.framework.pages.male.Lead_You_Screen;
import com.company.framework.pages.male.LoginAndDraftpage;
import com.company.test.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(TestListener.class)
public class HairTest_Stage6_Male_Test extends BaseTest {

    String mobileNumber = "9334600822";
    String name = "Aman";
    String age = "25";
    String otp = "123789";
    // String pincode = "560078";
    // String location = "JP Nagar";
    // String address = "Test Building 1234";

    @Test(description = "Verify Male User Can Fill All Details With Stage6", groups = {"regression"})
    @TestDescription("Verify the user can fill all the details with stage6 and navigate till Book a call page")

    public void verifyMaleUserCanFillAllDetailsWithStage6() throws InterruptedException {

        LoginAndDraftpage loginPage = new LoginAndDraftpage(DriverManager.getDriver());
        Lead_Screen leadScreen = new Lead_Screen(DriverManager.getDriver());
        HairTest_Screen hairTestScreen = new HairTest_Screen(DriverManager.getDriver());
        Assessment_Report_Screen assessmentReportScreen = new Assessment_Report_Screen(DriverManager.getDriver());
        Lead_You_Screen leadYouScreen = new Lead_You_Screen(DriverManager.getDriver());

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
            hairTestScreen.swipeUntilStage6TextVisible();
            Assert.assertTrue(hairTestScreen.isStage6TextOptionDisplayed(), "Stage 6 text option is not displayed");
            hairTestScreen.clickStage6Option();
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
            Assert.assertTrue(assessmentReportScreen.isStage6TextDisplayed(), "Stage 6 text is not displayed");
            Assert.assertTrue(assessmentReportScreen.isWhatHappensAtThisStageTextDisplayed(), "What happens at this stage text is not displayed");
            assessmentReportScreen.swipeUntilHowDoesItWorkTextVisible();
            Assert.assertTrue(assessmentReportScreen.isHowDoesItWorkTextDisplayed(), "How does it work text is not displayed");
            Assert.assertTrue(assessmentReportScreen.isContinueButtonDisplayed2(), "Continue button is not displayed");
            assessmentReportScreen.clickContinueButton2();
            Assert.assertTrue(assessmentReportScreen.isBookACallTextDisplayed(), "Book a call text is not displayed");
            assessmentReportScreen.clickBookACallBackButton();
            assessmentReportScreen.clickassessmentReportBackButton();
            Assert.assertTrue(leadYouScreen.youButtonDisplayed(),"You button is not displayed");
            leadYouScreen.clickYouButton();
            Assert.assertTrue(leadYouScreen.isLogoutButtonDisplayed(), "Logout button is not displayed");
            leadYouScreen.clickLogoutButton();
            Assert.assertTrue(leadYouScreen.isLogoutToggle_buttonDisplayed(), "Logout toggle button is not displayed");
            leadYouScreen.clickLogoutToggle_button();

        }
    }
}


