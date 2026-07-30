package com.company.framework.constants;

import java.io.File;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    public static final String PROJECT_PATH = System.getProperty("user.dir");

    public static final String TEST_OUTPUT_PATH = PROJECT_PATH + File.separator + "test-output";

    public static final String SCREENSHOT_PATH = TEST_OUTPUT_PATH + File.separator + "screenshots";

    public static final String LOG_PATH = TEST_OUTPUT_PATH + File.separator + "logs";

    public static final String REPORTS_DIR = TEST_OUTPUT_PATH + File.separator + "reports";

    public static final String FRAMEWORK_VERSION = "1.0.0";

    /** Grace period before PDF generation, so any last screenshot/file writes settle first. */
    public static final long REPORT_GENERATION_DELAY_MILLIS = 20_000;

    public static String screenshotPathFor(String platform) {
        return SCREENSHOT_PATH + File.separator + platform.toLowerCase();
    }
}
