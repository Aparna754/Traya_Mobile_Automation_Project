package com.company.test.tests.female;

import com.company.framework.annotations.TestDescription;
import com.company.framework.driver.DriverManager;
import com.company.framework.listeners.TestListener;
import com.company.framework.pages.female.Customer_Hamberger_Screen;
import com.company.framework.pages.female.Customer_Screen;
import com.company.framework.pages.female.LoginAndDraftpage;
import com.company.framework.pages.female.Order_Details_Screen;
import com.company.framework.pages.female.Order_History_Screen;
import com.company.test.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.time.Duration;
@Listeners(TestListener.class)
public class Validate_Your_Order_Female_Test extends BaseTest {

    String mobileNumber = "8892257924";
    String otp = "123789";

    private static final Duration QUICK_MOBILE_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration DASHBOARD_LOAD_TIMEOUT = Duration.ofSeconds(60);

    @Test(description = "Verify the hamburger menu's Your Orders flow - filter tabs, a Delivered order's full tracking/detail screen, and a Cancelled order's detail screen (female flow)", groups = {"regression"})
    @TestDescription("Login as a returning customer, open the hamburger menu, navigate to Your Orders, verify all filter tabs are displayed, open the first Delivered order and verify its full Order Summary (status/dates/tracking timeline/tracking actions/kit-issue/prescription/invoice/order details/delivery address), go back and open the first Cancelled order and verify its Order Summary, then log out")
    public void verifyYourOrderHistoryFlow() throws InterruptedException {

        LoginAndDraftpage loginPage = new LoginAndDraftpage(DriverManager.getDriver());
        Customer_Screen customerScreen = new Customer_Screen(DriverManager.getDriver());
        Customer_Hamberger_Screen hamburgerScreen = new Customer_Hamberger_Screen(DriverManager.getDriver());
        Order_History_Screen orderHistoryScreen = new Order_History_Screen(DriverManager.getDriver());
        Order_Details_Screen orderDetailsScreen = new Order_Details_Screen(DriverManager.getDriver());

        if (loginPage.isMobileNumberFieldDisplayed(QUICK_MOBILE_TIMEOUT)==true) {
            loginPage.enterMobileNumber(mobileNumber);
            loginPage.clickGetOTP();
            Assert.assertTrue(loginPage.isEnterTheOTPTextDisplayed(), "Enter the OTP text is not displayed");
            loginPage.enterOTP(otp);
            Thread.sleep(5000);
            loginPage.clickVerifyOTP();
            if (loginPage.isSkipButtonDisplayed(QUICK_MOBILE_TIMEOUT)==true) {
                loginPage.clickSkipButton();
            }
        }
        Assert.assertTrue(customerScreen.isTodaysChecklistTextDisplayed(DASHBOARD_LOAD_TIMEOUT), "Today's Checklist text is not displayed");

        // --- Hamburger menu -> Your Orders ---
        Assert.assertTrue(customerScreen.isHamburgerMenuButtonDisplayed(), "Hamburger menu button is not displayed");
        customerScreen.clickHamburgerMenuButton();
        Assert.assertTrue(hamburgerScreen.isYourOrdersMenuItemDisplayed(), "'Your Orders' menu item is not displayed");
        hamburgerScreen.clickYourOrdersMenuItem();
        Assert.assertTrue(orderHistoryScreen.isYourOrdersTitleDisplayed(QUICK_MOBILE_TIMEOUT), "'Your Orders' title is not displayed");

        // --- Filter tabs ---
        Assert.assertTrue(orderHistoryScreen.isAllTabDisplayed(), "'All' tab is not displayed");
        Assert.assertTrue(orderHistoryScreen.isOngoingTabDisplayed(), "'Ongoing' tab is not displayed");
        Assert.assertTrue(orderHistoryScreen.isDeliveredTabDisplayed(), "'Delivered' tab is not displayed");
        Assert.assertTrue(orderHistoryScreen.isCancelledTabDisplayed(), "'Cancelled' tab is not displayed");
        Assert.assertTrue(orderHistoryScreen.isOthersTabDisplayed(), "'Others' tab is not displayed");

        // --- Delivered order detail ---
        orderHistoryScreen.clickDeliveredTab();
        Assert.assertTrue(orderHistoryScreen.isOrderHistoryHeadingDisplayed(QUICK_MOBILE_TIMEOUT), "Order History text is not displayed");
        orderHistoryScreen.clickFirstOrder();
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
        // Confirmed live: this order's journey ended in an RTO (Return To Origin) flow rather than
        // a plain terminal "Delivered" step - the three Rto steps below are its actual final steps.
        Assert.assertTrue(orderDetailsScreen.isRtoMarkedStepDisplayed(), "'Rto Marked' tracking step is not displayed");
        Assert.assertTrue(orderDetailsScreen.isRtoIntransitStepDisplayed(), "'Rto Intransit' tracking step is not displayed");
        Assert.assertTrue(orderDetailsScreen.isRtoDeliveredStepDisplayed(), "'Rto Delivered' tracking step is not displayed");
        Assert.assertTrue(orderDetailsScreen.isTrackingIdDisplayed(), "Tracking ID is not displayed");
        Assert.assertTrue(orderDetailsScreen.isOpenTrackingLinkButtonDisplayed(), "'Open Tracking Link' button is not displayed");
        Assert.assertTrue(orderDetailsScreen.isShareButtonDisplayed(), "'Share' button is not displayed");
        Assert.assertTrue(orderDetailsScreen.isMyKitHasIssuesButtonDisplayed(), "'My Kit Has Issues' button is not displayed");
        Assert.assertTrue(orderDetailsScreen.isViewPrescriptionButtonDisplayed(), "'View Prescription' button is not displayed");
        Assert.assertTrue(orderDetailsScreen.isDownloadInvoiceButtonDisplayed(), "'Download Invoice' button is not displayed");
        orderDetailsScreen.swipeUpRepeatedly(3);
        Assert.assertTrue(orderDetailsScreen.isOrderDetailsHeadingDisplayed(), "Order Details heading is not displayed");
        Assert.assertTrue(orderDetailsScreen.isDeliveryAddressHeadingDisplayed(), "Delivery Address text is not displayed");

        // --- Back to Your Orders -> Cancelled order detail ---
        orderDetailsScreen.clickBackButton();
        Assert.assertTrue(orderHistoryScreen.isYourOrdersTitleDisplayed(QUICK_MOBILE_TIMEOUT), "'Your Orders' title is not displayed after going back");
        orderHistoryScreen.clickCancelledTab();
        Assert.assertTrue(orderHistoryScreen.isOrderHistoryHeadingDisplayed(QUICK_MOBILE_TIMEOUT), "Order History text is not displayed");
        orderHistoryScreen.clickFirstOrder();
        Assert.assertTrue(orderDetailsScreen.isOrderIdDisplayed(), "Order Id is not displayed");
        Assert.assertTrue(orderDetailsScreen.isOrderStatusDisplayed(), "Order Status is not displayed");
        Assert.assertTrue(orderDetailsScreen.isOrderPlacedDateDisplayed(), "Order Placed date is not displayed");
        Assert.assertTrue(orderDetailsScreen.isOrderDetailsHeadingDisplayed(), "Order Details text is not displayed");
        Assert.assertTrue(orderDetailsScreen.isDeliveryAddressHeadingDisplayed(), "Delivery Address text is not displayed");

        // --- Leave and log out ---
        orderDetailsScreen.clickBackButton();
        Assert.assertTrue(orderHistoryScreen.isGoBackButtonDisplayed(), "'Go back' button is not displayed");
        // Confirmed live: "Go back" from Your Orders returns straight to the still-open hamburger
        // menu (not the dashboard), landing directly on the Logout entry.
        orderHistoryScreen.clickGoBackButton();
        Assert.assertTrue(hamburgerScreen.isLogoutButtonDisplayed(), "Logout button is not displayed");
        hamburgerScreen.clickLogoutButton();
        Assert.assertTrue(hamburgerScreen.isLogoutConfirmButtonDisplayed(), "Logout confirmation button is not displayed");
        hamburgerScreen.clickLogoutConfirmButton();
    }
}
