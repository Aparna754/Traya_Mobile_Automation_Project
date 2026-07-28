package com.company.framework.utils;

import com.company.framework.constants.FrameworkConstants;
import io.appium.java_client.android.AndroidDriver;
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

    public static String takeScreenshot(AndroidDriver driver, String testName) {

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            Path directory = Path.of(FrameworkConstants.SCREENSHOT_PATH);
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