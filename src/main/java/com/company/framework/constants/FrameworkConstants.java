package com.company.framework.constants;

import java.io.File;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    public static final String PROJECT_PATH = System.getProperty("user.dir");

    public static final String TEST_OUTPUT_PATH = PROJECT_PATH + File.separator + "test-output";

    public static final String SCREENSHOT_PATH = TEST_OUTPUT_PATH + File.separator + "screenshots";

    public static final String LOG_PATH = TEST_OUTPUT_PATH + File.separator + "logs";

    public static final String REPORT_PATH = TEST_OUTPUT_PATH + File.separator + "reports" + File.separator + "ExtentReport.html";

    public static final String PDF_REPORT_PATH = TEST_OUTPUT_PATH + File.separator + "reports" + File.separator + "SuiteExecutionReport.pdf";
}
