//
//package com.framework.utils;
//
//import org.openqa.selenium.*;
//import java.io.File;
//import java.nio.file.Files;
//
//public class ScreenshotUtil {
//
//    public static String capture(WebDriver driver, String name) {
//
//        try {
//            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//            String path = System.getProperty("user.dir") + "/screenshots/" + name + ".png";
//
//            Files.copy(src.toPath(), new File(path).toPath());
//
//            return path;
//
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}


package com.framework.utils;

import org.openqa.selenium.*;
import java.io.File;
import java.nio.file.Files;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String name) {

        try {
        	if (driver == null) return null;
            new File(System.getProperty("user.dir") + "/screenshots").mkdirs();

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String path = System.getProperty("user.dir") + "/screenshots/"
                    + name + "_" + System.currentTimeMillis() + ".png";

            Files.copy(src.toPath(), new File(path).toPath());

            return path;

        } catch (Exception e) {
            return null;
        }
    }
}
