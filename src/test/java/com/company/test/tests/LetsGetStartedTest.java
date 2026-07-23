package com.company.test.tests;

import com.company.framework.driver.DriverManager;
import com.company.framework.pages.LetsGetStartedPage;
import com.company.test.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LetsGetStartedTest extends BaseTest {

    @Test(description = "Verify user can fill all details and proceed")
    public void verifyUserCanFillDetailsAndContinue() {

        LetsGetStartedPage page = new LetsGetStartedPage(DriverManager.getDriver());
        System.out.println("Application is opened");

        page.enterMobileNumber("99876437672");
        System.out.println("Application mobile entered");
        page.clickGetOTP();
        Assert.assertTrue(page.isLetsGetStartedDisplayed(), "Let's get started text is not displayed");
        page.enterName("Aman");
        page.enterAge("30");
        page.selectMale();
     //   Assert.assertTrue(page.isMaleSelected());
        page.selectFemale();
    //    Assert.assertTrue(page.isFemaleSelected());
        page.clickContinue();
    }
}
