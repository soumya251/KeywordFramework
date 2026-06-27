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

    public void execute(String keyword, String locator, String value, String pageName,String run
    		) throws Exception {

        driver = DriverFactory.driver;
       rf = new ReusableFunctions(driver);
 

        if (keyword.equalsIgnoreCase("openBrowser")) {
            DriverFactory.initDriver(value);

        } else if (keyword.equalsIgnoreCase("openUrl")) {
            rf.openUrl(value);

        } else if (keyword.equalsIgnoreCase("click")) {
        	
        	try {
        
            rf.click(locator);
        	}catch(Exception e)
        	{
        		String msg=e.getMessage();
        		System.out.println(msg);
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
        else if(keyword.equalsIgnoreCase("close"))
        {
        }
        else if(keyword.equalsIgnoreCase("SwitchOnwindow"))
        {
        	rf.SwitchOnwindow(locator);
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
        // *
        else if(keyword.equalsIgnoreCase("Check_visibility"))
        {
        	rf.checkvisibility(locator);
        }
        else if(keyword.equalsIgnoreCase("SwitchToParent"))
        {
        	rf.windowswitchtoparent(value);
        }
        else if(keyword.equalsIgnoreCase("NavigateBack"))
        {
        	rf.navigateback(value);
        }
        else if(keyword.equalsIgnoreCase("FetchGmailOtp"))
        {    
        	String email="";
        	String password="";
        	String otp=rf.fetchOtp(email,password);
        	System.out.println(otp);
        }
//        else if(keyword.equalsIgnoreCase("movepage"))
//        {
//        	rf.movepage(pageName);
//        
//        }
        
        else if(keyword.equalsIgnoreCase("compareAndAddToCart"))
        {
        	int count = 8;
        	try { count = Integer.parseInt(value);
        	} catch(Exception ex) {}
        	rf.compareAndAddToCart(count);
        }
        
        else {
            throw new RuntimeException("Invalid keyword: " + keyword);
        }
        
       
        
        
    }
    // *- not sure may be wrong or right
}