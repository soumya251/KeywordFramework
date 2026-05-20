//
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
//                driver = DriverFactory.driver;
//                driver.get(value);
//                break;
//
//            case "enterText":
//                driver = DriverFactory.driver;
//                driver.findElement(ObjectRepository.getLocator(locator)).sendKeys(value);
//                break;
//
//            case "click":
//                driver = DriverFactory.driver;
//                driver.findElement(ObjectRepository.getLocator(locator)).click();
//                break;
//
//            case "wait":
//                try {
//                    Thread.sleep(Long.parseLong(value));
//                } catch (Exception e) {}
//                break;
//
//            case "quit":
//                driver = DriverFactory.driver;
//                driver.quit();
//                break;
//              
//
//            default:
//                throw new RuntimeException("Invalid keyword: " + keyword);
//        }
//    }
//}

package com.framework.keywords;

import com.framework.keywords.ReusableFunctions;
import com.framework.driver.DriverFactory;
import org.openqa.selenium.WebDriver;

public class ActionKeywords {

    WebDriver driver;
    ReusableFunctions rf;

    public ActionKeywords() {
        driver = DriverFactory.driver;
        rf = new ReusableFunctions(driver);
    }

    public void execute(String keyword, String locator, String value) {

        driver = DriverFactory.driver;
        rf = new ReusableFunctions(driver); // refresh driver

        if (keyword.equalsIgnoreCase("openBrowser")) {
            DriverFactory.initDriver(value);

        } else if (keyword.equalsIgnoreCase("openUrl")) {
            rf.openUrl(value);

        } else if (keyword.equalsIgnoreCase("click")) {
        	
        	try {
        
            rf.click(locator);
        	}catch(Exception e)
        	{
        		System.out.println("not clicked");
        	}
            
        
        } else if (keyword.equalsIgnoreCase("enterText")) {
            rf.enterText(locator, value);

        } else if (keyword.equalsIgnoreCase("scrollToElement")) {
            rf.scrollToElement(locator);

        } else if (keyword.equalsIgnoreCase("wait")) {
            rf.waitTime(value);

        } else if (keyword.equalsIgnoreCase("quit")) {
            driver.quit();

        } 
        else if(keyword.equalsIgnoreCase("checkVisibilityClick"))
        {
        	rf.clickWhenVisible(locator);
        }
        else if(keyword.equalsIgnoreCase("checkVisibility"))
        {
        	rf.checkVisibilityOfElement(locator);
        }
        else if(keyword.equalsIgnoreCase("JavascriptClick"))
        {
        	rf.jsclick(locator);
        }
        else if(keyword.equalsIgnoreCase("checkvisibility"))
        {
        	rf.checkvisibility(locator);
        }
        else {
            throw new RuntimeException("Invalid keyword: " + keyword);
        }
        
        
    }
}