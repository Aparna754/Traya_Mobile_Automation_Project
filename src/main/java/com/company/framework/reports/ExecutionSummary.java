package com.company.framework.reports;

/**
 * Summary-page data. Project/environment/build metadata comes from config, set once via Builder;
 * platform/device/OS-version and all counts are DERIVED by PdfReportManager from the final,
 * completed list of TestExecutionResults at generation time - not accumulated via counters during
 * the run - since onFinish(ISuite) only fires after every test thread has finished, the list is
 * already complete and stable by the time a summary is needed. This avoids any counter/list
 * drift under parallel Android+iOS execution.
 */
public final class ExecutionSummary {

    private final String projectName;
    private final String environment;
    private final String executedBy;
    private final String executionDate;
    private final String platform;
    private final String deviceName;
    private final String osVersion;
    private final int totalTestCases;
    private final int passed;
    private final int failed;
    private final int skipped;
    private final long executionTimeMillis;
    private final double passPercentage;
    private final double failPercentage;

    private ExecutionSummary(Builder builder) {
        this.projectName = builder.projectName;
        this.environment = builder.environment;
        this.executedBy = builder.executedBy;
        this.executionDate = builder.executionDate;
        this.platform = builder.platform;
        this.deviceName = builder.deviceName;
        this.osVersion = builder.osVersion;
        this.totalTestCases = builder.totalTestCases;
        this.passed = builder.passed;
        this.failed = builder.failed;
        this.skipped = builder.skipped;
        this.executionTimeMillis = builder.executionTimeMillis;
        this.passPercentage = builder.passPercentage;
        this.failPercentage = builder.failPercentage;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getExecutedBy() {
        return executedBy;
    }

    public String getExecutionDate() {
        return executionDate;
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

    public int getTotalTestCases() {
        return totalTestCases;
    }

    public int getPassed() {
        return passed;
    }

    public int getFailed() {
        return failed;
    }

    public int getSkipped() {
        return skipped;
    }

    public long getExecutionTimeMillis() {
        return executionTimeMillis;
    }

    public double getPassPercentage() {
        return passPercentage;
    }

    public double getFailPercentage() {
        return failPercentage;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String projectName;
        private String environment;
        private String executedBy;
        private String executionDate;
        private String platform;
        private String deviceName;
        private String osVersion;
        private int totalTestCases;
        private int passed;
        private int failed;
        private int skipped;
        private long executionTimeMillis;
        private double passPercentage;
        private double failPercentage;

        private Builder() {
        }

        public Builder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Builder environment(String environment) {
            this.environment = environment;
            return this;
        }

        public Builder executedBy(String executedBy) {
            this.executedBy = executedBy;
            return this;
        }

        public Builder executionDate(String executionDate) {
            this.executionDate = executionDate;
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

        public Builder totalTestCases(int totalTestCases) {
            this.totalTestCases = totalTestCases;
            return this;
        }

        public Builder passed(int passed) {
            this.passed = passed;
            return this;
        }

        public Builder failed(int failed) {
            this.failed = failed;
            return this;
        }

        public Builder skipped(int skipped) {
            this.skipped = skipped;
            return this;
        }

        public Builder executionTimeMillis(long executionTimeMillis) {
            this.executionTimeMillis = executionTimeMillis;
            return this;
        }

        public Builder passPercentage(double passPercentage) {
            this.passPercentage = passPercentage;
            return this;
        }

        public Builder failPercentage(double failPercentage) {
            this.failPercentage = failPercentage;
            return this;
        }

        public ExecutionSummary build() {
            return new ExecutionSummary(this);
        }
    }
}
