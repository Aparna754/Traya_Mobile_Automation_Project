package com.company.framework.reports;

import java.util.List;

/**
 * Aggregate root handed from data collection (TestListener/PdfReportManager) to PDF rendering -
 * keeps the renderer's input to a single, immutable object instead of scattering summary/result
 * parameters across method signatures.
 */
public final class PdfReportModel {

    private final ExecutionSummary executionSummary;
    private final List<TestExecutionResult> testExecutionResults;

    public PdfReportModel(ExecutionSummary executionSummary, List<TestExecutionResult> testExecutionResults) {
        this.executionSummary = executionSummary;
        this.testExecutionResults = List.copyOf(testExecutionResults);
    }

    public ExecutionSummary getExecutionSummary() {
        return executionSummary;
    }

    public List<TestExecutionResult> getTestExecutionResults() {
        return testExecutionResults;
    }
}
