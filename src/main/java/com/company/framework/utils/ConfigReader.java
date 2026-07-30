package com.company.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

        private static final Properties properties = new Properties();

        static {
            try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (inputStream == null) {
                    throw new IOException("config.properties not found on classpath");
                }
                properties.load(inputStream);

            } catch (IOException e) {
                throw new RuntimeException("Unable to load config.properties", e);
            }
        }

        public static String get(String key) {
            return properties.getProperty(key);
        }

        /**
         * Platform-aware lookup: resolves the active platform, then looks up "<platform>.<key>"
         * (e.g. "android.deviceName"), falling back to a flat "<key>" for shared settings.
         * Only for contexts with no explicit Platform available (e.g. TestListener/ScreenshotUtil,
         * which run outside BaseTest's resolved Platform) - callers that already know their
         * Platform (e.g. DriverFactory) must use getForPlatform(String, String) instead, so
         * capability lookups aren't silently re-derived from ambient/default state.
         */
        public static String getForCurrentPlatform(String key) {
            return getForPlatform(resolvePlatform(), key);
        }

        /**
         * Platform-aware lookup for a Platform already known to the caller (e.g. DriverFactory,
         * which received it explicitly from BaseTest) - does not re-resolve the platform itself,
         * so it can never disagree with what the caller was actually told to build.
         */
        public static String getForPlatform(String platformName, String key) {
            String prefixed = properties.getProperty(platformName.toLowerCase() + "." + key);
            return prefixed != null ? prefixed : properties.getProperty(key);
        }

        /**
         * -Dplatform system property wins (CLI override), then config.properties' "platform" key.
         */
        public static String resolvePlatform() {
            String systemProperty = System.getProperty("platform");
            if (systemProperty != null && !systemProperty.isBlank()) {
                return systemProperty;
            }
            String fileValue = properties.getProperty("platform");
            if (fileValue != null && !fileValue.isBlank()) {
                return fileValue;
            }
            throw new IllegalStateException("platform not set via -Dplatform, testng <parameter>, or config.properties");
        }
    }
