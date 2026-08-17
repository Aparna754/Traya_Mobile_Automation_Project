package com.company.test.tests.male;

import com.company.framework.annotations.TestDescription;
import com.company.framework.driver.DriverManager;
import com.company.framework.listeners.TestListener;
import com.company.framework.pages.male.Customer_Hamberger_Screen;
import com.company.framework.pages.male.Customer_Screen;
import com.company.framework.pages.male.LoginAndDraftpage;
import com.company.framework.pages.male.Order_Details_Screen;
import com.company.framework.pages.male.Order_History_Screen;
import com.company.test.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

@Listeners(TestListener.class)
public class Validate_Your_Order_Male_Test extends BaseTest {

    String mobileNumber = "9711406757";
    String otp = "123789";

    private static final Duration QUICK_MOBILE_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration DASHBOARD_LOAD_TIMEOUT = Duration.ofSeconds(60);

    @Test(description = "Verify the hamburger menu's Your Orders flow - filter tabs, a Delivered order's full tracking/detail screen, and a Cancelled order's detail screen", groups = {"regression"})
    @TestDescription("Login as a returning customer, open the hamburger menu, navigate to Your Orders, verify all filter tabs are displayed, open the first Delivered order and verify its full Order Summary (status/dates/tracking timeline/tracking actions/kit-issue/prescription/invoice/order details/delivery address), go back and open the first Cancelled order and verify its Order Summary, then log out")
    public void verifyYourOrderHistoryFlow() throws InterruptedException {

        LoginAndDraftpage loginPage = new LoginAndDraftpage(DriverManager.getDriver());
        Customer_Screen customerScreen = new Customer_Screen(DriverManager.getDriver());
        Customer_Hamberger_Screen hamburgerScreen = new Customer_Hamberger_Screen(DriverManager.getDriver());
        Order_History_Screen orderHistoryScreen = new Order_History_Screen(DriverManager.getDriver());
        Order_Details_Screen orderDetailsScreen = new Order_Details_Screen(DriverManager.getDriver());

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
        Assert.assertTrue(customerScreen.isTodaysChecklistTextDisplayed(DASHBOARD_LOAD_TIMEOUT), "Today's Checklist text is not displayed");
        // --- Hamburger menu -> Your Orders ---
        customerScreen.clickHamburgerMenuButton();
        Thread.sleep(2000);
        Assert.assertTrue(hamburgerScreen.isYourOrdersMenuItemDisplayed(), "'Your Orders' menu item is not displayed");
        hamburgerScreen.clickYourOrdersMenuItem();
        Thread.sleep(5000);
        // --- Filter tabs ---
        Assert.assertTrue(orderHistoryScreen.isAllTabDisplayed(), "'All' tab is not displayed");
        Assert.assertTrue(orderHistoryScreen.isOngoingTabDisplayed(), "'Ongoing' tab is not displayed");
        Assert.assertTrue(orderHistoryScreen.isDeliveredTabDisplayed(), "'Delivered' tab is not displayed");
        Assert.assertTrue(orderHistoryScreen.isCancelledTabDisplayed(), "'Cancelled' tab is not displayed");
        Assert.assertTrue(orderHistoryScreen.isOthersTabDisplayed(), "'Others' tab is not displayed");
        // --- Delivered order detail ---
        orderHistoryScreen.clickDeliveredTab();
        Thread.sleep(3000);
        orderHistoryScreen.clickFirstOrder();
        Thread.sleep(3000);
        Assert.assertTrue(orderDetailsScreen.isOrderSummaryTitleDisplayed(), "Order Summary title is not displayed");
        Assert.assertTrue(orderDetailsScreen.isOrderIdDisplayed(), "Order ID is not displayed");
        Assert.assertTrue(orderDetailsScreen.isOrderStatusDisplayed(), "Order Status is not displayed");
        Assert.assertTrue(orderDetailsScreen.isOrderPlacedDateDisplayed(), "Order Placed Date is not displayed");
        Assert.assertTrue(orderDetailsScreen.isOrderDeliveryDateDisplayed(), "Order Delivery Date is not displayed");
        Assert.assertTrue(orderDetailsScreen.isOrderTrackingHeadingDisplayed(), "Order Tracking heading is not displayed");
        Assert.assertTrue(orderDetailsScreen.isOrderRegisteredStepDisplayed(), "'Order Registered' tracking step is not displayed");
        Assert.assertTrue(orderDetailsScreen.isPickupPendingStepDisplayed(), "'Pickup Pending' tracking step is not displayed");
        Assert.assertTrue(orderDetailsScreen.isPickedUpStepDisplayed(), "'Picked Up' tracking step is not displayed");
        Assert.assertTrue(orderDetailsScreen.isInTransitStepDisplayed(), "'In Transit' tracking step is not displayed");
        Assert.assertTrue(orderDetailsScreen.isOutForDeliveryStepDisplayed(), "'Out For Delivery' tracking step is not displayed");
        Assert.assertTrue(orderDetailsScreen.isDeliveredStepDisplayed(), "'Delivered' tracking step is not displayed");
        Assert.assertTrue(orderDetailsScreen.isTrackingIdDisplayed(), "Tracking ID is not displayed");
        Assert.assertTrue(orderDetailsScreen.isOpenTrackingLinkButtonDisplayed(), "'Open Tracking Link' button is not displayed");
        Assert.assertTrue(orderDetailsScreen.isShareButtonDisplayed(), "'Share' button is not displayed");
        Assert.assertTrue(orderDetailsScreen.isMyKitHasIssuesButtonDisplayed(), "'My Kit Has Issues' button is not displayed");
        Assert.assertTrue(orderDetailsScreen.isViewPrescriptionButtonDisplayed(), "'View Prescription' button is not displayed");
        Assert.assertTrue(orderDetailsScreen.isDownloadInvoiceButtonDisplayed(), "'Download Invoice' button is not displayed");
        orderDetailsScreen.swipeUpRepeatedly(3);
        Thread.sleep(1000);
        Assert.assertTrue(orderDetailsScreen.isOrderDetailsHeadingDisplayed(), "Order Details heading is not displayed");
        Assert.assertTrue(orderDetailsScreen.isDeliveryAddressHeadingDisplayed(), "Delivery Address text is not displayed");
        // --- Back to Your Orders -> Cancelled order detail ---
        orderDetailsScreen.clickBackButton();
        Thread.sleep(2000);
        orderHistoryScreen.clickCancelledTab();
        Thread.sleep(3000);
        Assert.assertTrue(orderHistoryScreen.isOrderHistoryHeadingDisplayed(), "Order History text is not displayed");
        orderHistoryScreen.clickFirstOrder();
        Thread.sleep(3000);
        Assert.assertTrue(orderDetailsScreen.isOrderIdDisplayed(), "Order Id is not displayed");
        Assert.assertTrue(orderDetailsScreen.isOrderStatusDisplayed(), "Order Status is not displayed");
        Assert.assertTrue(orderDetailsScreen.isOrderPlacedDateDisplayed(), "Order Placed date is not displayed");
        Assert.assertTrue(orderDetailsScreen.isOrderDetailsHeadingDisplayed(), "Order Details text is not displayed");
        Assert.assertTrue(orderDetailsScreen.isDeliveryAddressHeadingDisplayed(), "Delivery Address text is not displayed");
        // --- Leave and log out ---
        orderDetailsScreen.clickBackButton();
        Thread.sleep(2000);
        orderHistoryScreen.clickGoBackButton();
        Thread.sleep(2000);
        Assert.assertTrue(hamburgerScreen.isLogoutButtonDisplayed(), "Logout button is not displayed");
        hamburgerScreen.clickLogoutButton();
        Thread.sleep(1000);
        Assert.assertTrue(hamburgerScreen.isLogoutToggle_buttonDisplayed(), "Logout confirmation button is not displayed");
        hamburgerScreen.clickLogoutToggle_button();
    }
}
