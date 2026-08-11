package com.company.test.tests.male;

import com.company.framework.annotations.TestDescription;
import com.company.framework.listeners.TestListener;
import com.company.framework.pages.web.Web_Chrome_SignIn;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class Test_FLow {

    @Test(description = "Verify Web Flow Opens Chrome And Navigates To The ERP Welcome Page", groups = {"regression"})
    @TestDescription("Verify the web automation flow opens Chrome, maximizes it, and navigates to the ERP welcome URL")

    public void verifyWebFlow() {

        // quit() in finally: the dedicated profile's SingletonLock otherwise survives the JVM
        // exit and blocks every later ChromeDriver launch against this same profile with
        // "session not created: Chrome instance exited" until the leftover process is killed.
        WebDriver webDriver = Web_Chrome_SignIn.createSignedInChromeDriver();
        try {
            webDriver.get("https://erp.traya.health/welcome");
        } 
        finally {
            System.out.println("Done");
         //   webDriver.quit();
        }
    }
}
