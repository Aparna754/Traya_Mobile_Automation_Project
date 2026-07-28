package com.company.framework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.company.framework.constants.FrameworkConstants;
import java.io.File;

public final class ExtentManager {

    private static ExtentReports extentReports;

    private ExtentManager() {
    }

    public static ExtentReports getExtentReports() {

        if (extentReports == null) {
            File reportDirectory = new File(FrameworkConstants.TEST_OUTPUT_PATH);

            if (!reportDirectory.exists()) {
                reportDirectory.mkdirs();
            }

            ExtentSparkReporter reporter = new ExtentSparkReporter(FrameworkConstants.REPORT_PATH);

            extentReports = new ExtentReports();
            extentReports.attachReporter(reporter);
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        }

        return extentReports;
    }
}