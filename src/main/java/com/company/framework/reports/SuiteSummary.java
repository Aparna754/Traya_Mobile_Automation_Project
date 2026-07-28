package com.company.framework.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuiteSummary {

    private String suiteName;
    private Date startTime;
    private Date endTime;
    private String platform;
    private String device;
    private final List<ScriptResult> scriptResults = new ArrayList<>();

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public List<ScriptResult> getScriptResults() {
        return scriptResults;
    }

    public void addScriptResult(ScriptResult scriptResult) {
        scriptResults.add(scriptResult);
    }

    public long getTotalScripts() {
        return scriptResults.size();
    }

    public long getPassedCount() {
        return scriptResults.stream().filter(r -> r.getStatus() == ScriptResult.Status.PASSED).count();
    }

    public long getFailedCount() {
        return scriptResults.stream().filter(r -> r.getStatus() == ScriptResult.Status.FAILED).count();
    }

    public long getSkippedCount() {
        return scriptResults.stream().filter(r -> r.getStatus() == ScriptResult.Status.SKIPPED).count();
    }

    public long getTerminatedCount() {
        return scriptResults.stream().filter(r -> r.getStatus() == ScriptResult.Status.TERMINATED).count();
    }

    public boolean isOverallPass() {
        return getFailedCount() == 0 && getTerminatedCount() == 0;
    }

    public long getDurationMillis() {
        if (startTime == null || endTime == null) {
            return 0;
        }
        return endTime.getTime() - startTime.getTime();
    }
}
