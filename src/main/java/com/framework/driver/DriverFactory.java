package com.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver driver;

    public static WebDriver initDriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
         //   WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("edge"))
        {
        	driver=new EdgeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox"))
        {
        	driver=new FirefoxDriver();
        }
        else
        {
        	System.out.println("make sure u have given a correct broswer name");
        }

        driver.manage().window().maximize();
        return driver;
    }
}