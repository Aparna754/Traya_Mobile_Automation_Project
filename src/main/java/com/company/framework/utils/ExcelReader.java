package com.company.framework.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExcelReader {

   /* public static Object[][] getExcelData(String filePath, String sheetName) throws Exception {

        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(sheetName);
        int rows = sheet.getPhysicalNumberOfRows();
        int columns = sheet.getRow(0).getPhysicalNumberOfCells();
        Object[][] data = new Object[rows - 1][columns];
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }
        workbook.close();
        return data;
    }*/

   /* public static String[] getLocatorData(String filePath, String sheetName, String pageName, String elementName) throws Exception {

        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(sheetName);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            String page = row.getCell(0).getStringCellValue();
            String element = row.getCell(1).getStringCellValue();
            if (page.equalsIgnoreCase(pageName) && element.equalsIgnoreCase(elementName)) {
                String locatorType = row.getCell(3).getStringCellValue();
                String locatorValue = row.getCell(4).getStringCellValue();
                workbook.close();
                return new String[]{locatorType, locatorValue};
            }
        }
        workbook.close();
        throw new RuntimeException("Locator not found for Page: " + pageName + " and Element: " + elementName);
    }*/

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
