package com.company.framework.listeners;

import com.company.framework.constants.FrameworkConstants;
import com.company.framework.notifications.SlackNotifier;
import com.company.framework.reports.PdfReportGenerator;
import com.company.framework.reports.ScriptResult;
import com.company.framework.reports.SuiteSummary;
import com.company.framework.utils.ConfigReader;
import com.company.framework.utils.LogUtil;
import org.openqa.selenium.WebDriverException;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import java.io.File;
import java.util.Date;
import java.util.Map;

public class SuiteReportListener implements ISuiteListener {

    @Override
    public void onFinish(ISuite suite) {

        SuiteSummary summary = new SuiteSummary();
        summary.setSuiteName(suite.getName());
        summary.setPlatform(ConfigReader.get("platformName"));
        summary.setDevice(ConfigReader.get("deviceName"));

        Map<String, ISuiteResult> results = suite.getResults();

        Date earliestStart = null;
        Date latestEnd = null;

        for (ISuiteResult suiteResult : results.values()) {

            ITestContext context = suiteResult.getTestContext();

            if (earliestStart == null || context.getStartDate().before(earliestStart)) {
                earliestStart = context.getStartDate();
            }
            if (latestEnd == null || context.getEndDate().after(latestEnd)) {
                latestEnd = context.getEndDate();
            }

            for (ITestResult result : context.getPassedTests().getAllResults()) {
                summary.addScriptResult(toScriptResult(result, ScriptResult.Status.PASSED));
            }
            for (ITestResult result : context.getSkippedTests().getAllResults()) {
                summary.addScriptResult(toScriptResult(result, ScriptResult.Status.SKIPPED));
            }
            for (ITestResult result : context.getFailedTests().getAllResults()) {
                summary.addScriptResult(toScriptResult(result, classifyFailure(result)));
            }
        }

        summary.setStartTime(earliestStart);
        summary.setEndTime(latestEnd);

        PdfReportGenerator.generate(summary, FrameworkConstants.PDF_REPORT_PATH);
        sendReportToSlack(summary);
    }

    /**
     * Best-effort: a Slack outage or misconfigured token should never fail the
     * build, so any failure here is logged and swallowed.
     */
    private static void sendReportToSlack(SuiteSummary summary) {

        if (!Boolean.parseBoolean(ConfigReader.get("slack.enabled"))) {
            return;
        }

        try {
            String botToken = System.getenv("SLACK_BOT_TOKEN");
            if (botToken == null || botToken.isBlank()) {
                botToken = ConfigReader.get("slack.botToken");
            }
            String recipientUserId = ConfigReader.get("slack.recipientUserId");

            if (botToken == null || botToken.isBlank() || recipientUserId == null || recipientUserId.isBlank()) {
                LogUtil.log("Slack reporting is enabled but slack.botToken/slack.recipientUserId are not configured; skipping.");
                return;
            }

            String message = String.format(
                    "*Suite Execution Report* - %s%nStatus: %s | Total: %d | Passed: %d | Failed: %d | Skipped: %d | Terminated: %d",
                    summary.getSuiteName(),
                    summary.isOverallPass() ? "PASS" : "FAIL",
                    summary.getTotalScripts(),
                    summary.getPassedCount(),
                    summary.getFailedCount(),
                    summary.getSkippedCount(),
                    summary.getTerminatedCount());

            SlackNotifier.sendFileToUser(botToken, recipientUserId, new File(FrameworkConstants.PDF_REPORT_PATH), message);
            LogUtil.log("Suite execution report sent to Slack");

        } catch (Exception e) {
            LogUtil.log("Failed to send suite report to Slack: " + e.getMessage());
        }
    }

    private static ScriptResult toScriptResult(ITestResult result, ScriptResult.Status status) {

        String screenshotPath = (String) result.getAttribute("screenshotPath");
        String errorMessage = result.getThrowable() != null ? result.getThrowable().getMessage() : null;
        return new ScriptResult(result.getMethod().getMethodName(), status, screenshotPath, errorMessage);
    }

    /**
     * Assertion failures are genuine test failures. Anything else (driver/session
     * crash, interruption) is reported as TERMINATED instead of FAILED so the two
     * are distinguishable in the report.
     */
    private static ScriptResult.Status classifyFailure(ITestResult result) {

        Throwable throwable = result.getThrowable();
        if (throwable == null || throwable instanceof AssertionError) {
            return ScriptResult.Status.FAILED;
        }
        if (throwable instanceof WebDriverException || throwable instanceof InterruptedException) {
            return ScriptResult.Status.TERMINATED;
        }
        return ScriptResult.Status.FAILED;
    }
}
