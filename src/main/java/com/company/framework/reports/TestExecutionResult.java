package com.company.framework.reports;

/**
 * Immutable record of a single executed test method, assembled by TestListener and handed to
 * PdfReportManager for rendering. Built via Builder so optional failure-only fields (exception
 * details, screenshot) don't force a sprawling constructor for passed/skipped tests.
 */
public final class TestExecutionResult {

    public enum Status {
        PASSED, FAILED, SKIPPED
    }

    private final String testClassName;
    private final String testMethodName;
    private final String description;
    private final Status status;
    private final String platform;
    private final String deviceName;
    private final String osVersion;
    private final long executionTimeMillis;
    private final String failureReason;
    private final String exceptionType;
    private final String exceptionMessage;
    private final String rootCause;
    private final String failedLocator;
    private final String stackTrace;
    private final String screenshotPath;
    private final String screenshotFileName;

    private TestExecutionResult(Builder builder) {
        this.testClassName = builder.testClassName;
        this.testMethodName = builder.testMethodName;
        this.description = builder.description;
        this.status = builder.status;
        this.platform = builder.platform;
        this.deviceName = builder.deviceName;
        this.osVersion = builder.osVersion;
        this.executionTimeMillis = builder.executionTimeMillis;
        this.failureReason = builder.failureReason;
        this.exceptionType = builder.exceptionType;
        this.exceptionMessage = builder.exceptionMessage;
        this.rootCause = builder.rootCause;
        this.failedLocator = builder.failedLocator;
        this.stackTrace = builder.stackTrace;
        this.screenshotPath = builder.screenshotPath;
        this.screenshotFileName = builder.screenshotFileName;
    }

    public String getTestClassName() {
        return testClassName;
    }

    public String getTestMethodName() {
        return testMethodName;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public long getExecutionTimeMillis() {
        return executionTimeMillis;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public String getRootCause() {
        return rootCause;
    }

    public String getFailedLocator() {
        return failedLocator;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public String getScreenshotPath() {
        return screenshotPath;
    }

    public String getScreenshotFileName() {
        return screenshotFileName;
    }

    public boolean hasScreenshot() {
        return screenshotPath != null && !screenshotPath.isBlank();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String testClassName;
        private String testMethodName;
        private String description;
        private Status status;
        private String platform;
        private String deviceName;
        private String osVersion;
        private long executionTimeMillis;
        private String failureReason;
        private String exceptionType;
        private String exceptionMessage;
        private String rootCause;
        private String failedLocator;
        private String stackTrace;
        private String screenshotPath;
        private String screenshotFileName;

        private Builder() {
        }

        public Builder testClassName(String testClassName) {
            this.testClassName = testClassName;
            return this;
        }

        public Builder testMethodName(String testMethodName) {
            this.testMethodName = testMethodName;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Builder platform(String platform) {
            this.platform = platform;
            return this;
        }

        public Builder deviceName(String deviceName) {
            this.deviceName = deviceName;
            return this;
        }

        public Builder osVersion(String osVersion) {
            this.osVersion = osVersion;
            return this;
        }

        public Builder executionTimeMillis(long executionTimeMillis) {
            this.executionTimeMillis = executionTimeMillis;
            return this;
        }

        public Builder failureReason(String failureReason) {
            this.failureReason = failureReason;
            return this;
        }

        public Builder exceptionType(String exceptionType) {
            this.exceptionType = exceptionType;
            return this;
        }

        public Builder exceptionMessage(String exceptionMessage) {
            this.exceptionMessage = exceptionMessage;
            return this;
        }

        public Builder rootCause(String rootCause) {
            this.rootCause = rootCause;
            return this;
        }

        public Builder failedLocator(String failedLocator) {
            this.failedLocator = failedLocator;
            return this;
        }

        public Builder stackTrace(String stackTrace) {
            this.stackTrace = stackTrace;
            return this;
        }

        public Builder screenshotPath(String screenshotPath) {
            this.screenshotPath = screenshotPath;
            return this;
        }

        public Builder screenshotFileName(String screenshotFileName) {
            this.screenshotFileName = screenshotFileName;
            return this;
        }

        public TestExecutionResult build() {
            return new TestExecutionResult(this);
        }
    }
}
