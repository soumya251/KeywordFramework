//package com.framework.engine;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.framework.keywords.ActionKeywords;
//import com.framework.reporting.ExtentManager;
//import com.framework.utils.ExcelReader;
//import com.framework.utils.ScreenshotUtil;
//
//import org.openqa.selenium.WebDriver;
//
//import java.util.List;
//
//public class KeywordEngine {
//	
//	ExtentReports extent=ExtentManager.getInstance();
//	ExtentTest test=extent.createTest("Keyword Test");
//
//    WebDriver driver;
//    ActionKeywords actions;
//    
//    
//
//    public void runTest(String excelPath) {
//    	
//
//        List<String[]> steps = ExcelReader.getData(excelPath);
//
//        for (String[] step : steps) {
//
//            String keyword = step[0];
//            String locator = step[1];
//            String value = step[2];
//            
//
//            actions = new ActionKeywords(driver);
//            try {
//            	 actions.execute(keyword, locator, value);
//            	 test.pass("step paased:" +keyword);
//            	
//            }catch(Exception e)
//            {
//            	String path=ScreenshotUtil.capture(com.framework.driver.DriverFactory.driver, keyword);
//            	test.fail("step failed :" +keyword).addScreenCaptureFromPath(path);
//            }
////            actions.execute(keyword, locator, value);
//
//           driver = com.framework.driver.DriverFactory.driver;
//        }
//        extent.flush();
//    }
//}

//
//package com.framework.engine;
//
//import com.aventstack.extentreports.*;
//import com.framework.keywords.ActionKeywords;
//import com.framework.reporting.ExtentManager;
//import com.framework.utils.ExcelReader;
//import com.framework.utils.ScreenshotUtil;
//import com.framework.driver.DriverFactory;
//
//import java.util.List;
//import java.util.Map;
//
//public class KeywordEngine {
//
//    public void runTest(String excelPath) {
//
//        ExtentReports extent = ExtentManager.getInstance();
//        ExtentTest test = extent.createTest("Keyword Test");
//
//        List<String[]> steps = ExcelReader.getSteps(excelPath);
//        Map<String, String> testData = ExcelReader.getTestData(excelPath);
//
//        ActionKeywords actions = new ActionKeywords();
//
//        int stepNum = 1;
//
//        for (String[] step : steps) {
//
//            String page = step[0];
//            String keyword = step[1];
//            String locator = step[2];
//            String dataKey = step[3];
//            String run = step[4];
//
//            if (!run.equalsIgnoreCase("Y")) continue;
//
//            String value = testData.getOrDefault(dataKey, dataKey);
//
//            String stepName = "Step_" + stepNum + "_" + keyword;
//
//            try {
//                actions.execute(keyword, locator, value);
//
//                String path = ScreenshotUtil.capture(DriverFactory.driver, stepName);
//
//                test.pass(stepName).addScreenCaptureFromPath(path);
//
//            } catch (Exception e) {
//
//                String path = ScreenshotUtil.capture(DriverFactory.driver, stepName);
//
//                test.fail(stepName + " | " + e.getMessage())
//                    .addScreenCaptureFromPath(path);
//            }
//
//            stepNum++;
//        }
//
//        extent.flush();
//    }
//}

package com.framework.engine;

import com.aventstack.extentreports.*;
import com.framework.keywords.ActionKeywords;
import com.framework.reporting.ExtentManager;
import com.framework.utils.ExcelReader;
import com.framework.utils.ScreenshotUtil;
import com.framework.driver.DriverFactory;

import java.util.List;
import java.util.Map;

public class KeywordEngine {

    public void runTest(String excelPath) {

        // 🔹 Create report
        ExtentReports extent = ExtentManager.getInstance();
        ExtentTest test = extent.createTest("Keyword Test");

        // 🔹 Read Excel
        List<String[]> steps = ExcelReader.getSteps(excelPath);
        Map<String, String> testData = ExcelReader.getTestData(excelPath);

        // 🔹 Create action object
        ActionKeywords actions = new ActionKeywords();

        int stepNum = 1;

        // 🔁 Loop steps
        for (String[] step : steps) {

            String pageName = step[0];
            String keyword = step[1];
            String locator = step[2];
            String dataKey = step[3];
            String run = step[4];

            //  Skip step if run = N
            if (!run.equalsIgnoreCase("Y")) continue;

            // Get actual value from Sheet2
            String value = testData.getOrDefault(dataKey, dataKey);

            String stepName = "Step_" + stepNum + "_" + keyword;

            try {
                // 🔹 Execute keyword
                actions.execute(keyword, locator, value);

                // 🔹 Capture screenshot safely
                String path = null;

                if (DriverFactory.driver != null) {
                    path = ScreenshotUtil.capture(DriverFactory.driver, stepName);
                }

                // 🔹 Add to report
                if (path != null) {
                    test.pass(stepName)
                        .addScreenCaptureFromPath(path);
                } else {
                    test.pass(stepName + " (No Screenshot)");
                }

            } catch (Exception e) {

                // 🔹 Capture screenshot on failure
                String path = null;

                if (DriverFactory.driver != null) {
                    path = ScreenshotUtil.capture(DriverFactory.driver, stepName);
                }

                // 🔹 Add failure to report
                if (path != null) {
                    test.fail(stepName + " | " + e.getMessage())
                        .addScreenCaptureFromPath(path);
                } else {
                    test.fail(stepName + " | " + e.getMessage());
                }
            }

            stepNum++;
        }

        // 🔹 Flush report (VERY IMPORTANT)
        extent.flush();
    }
}