package com.company.framework.listeners;

import com.company.framework.annotations.TestDescription;
import com.company.framework.constants.FrameworkConstants;
import com.company.framework.driver.DriverManager;
import com.company.framework.driver.factory.Platform;
import com.company.framework.reports.PdfReportManager;
import com.company.framework.reports.TestExecutionResult;
import com.company.framework.utils.ConfigReader;
import com.company.framework.utils.LogUtil;
import com.company.framework.utils.PlatformUtils;
import com.company.framework.utils.ScreenshotUtil;
import io.appium.java_client.AppiumDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Collects per-test data into TestExecutionResults and hands them to PdfReportManager - all
 * OpenPDF rendering lives in PdfReportManager, this class only observes the TestNG lifecycle and
 * assembles data (SRP: listener collects, manager renders).
 *
 * Implements BOTH ITestListener (per-@Test-method events) AND ISuiteListener. The PDF is
 * generated from ISuiteListener.onFinish(ISuite), not ITestListener.onFinish(ITestContext):
 * the latter fires once per <test> block, which would generate the PDF TWICE for
 * testng-parallel.xml (one <test> per platform). ISuiteListener.onFinish fires exactly once,
 * after every <test> block - and therefore every platform's threads - has completed.
 */
public class TestListener implements ITestListener, ISuiteListener {

    private static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();
    private static final Pattern LOCATOR_PATTERN = Pattern.compile("By\\.[a-zA-Z]+:\\s*[^)}]+");

    @Override
    public void onStart(ISuite suite) {
        PdfReportManager.recordSuiteStart();
        LogUtil.log("Suite started: " + suite.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {

        START_TIME.set(System.currentTimeMillis());
        String testName = result.getMethod().getMethodName();
        Platform platform = PlatformUtils.getCurrentPlatform();

        System.out.println("TEST STARTED: " + testName + " [" + platform + "]");
        LogUtil.log("TEST STARTED: " + testName + " [" + platform + "]");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        String testName = result.getMethod().getMethodName();
        LogUtil.log("TEST PASSED: " + testName);

        PdfReportManager.addResult(baseResultBuilder(result)
                .status(TestExecutionResult.Status.PASSED)
                .build());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getMethod().getMethodName();
        Throwable failure = result.getThrowable();
        LogUtil.log("TEST FAILED: " + testName);

        String screenshotPath = null;
        String screenshotFileName = null;
        AppiumDriver driver = DriverManager.getDriver();
        if (driver != null) {
            try {
                screenshotPath = ScreenshotUtil.captureFailureScreenshot(driver, testName);
                screenshotFileName = new java.io.File(screenshotPath).getName();
                LogUtil.log("Screenshot saved at: " + screenshotPath);
            } catch (Exception e) {
                LogUtil.log("Could not capture screenshot: " + e.getMessage());
            }
        }

        TestExecutionResult.Builder builder = baseResultBuilder(result)
                .status(TestExecutionResult.Status.FAILED)
                .screenshotPath(screenshotPath)
                .screenshotFileName(screenshotFileName);

        if (failure != null) {
            LogUtil.log("Failure Reason: " + failure.getMessage());
            builder.failureReason(failure.getMessage())
                    .exceptionType(failure.getClass().getName())
                    .exceptionMessage(failure.getMessage())
                    .rootCause(rootCauseOf(failure))
                    .failedLocator(extractFailedLocator(failure))
                    .stackTrace(stackTraceOf(failure));
        }

        PdfReportManager.addResult(builder.build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        String testName = result.getMethod().getMethodName();
        LogUtil.log("TEST SKIPPED: " + testName);

        TestExecutionResult.Builder builder = baseResultBuilder(result)
                .status(TestExecutionResult.Status.SKIPPED);

        Throwable skipCause = result.getThrowable();
        if (skipCause != null) {
            builder.failureReason(skipCause.getMessage());
        }

        PdfReportManager.addResult(builder.build());
    }

    @Override
    public void onFinish(ISuite suite) {

        PdfReportManager.recordSuiteEnd();

        LogUtil.log("Waiting " + (FrameworkConstants.REPORT_GENERATION_DELAY_MILLIS / 1000)
                + "s before generating the report...");
        try {
            Thread.sleep(FrameworkConstants.REPORT_GENERATION_DELAY_MILLIS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        PdfReportManager.generateReport();
        LogUtil.log("PDF execution report generation triggered for suite: " + suite.getName());
    }

    private TestExecutionResult.Builder baseResultBuilder(ITestResult result) {

        Platform platform = PlatformUtils.getCurrentPlatform();
        Long startedAt = START_TIME.get();
        long executionTimeMillis = startedAt != null ? System.currentTimeMillis() - startedAt : 0L;

        return TestExecutionResult.builder()
                .testClassName(result.getTestClass().getName())
                .testMethodName(result.getMethod().getMethodName())
                .description(descriptionOf(result))
                .platform(platform.name())
                .deviceName(ConfigReader.getForPlatform(platform.name(), "deviceName"))
                .osVersion(ConfigReader.getForPlatform(platform.name(), "platformVersion"))
                .executionTimeMillis(executionTimeMillis);
    }

    private String descriptionOf(ITestResult result) {

        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        TestDescription annotation = method != null ? method.getAnnotation(TestDescription.class) : null;
        if (annotation != null) {
            return annotation.value();
        }
        String testNgDescription = result.getMethod().getDescription();
        return testNgDescription != null && !testNgDescription.isBlank() ? testNgDescription : "No description provided";
    }

    private String rootCauseOf(Throwable throwable) {
        Throwable current = throwable;
        while (current.getCause() != null && current.getCause() != current) {
            current = current.getCause();
        }
        return current.getClass().getSimpleName() + ": " + current.getMessage();
    }

    private String stackTraceOf(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    private String extractFailedLocator(Throwable throwable) {

        String haystack = throwable.getMessage() != null ? throwable.getMessage() : stackTraceOf(throwable);
        Matcher matcher = LOCATOR_PATTERN.matcher(haystack);
        return matcher.find() ? matcher.group().trim() : "Not available";
    }
}
