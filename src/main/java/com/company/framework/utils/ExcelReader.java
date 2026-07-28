package com.company.framework.utils;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExcelReader {

    private static final String FILE_PATH = "/testdata/Elements_Page.csv";

    private static String[] getElementData(String pageName, String elementName) {

        try (InputStream inputStream = ExcelReader.class.getResourceAsStream(FILE_PATH)) {
            if (inputStream == null) {
                throw new RuntimeException("CSV file not found: " + FILE_PATH);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            // Skip header
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1);

                // Expected columns:
                // 0 = Page
                // 1 = Element_Name
                // 2 = Type
                // 3 = Locator_Type
                // 4 = Locator_Value

                if (data.length < 5) {
                    continue;
                }

                String page = data[0].trim();
                String elementNameFromCsv = data[1].trim();
                String type = data[2].trim();
                String locatorType = data[3].trim();
                String locatorValue = data[4].trim();

                // Validate that all required fields are not null or blank
                if (page.isEmpty() || elementNameFromCsv.isEmpty() || type.isEmpty() || locatorType.isEmpty() || locatorValue.isEmpty()) {
                    continue;
                }

                // Match Page and Element_Name
                if (page.equalsIgnoreCase(pageName) && elementNameFromCsv.equalsIgnoreCase(elementName)) {
                    return new String[]{locatorType, locatorValue};
                }
            }

            throw new RuntimeException("Valid locator data not found for Page: " + pageName + " and Element: " + elementName);

        } catch (Exception e) {
            throw new RuntimeException("Error reading locator from CSV file", e);
        }
    }
    public static String getLocatorValue(String pageName, String elementName) {
        String[] data = getElementData(pageName, elementName);
        return data[1];
    }

    public static String getLocatorType(String pageName, String elementName) {
        String[] data = getElementData(pageName, elementName);
        return data[0];
    }
}
