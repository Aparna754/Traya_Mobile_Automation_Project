package com.company.framework.utils;

import com.company.framework.constants.FrameworkConstants;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public final class LogUtil {

    private LogUtil() {
    }

    private static final String LOG_FILE = FrameworkConstants.LOG_PATH + File.separator + "automation.log";
    public static void log(String message) {
        try {
            File folder = new File(FrameworkConstants.LOG_PATH);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
                writer.write(LocalDateTime.now() + " - " + message + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}