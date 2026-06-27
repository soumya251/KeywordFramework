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

	public static String value;
	public static String run;

	public static void runTest(String excelPath) {
		ExtentReports extent = ExtentManager.getInstance();
		ExtentTest test = extent.createTest("Keyword Test");

		List<String[]> steps = ExcelReader.getSteps(excelPath);
		Map<String, String> testData = ExcelReader.getTestData(excelPath);

		ActionKeywords actions = new ActionKeywords();

		int stepNum = 1;

		for (String[] step : steps) {

			String pageName = step[0];
			String keyword = step[1];
			String locator = step[2];
			String dataKey = step[3];
			  run = step[4];

			if (!run.equalsIgnoreCase("Y"))
				continue;

			 value = testData.getOrDefault(dataKey, dataKey);

			String stepName = "Step_" + stepNum + "_" + keyword;

			try {

				actions.execute(keyword, locator, value,pageName,run);

				String path = null;

				if (DriverFactory.driver != null) {
					path = ScreenshotUtil.capture(DriverFactory.driver, stepName);
				}

				if (path != null) {
					test.pass(stepName).addScreenCaptureFromPath(path);
				} else {
					test.pass(stepName + " (No Screenshot)");
				}

			} catch (Exception e) {

				String path = null;

				if (DriverFactory.driver != null) {
					path = ScreenshotUtil.capture(DriverFactory.driver, stepName);
				}

				if (path != null) {
					test.fail(stepName + " | " + e.getMessage()).addScreenCaptureFromPath(path);
				} else {
					test.fail(stepName + " | " + e.getMessage());
				}
			}

			stepNum++;
		}

		extent.flush();
	}
}