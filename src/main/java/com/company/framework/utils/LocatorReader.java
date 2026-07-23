package com.company.framework.utils;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class LocatorReader {

    /*public static By getLocator(
            String locatorType,
            String locatorValue
    {

        switch (locatorType.toLowerCase()) {

            case "xpath":
                return By.xpath(locatorValue);

            case "id":
                return By.id(locatorValue);

            case "accessibilityid":
                return AppiumBy.accessibilityId(locatorValue);

            case "classname":
                return By.className(locatorValue);

            default:
                throw new RuntimeException(
                        "Unsupported locator type: "
                                + locatorType
                );
        }
    }*/
    public static By getLocator(String pageName, String elementName) {

        String locatorType = ExcelReader.getLocatorType(pageName, elementName);
        String locatorValue = ExcelReader.getLocatorValue(pageName, elementName);
        switch (locatorType.toLowerCase()) {
            case "id":
                return By.id(locatorValue);

            case "xpath":
                return By.xpath(locatorValue);

            case "accessibilityid":
                return AppiumBy.accessibilityId(locatorValue);

            case "classname":
                return By.className(locatorValue);

            default:
                throw new RuntimeException("Unsupported locator type: " + locatorType);
        }
    }

}
