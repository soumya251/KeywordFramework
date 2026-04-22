//package com.framework.reporting;
//
//	import com.aventstack.extentreports.*;
//	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//
//	public class ExtentManager {
//
//	    public static ExtentReports getInstance() {
//
//	        ExtentSparkReporter spark =
//	                new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/ExtentReport.html");
//
//	        ExtentReports extent = new ExtentReports();
//	        extent.attachReporter(spark);
//
//	        return extent;
//	    }
//	}
//

package com.framework.reporting;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {

    public static ExtentReports getInstance() {

        new File(System.getProperty("user.dir") + "/reports").mkdirs();

        ExtentSparkReporter spark =
                new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/ExtentReport.html");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);

        return extent;
    }
}