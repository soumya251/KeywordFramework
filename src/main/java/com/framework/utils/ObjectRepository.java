package com.framework.utils;

import org.openqa.selenium.By;

public class ObjectRepository {

    public static By getLocator(String locator) {

        if (locator == null || locator.isEmpty()) return null;

        String[] parts = locator.split("=",2);
        String type = parts[0];
        String value = parts[1];

        switch (type.toLowerCase()) {

            case "id":
                return By.id(value);

            case "name":
                return By.name(value);

            case "xpath":
                return By.xpath(value);

            case "css":
                return By.cssSelector(value);
        }

        return null;
    }
}