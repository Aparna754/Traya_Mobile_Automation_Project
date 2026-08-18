package com.company.framework.pages.male;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.company.framework.utils.SwipeUtils;
import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.time.Duration;
public class HairTest_Screen {

    private final WaitUtils waitUtils;
    private final AppiumDriver driver;

    public HairTest_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='HAIR LOSS']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_mobile_number_field") // TODO(iOS): replace once real app exists
    private WebElement HairLossText;
    public boolean isHairLossTextDisplayed() {
        return waitUtils.isElementDisplayed(HairLossText);
    }
    public boolean isHairLossTextDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(HairLossText, timeout);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Which image best describes your hair loss?']")
  //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_get_otp_button") // TODO(iOS): replace once real app exists
    private WebElement WhichImageBestDescribesYourHairLossText;
    public boolean isWhichImageBestDescribesYourHairLossTextDisplayed() {
        return waitUtils.isElementDisplayed(WhichImageBestDescribesYourHairLossText);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Stage 1']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement Stage1TextOption;
    public boolean isStage1TextOptionDisplayed() {
        return waitUtils.isElementDisplayed(Stage1TextOption);
    }
    public void clickStage1Option() {
        waitUtils.waitForElement(Stage1TextOption).click();
    }

     @AndroidFindBy(xpath = "//android.widget.TextView[@text='Stage 2']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement Stage2TextOption;
    public boolean isStage2TextOptionDisplayed() {
        return waitUtils.isElementDisplayed(Stage2TextOption);
    }
    public void clickStage2Option() {
        waitUtils.waitForElement(Stage2TextOption).click();
    }

     @AndroidFindBy(xpath = "//android.widget.TextView[@text='Stage 3']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement Stage3TextOption;
    public boolean isStage3TextOptionDisplayed() {
        return waitUtils.isElementDisplayed(Stage3TextOption);
    }
    public void clickStage3Option() {
        waitUtils.waitForElement(Stage3TextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Stage 4']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement Stage4TextOption;
    public boolean isStage4TextOptionDisplayed() {
        return waitUtils.isElementDisplayed(Stage4TextOption);
    }
    public void clickStage4Option() {
        waitUtils.waitForElement(Stage4TextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Stage 5']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement Stage5TextOption;
    public boolean isStage5TextOptionDisplayed() {
        return waitUtils.isElementDisplayed(Stage5TextOption);
    }
    public void clickStage5Option() {
        waitUtils.waitForElement(Stage5TextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Stage 6']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement stage6TextOption;
    public boolean isStage6TextOptionDisplayed() { 
        return waitUtils.isElementDisplayed(stage6TextOption); 
    }
    public void clickStage6Option() { 
        waitUtils.waitForElement(stage6TextOption).click(); 
    }
    public void swipeUntilStage6TextVisible() { 
        SwipeUtils.swipeUntilVisible(driver, stage6TextOption); 
    }

     @AndroidFindBy(xpath = "//android.widget.TextView[@text='Front only']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement FrontOnlyTextOption;
    public boolean isFrontOnlyTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(FrontOnlyTextOption);
    }
    public void clickFrontOnlyTextOption() {
        waitUtils.waitForElement(FrontOnlyTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='At the top of the head only']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement AtTheTopOfTheHeadOnlyTextOption;
    public boolean isAtTheTopOfTheHeadOnlyTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(AtTheTopOfTheHeadOnlyTextOption);
    }
    public void clickAtTheTopOfTheHeadOnlyTextOption() {
        waitUtils.waitForElement(AtTheTopOfTheHeadOnlyTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Both front and top of the head']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement BothFrontAndTopOfTheHeadTextOption;
    public boolean isBothFrontAndTopOfTheHeadTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(BothFrontAndTopOfTheHeadTextOption);
    }
    public void clickBothFrontAndTopOfTheHeadTextOption() {
        waitUtils.waitForElement(BothFrontAndTopOfTheHeadTextOption).click();
    }
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='None']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement NoneTextOption;
    public boolean isNoneTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(NoneTextOption);
    }
    public void clickNoneTextOption() {
        waitUtils.waitForElement(NoneTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Mother or anyone from mother's side of the family']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement MotherOrAnyoneFromMothersSideOfTheFamilyTextOption;
    public boolean isMotherOrAnyoneFromMothersSideOfTheFamilyTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(MotherOrAnyoneFromMothersSideOfTheFamilyTextOption);
    }
    public void clickMotherOrAnyoneFromMothersSideOfTheFamilyTextOption() {
        waitUtils.waitForElement(MotherOrAnyoneFromMothersSideOfTheFamilyTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Father or anyone from father's side of the family']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement FatherOrAnyoneFromFathersSideOfTheFamilyTextOption;
    public boolean isFatherOrAnyoneFromFathersSideOfTheFamilyTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(FatherOrAnyoneFromFathersSideOfTheFamilyTextOption);
    }
    public void clickFatherOrAnyoneFromFathersSideOfTheFamilyTextOption() {
        waitUtils.waitForElement(FatherOrAnyoneFromFathersSideOfTheFamilyTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Both']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement BothTextOption;
    public boolean isBothTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(BothTextOption);
    }
    public void clickBothTextOption() {
        waitUtils.waitForElement(BothTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Severe Illness (Dengue, Malaria, Typhoid or Covid)']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement SevereIllnessTextOption;
    public boolean isSevereIllnessTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(SevereIllnessTextOption);
    }
    public void clickSevereIllnessTextOption() {
        waitUtils.waitForElement(SevereIllnessTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Heavy weight loss / heavy weight gain']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement HeavyWeightLossTextOption;
    public boolean isHeavyWeightLossTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(HeavyWeightLossTextOption);
    }
    public void clickHeavyWeightLossTextOption() {
        waitUtils.waitForElement(HeavyWeightLossTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Surgery / heavy medication']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement SurgeryOrHeavyMedicationTextOption;
    public boolean isSurgeryOrHeavyMedicationTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(SurgeryOrHeavyMedicationTextOption);
    }
    public void clickSurgeryOrHeavyMedicationTextOption() {
        waitUtils.waitForElement(SurgeryOrHeavyMedicationTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Next']/android.view.ViewGroup")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement NextButton;
    public boolean isNextButtonDisplayed() {
        return waitUtils.isElementDisplayed(NextButton);
    }
    public void clickNextButton() {
        waitUtils.waitForElement(NextButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='No']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement NoTextOption;
    public boolean isNoTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(NoTextOption);
    }
    public void clickNoTextOption() {
        waitUtils.waitForElement(NoTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Yes, mild that comes and goes']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement YesMildThatComesAndGoesTextOption;
    public boolean isYesMildThatComesAndGoesTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(YesMildThatComesAndGoesTextOption);
    }
    public void clickYesMildThatComesAndGoesTextOption() {
        waitUtils.waitForElement(YesMildThatComesAndGoesTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Yes, heavy dandruff that sticks to the scalp']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement YesHeavyDandruffThatSticksToTheScalpTextOption;
    public boolean isYesHeavyDandruffThatSticksToTheScalpTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(YesHeavyDandruffThatSticksToTheScalpTextOption);
    }
    public void clickYesHeavyDandruffThatSticksToTheScalpTextOption() {
        waitUtils.waitForElement(YesHeavyDandruffThatSticksToTheScalpTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='I have Psoriasis']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement IHavePsoriasisTextOption;
    public boolean isIHavePsoriasisTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(IHavePsoriasisTextOption);
    }
    public void clickIHavePsoriasisTextOption() {
        waitUtils.waitForElement(IHavePsoriasisTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='I have Seborrheic Dermatitis']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement IHaveSeborrheicDermatitisTextOption;
    public boolean isIHaveSeborrheicDermatitisTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(IHaveSeborrheicDermatitisTextOption);
    }
    public void clickIHaveSeborrheicDermatitisTextOption() {
        waitUtils.waitForElement(IHaveSeborrheicDermatitisTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Very peacefully for 6 to 8 hours']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement VeryPeacefullyFor6To8HoursTextOption;
    public boolean isVeryPeacefullyFor6To8HoursTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(VeryPeacefullyFor6To8HoursTextOption);
    }
    public void clickVeryPeacefullyFor6To8HoursTextOption() {
        waitUtils.waitForElement(VeryPeacefullyFor6To8HoursTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Disturbed sleep, I wake up at least one time during the night']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement DisturbedSleepTextOption;
    public boolean isDisturbedSleepTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(DisturbedSleepTextOption);
    }
    public void clickDisturbedSleepTextOption() {
        waitUtils.waitForElement(DisturbedSleepTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Have difficulty falling asleep']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement HaveDifficultyFallingAsleepTextOption;
    public boolean isHaveDifficultyFallingAsleepTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(HaveDifficultyFallingAsleepTextOption);
    }
    public void clickHaveDifficultyFallingAsleepTextOption() {
        waitUtils.waitForElement(HaveDifficultyFallingAsleepTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Low']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement LowTextOption;
    public boolean isLowTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(LowTextOption);
    }
    public void clickLowTextOption() {
        waitUtils.waitForElement(LowTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Moderate (work, family etc)']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement ModerateTextOption;
    public boolean isModerateTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(ModerateTextOption);
    }
    public void clickModerateTextOption() {
        waitUtils.waitForElement(ModerateTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='High (Loss of close one, separation, home, illness)']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement HighTextOption;
    public boolean isHighTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(HighTextOption);
    }
    public void clickHighTextOption() {
        waitUtils.waitForElement(HighTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='No/Rarely']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement NoRarelyTextOption;
    public boolean isNoRarelyTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(NoRarelyTextOption);
    }
    public void clickNoRarelyTextOption() {
        waitUtils.waitForElement(NoRarelyTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Yes']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement YesTextOption;
    public boolean isYesTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(YesTextOption);
    }
    public void clickYesTextOption() {
        waitUtils.waitForElement(YesTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Unsatisfactory bowel movements']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement UnsatisfactoryBowelMovementsTextOption;
    public boolean isUnsatisfactoryBowelMovementsTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(UnsatisfactoryBowelMovementsTextOption);
    }
    public void clickUnsatisfactoryBowelMovementsTextOption() {
        waitUtils.waitForElement(UnsatisfactoryBowelMovementsTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Suffering from IBS (irritable bowel syndrome) /dysentery']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement SufferingFromIBSOrDysenteryTextOption;
    public boolean isSufferingFromIBSOrDysenteryTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(SufferingFromIBSOrDysenteryTextOption);
    }
    public void clickSufferingFromIBSOrDysenteryTextOption() {
        waitUtils.waitForElement(SufferingFromIBSOrDysenteryTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Always high']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement AlwaysHighTextOption;
    public boolean isAlwaysHighTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(AlwaysHighTextOption);
    }
    public void clickAlwaysHighTextOption() {
        waitUtils.waitForElement(AlwaysHighTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Low when I wake up, but gradually increases']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement LowWhenIWakeUpButGraduallyIncreasesTextOption;
    public boolean isLowWhenIWakeUpButGraduallyIncreasesTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(LowWhenIWakeUpButGraduallyIncreasesTextOption);
    }
    public void clickLowWhenIWakeUpButGraduallyIncreasesTextOption() {
        waitUtils.waitForElement(LowWhenIWakeUpButGraduallyIncreasesTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Very low in afternoon']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement VeryLowInAfternoonTextOption;
    public boolean isVeryLowInAfternoonTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(VeryLowInAfternoonTextOption);
    }
    public void clickVeryLowInAfternoonTextOption() {
        waitUtils.waitForElement(VeryLowInAfternoonTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Low by evening / night']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement LowByEveningNightTextOption;
    public boolean isLowByEveningNightTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(LowByEveningNightTextOption);
    }
    public void clickLowByEveningNightTextOption() {
        waitUtils.waitForElement(LowByEveningNightTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Always low']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement AlwaysLowTextOption;
    public boolean isAlwaysLowTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(AlwaysLowTextOption);
    }
    public void clickAlwaysLowTextOption() {
        waitUtils.waitForElement(AlwaysLowTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Not Sure']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement NotSureTextOption;
    public boolean isNotSureTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(NotSureTextOption);
    }
    public void clickNotSureTextOption() {
        waitUtils.waitForElement(NotSureTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Low blood pressure']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement LowBloodPressureTextOption;
    public boolean isLowBloodPressureTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(LowBloodPressureTextOption);
    }
    public void clickLowBloodPressureTextOption() {
        waitUtils.waitForElement(LowBloodPressureTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='High blood pressure']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement HighBloodPressureTextOption;
    public boolean isHighBloodPressureTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(HighBloodPressureTextOption);
    }
    public void clickHighBloodPressureTextOption() {
        waitUtils.waitForElement(HighBloodPressureTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Thyroid']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement ThyroidTextOption;
    public boolean isThyroidTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(ThyroidTextOption);
    }
    public void clickThyroidTextOption() {
        waitUtils.waitForElement(ThyroidTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cholesterol']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement CholesterolTextOption;
    public boolean isCholesterolTextOptionDisplayed() {
        return waitUtils.isElementDisplayed(CholesterolTextOption);
    }
    public void clickCholesterolTextOption() {
        waitUtils.waitForElement(CholesterolTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Take Scalp Photo']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement TakeScalpPhotoButton;
    public boolean isTakeScalpPhotoButtonDisplayed() {
        return waitUtils.isElementDisplayed(TakeScalpPhotoButton);
    }
    public void clickTakeScalpPhotoButton() {
        waitUtils.waitForElement(TakeScalpPhotoButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Upload From Gallery']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement UploadFromGalleryButton;
    public boolean isUploadFromGalleryButtonDisplayed() {
        return waitUtils.isElementDisplayed(UploadFromGalleryButton);
    }
    public void clickUploadFromGalleryButton() {
        waitUtils.waitForElement(UploadFromGalleryButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Continue']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement ContinueTextOption;
    public boolean isContinueButtonDisplayed() {
        return waitUtils.isElementDisplayed(ContinueTextOption);
    }
    public boolean isContinueButtonDisplayed(Duration timeout) {
        return waitUtils.isElementDisplayed(ContinueTextOption, timeout);
    }
    public void clickContinueButton() {
        waitUtils.waitForElement(ContinueTextOption).click();
    }

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement ImageCaptureButton;
    public boolean isImageCaptureButtonDisplayed() {
        return waitUtils.isElementDisplayed(ImageCaptureButton);
    }
    public void clickImageCaptureButton() {
        waitUtils.waitForElement(ImageCaptureButton).click();
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='󰄬']")
 //   @iOSXCUITFindBy(accessibility = "PLACEHOLDER_name_field") // TODO(iOS): replace once real app exists
    private WebElement TickImagebutton;
    public boolean isTickImagebuttonDisplayed() {
        return waitUtils.isElementDisplayed(TickImagebutton);
    }
    public void clickTickImagebutton() {
        waitUtils.waitForElement(TickImagebutton).click();
    }
}
