package com.company.framework.driver.factory;

public enum Platform {
    ANDROID,IOS;

    public static Platform fromString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("platform must be specified (Android|iOS)");
        }
        String normalized = value.trim().toUpperCase();
        if (normalized.equals("IOS")) {
            return IOS;
        }
        if (normalized.equals("ANDROID")) {
            return ANDROID;
        }
        throw new IllegalArgumentException("Unsupported platform: " + value + " (expected Android or iOS)");
    }
}
