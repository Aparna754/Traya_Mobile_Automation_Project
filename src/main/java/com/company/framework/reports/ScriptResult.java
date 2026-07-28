package com.company.framework.reports;

public class ScriptResult {

    public enum Status {
        PASSED, FAILED, SKIPPED, TERMINATED
    }

    private final String scriptName;
    private final Status status;
    private final String screenshotPath;
    private final String errorMessage;

    public ScriptResult(String scriptName, Status status, String screenshotPath, String errorMessage) {
        this.scriptName = scriptName;
        this.status = status;
        this.screenshotPath = screenshotPath;
        this.errorMessage = errorMessage;
    }

    public String getScriptName() {
        return scriptName;
    }

    public Status getStatus() {
        return status;
    }

    public String getScreenshotPath() {
        return screenshotPath;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
