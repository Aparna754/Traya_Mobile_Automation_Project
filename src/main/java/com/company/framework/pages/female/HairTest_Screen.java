package com.company.framework.pages.female;

import com.company.framework.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Female Hair Test question flow, reached via "Take Hair Test Again" on the home dashboard.
 * Confirmed live to be a completely different question set from the male flow
 * (com.company.framework.pages.male.HairTest_Screen's Stage 1-6 image options) - sections seen
 * live: HAIR TYPE, HAIR FALL, HAIR CONDITION, HEAVY HAIR FALL, MEDICAL CONDITION, and more.
 *
 * Options render as one of two widget styles, both exposing the option's label as content-desc:
 *   - android.widget.CheckBox (resource-id="checkbox") - single-select questions auto-advance on
 *     tap; multi-select questions need an explicit "Next" tap afterward.
 *   - a plain clickable android.view.ViewGroup (image-card style, e.g. the Female Hair Scale
 *     question) - no checked state, tapping the card advances immediately.
 * Every option (regardless of widget class) exposes its label via content-desc - each field below
 * is matched on that, same @AndroidFindBy/PageFactory convention as every other page object in
 * this project.
 */
public class HairTest_Screen {

    private final WaitUtils waitUtils;

    public HairTest_Screen(AppiumDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // --- HAIR TYPE section ---
    @AndroidFindBy(xpath = "//*[@content-desc='Straight']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement StraightOption;
    public boolean isStraightOptionDisplayed() { return waitUtils.isElementDisplayed(StraightOption); }
    public void clickStraightOption() { waitUtils.waitForElement(StraightOption).click(); }

    @AndroidFindBy(xpath = "//*[@content-desc='Control Hairfall']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ControlHairfallOption;
    public boolean isControlHairfallOptionDisplayed() { return waitUtils.isElementDisplayed(ControlHairfallOption); }
    public void clickControlHairfallOption() { waitUtils.waitForElement(ControlHairfallOption).click(); }

    // --- HAIR FALL section ---
    @AndroidFindBy(xpath = "//*[@content-desc='Yes, extreme hair fall']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement YesExtremeHairFallOption;
    public boolean isYesExtremeHairFallOptionDisplayed() { return waitUtils.isElementDisplayed(YesExtremeHairFallOption); }
    public void clickYesExtremeHairFallOption() { waitUtils.waitForElement(YesExtremeHairFallOption).click(); }

    // --- HAIR CONDITION section (image-card, "Where do you stand on the Female Hair Scale?") ---
    @AndroidFindBy(xpath = "//*[@content-desc='Hair Thinning']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement HairThinningOption;
    public boolean isHairThinningOptionDisplayed() { return waitUtils.isElementDisplayed(HairThinningOption); }
    public void clickHairThinningOption() { waitUtils.waitForElement(HairThinningOption).click(); }

    // --- Hair quality / thickness ---
    @AndroidFindBy(xpath = "//*[@content-desc='Good Hair Quality']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement GoodHairQualityOption;
    public boolean isGoodHairQualityOptionDisplayed() { return waitUtils.isElementDisplayed(GoodHairQualityOption); }
    public void clickGoodHairQualityOption() { waitUtils.waitForElement(GoodHairQualityOption).click(); }

    @AndroidFindBy(xpath = "//*[@content-desc='Thin']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement ThinOption;
    public boolean isThinOptionDisplayed() { return waitUtils.isElementDisplayed(ThinOption); }
    public void clickThinOption() { waitUtils.waitForElement(ThinOption).click(); }

    // --- Reused across multiple distinct questions - PageFactory re-resolves this on each call,
    // so the same field/methods work regardless of which question is currently showing it.
    @AndroidFindBy(xpath = "//*[@content-desc='None']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement NoneOption;
    public boolean isNoneOptionDisplayed() { return waitUtils.isElementDisplayed(NoneOption); }
    public void clickNoneOption() { waitUtils.waitForElement(NoneOption).click(); }

    @AndroidFindBy(xpath = "//*[@content-desc='No']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement NoOption;
    public boolean isNoOptionDisplayed() { return waitUtils.isElementDisplayed(NoOption); }
    public void clickNoOption() { waitUtils.waitForElement(NoOption).click(); }

    @AndroidFindBy(xpath = "//*[@content-desc='Within 24 hours']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement Within24HrsOption;
    public boolean isWithin24HrsOptionDisplayed() { return waitUtils.isElementDisplayed(Within24HrsOption); }
    public void clickWithin24HrsOption() { waitUtils.waitForElement(Within24HrsOption).click(); }

    // --- Sleep quality ---
    @AndroidFindBy(xpath = "//*[@content-desc='Peacefully for 6-8 hours']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement PeacefullyFor6To8HrsOption;
    public boolean isPeacefullyFor6To8HrsOptionDisplayed() { return waitUtils.isElementDisplayed(PeacefullyFor6To8HrsOption); }
    public void clickPeacefullyFor6To8HrsOption() { waitUtils.waitForElement(PeacefullyFor6To8HrsOption).click(); }

    @AndroidFindBy(xpath = "//*[@content-desc='Not at all']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement NotAtAllOption;
    public boolean isNotAtAllOptionDisplayed() { return waitUtils.isElementDisplayed(NotAtAllOption); }
    public void clickNotAtAllOption() { waitUtils.waitForElement(NotAtAllOption).click(); }

    // --- Stress level (same phrasing as the male flow's equivalent question) ---
    @AndroidFindBy(xpath = "//*[@content-desc='Always high']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement AlwaysHighOption;
    public boolean isAlwaysHighOptionDisplayed() { return waitUtils.isElementDisplayed(AlwaysHighOption); }
    public void clickAlwaysHighOption() { waitUtils.waitForElement(AlwaysHighOption).click(); }

    // --- Multi-select confirm button ---
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Next']")
    //  @iOSXCUITFindBy(accessibility = "PLACEHOLDER_female_radio_button") // TODO(iOS): replace once real app exists
    private WebElement NextButton;
    public boolean isNextButtonDisplayed() { return waitUtils.isElementDisplayed(NextButton); }
    public void clickNextButton() { waitUtils.waitForElement(NextButton).click(); }
}
