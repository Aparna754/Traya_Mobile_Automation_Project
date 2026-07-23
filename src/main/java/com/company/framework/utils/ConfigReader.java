package com.company.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

        private static final Properties properties = new Properties();

        static {
            try {
                FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
                properties.load(fileInputStream);

            } catch (IOException e) {
                throw new RuntimeException("Unable to load config.properties", e);
            }
        }

        public static String get(String key) {
            return properties.getProperty(key);
        }
    }
