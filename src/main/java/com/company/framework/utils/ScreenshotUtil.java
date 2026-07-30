package com.company.framework.utils;

import com.company.framework.constants.FrameworkConstants;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ScreenshotUtil {

    private ScreenshotUtil() {
    }

    /**
     * Captures a screenshot for a FAILED test only - callers must not invoke this for
     * passed/skipped tests (see TestListener, the sole caller). Naming convention:
     * <TestMethodName>_FAILED_yyyyMMdd_HHmmss.png
     */
    public static String captureFailureScreenshot(AppiumDriver driver, String testName) {

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_FAILED_" + timestamp + ".png";
            Path directory = Path.of(FrameworkConstants.screenshotPathFor(PlatformUtils.getCurrentPlatform().name()));
            Files.createDirectories(directory);
            Path destination = directory.resolve(fileName);
            Files.copy(screenshot.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved: " + destination);

            return destination.toAbsolutePath().toString();

        } catch (Exception e) {

            throw new RuntimeException("Failed to capture screenshot", e);
        }
    }
}