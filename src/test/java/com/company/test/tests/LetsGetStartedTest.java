package com.company.test.tests;

import com.company.framework.driver.DriverManager;
import com.company.framework.listeners.TestListener;
import com.company.framework.pages.LetsGetStartedPage;
import com.company.framework.stepgroups.RandomAge;
import com.company.framework.stepgroups.RandomNumber;
import com.company.test.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LetsGetStartedTest extends BaseTest {

    @Test(description = "Verify user can fill all details and proceed")
    public void verifyUserCanFillDetailsAndContinue() {

        LetsGetStartedPage page = new LetsGetStartedPage(DriverManager.getDriver());
        String mobileNumber = RandomNumber.generateMobileNumber();
        String age = RandomAge.generateRandomAge();

        System.out.println("Application is opened");
        page.enterMobileNumber(mobileNumber);
        System.out.println("Application mobile entered");
        page.clickContinue();
        page.clickGetOTP();
        Assert.assertTrue(page.isLetsGetStartedDisplayed(), "Let's get started text is not displayed");
        page.enterName("Aman");
        page.enterAge(age);
        page.selectMale();
        page.selectFemale();
     //   page.clickContinue();
    }
}
