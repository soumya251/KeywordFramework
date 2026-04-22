//package com.framework.keywords;
//
//import com.framework.driver.DriverFactory;
//import com.framework.utils.ObjectRepository;
//import org.openqa.selenium.WebDriver;
//
//public class ActionKeywords {
//
//    WebDriver driver;
//
//    public ActionKeywords(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    public void execute(String keyword, String locator, String value) {
//
//        switch (keyword) {
//
//            case "openBrowser":
//                DriverFactory.initDriver(value);
//                driver = DriverFactory.driver;
//                break;
//
//            case "openUrl":
//                driver.get(value);
//                break;
//
//            case "click":
//                driver.findElement(ObjectRepository.getLocator(locator)).click();
//                break;
//
//            case "enterText":
//                driver.findElement(ObjectRepository.getLocator(locator)).sendKeys(value);
//                break;
//
//            case "wait":
//                try {
//                    Thread.sleep(Long.parseLong(value));
//                } catch (Exception e) {}
//                break;
//
//            default:
//                System.out.println("Invalid keyword: " + keyword);
//        }
//    }
//}



package com.framework.keywords;

import com.framework.driver.DriverFactory;
import com.framework.utils.ObjectRepository;
import org.openqa.selenium.WebDriver;

public class ActionKeywords {

    WebDriver driver;

    public void execute(String keyword, String locator, String value) {

        switch (keyword) {

            case "openBrowser":
                DriverFactory.initDriver(value);
                driver = DriverFactory.driver;
                break;

            case "openUrl":
                driver = DriverFactory.driver;
                driver.get(value);
                break;

            case "enterText":
                driver = DriverFactory.driver;
                driver.findElement(ObjectRepository.getLocator(locator)).sendKeys(value);
                break;

            case "click":
                driver = DriverFactory.driver;
                driver.findElement(ObjectRepository.getLocator(locator)).click();
                break;

            case "wait":
                try {
                    Thread.sleep(Long.parseLong(value));
                } catch (Exception e) {}
                break;

            case "quit":
                driver = DriverFactory.driver;
                driver.quit();
                break;

            default:
                throw new RuntimeException("Invalid keyword: " + keyword);
        }
    }
}