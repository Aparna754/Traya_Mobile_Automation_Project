package com.company.framework.utils;

import java.io.IOException;

public final class AppResetUtils {

    private static final String[] PERMISSIONS_TO_GRANT = {
            "android.permission.POST_NOTIFICATIONS",
            "android.permission.CAMERA",
            "android.permission.READ_PHONE_STATE",
            "android.permission.RECORD_AUDIO"
    };

    private AppResetUtils() {
    }

    public static void resetApp(String appPackage) {

        runAdb("shell", "pm", "clear", appPackage);

        for (String permission : PERMISSIONS_TO_GRANT) {
            runAdb("shell", "pm", "grant", appPackage, permission);
        }
    }

    private static void runAdb(String... args) {

        try {
            String[] command = new String[args.length + 1];
            command[0] = "adb";
            System.arraycopy(args, 0, command, 1, args.length);

            Process process = new ProcessBuilder(command).redirectErrorStream(true).start();
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to run adb command: " + String.join(" ", args), e);
        }
    }
}
