package com.framework.keywords;

import com.framework.utils.ObjectRepository;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableFunctions {

	WebDriver driver;
	// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public ReusableFunctions(WebDriver driver) {
		this.driver = driver;
	}

	public void openUrl(String url) {
		driver.get(url);
	}

	public void click(String locator) {
		try {
			driver.findElement(ObjectRepository.getLocator(locator)).click();
		} catch (Exception e) {

		}
	}

	public void enterText(String locator, String value) {
		driver.findElement(ObjectRepository.getLocator(locator)).sendKeys(value);
	}

	public void scrollToElement(String locator) {

//        WebElement element = driver.findElement(
//                ObjectRepository.getLocator(locator)
		// );
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement element = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ObjectRepository.getLocator(locator)));
			System.out.println("yes elemnt is visible");

			JavascriptExecutor js = (JavascriptExecutor) driver;
			// js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
			js.executeScript("arguments[0].scrollIntoView(true)", element);
		} catch (Exception e) {
			System.out.println("element not found :" + e.getMessage());
		}
	}
	
	//**************** click the element if the element is visible

//	public void clickWhenVisible(String locator) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		try {
//			WebElement element = wait
//					.until(ExpectedConditions.visibilityOfElementLocated(ObjectRepository.getLocator(locator)));
//			element.click();
//			waitTime("3000");
//
//			System.out.println("clicked");
//		} catch (Exception e) {
//			String msg = e.getMessage();
//			System.out.println(msg);
//			// System.out.println("not abe to find tye element so not clickable");
//		}
//	}
	
	
	public void clickWhenVisible(String locator) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        WebElement element = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                ObjectRepository.getLocator(locator)));

	        String parent = driver.getWindowHandle();

	        element.click();

	        Set<String> tabs = driver.getWindowHandles();

	        for(String tab : tabs) {
	            if(!tab.equals(parent)) {
	                driver.switchTo().window(tab);
	                break;
	            }
	        }

	        System.out.println(driver.getTitle());

	    } catch(Exception e) {
	        System.out.println(e.getMessage());
	    }
	}

	public void waitTime(String value) {
		try {
			Thread.sleep((long) Double.parseDouble(value));
			// Thread.sleep(Long.parseLong(value));
			System.out.println("waited successfully");
		} catch (Exception e) {
			String msg = e.getMessage();
			System.out.println(msg);

		}
	}

	public void checkVisibilityOfElement(String locator)

	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(ObjectRepository.getLocator(locator)));
		} catch (Exception e) {
			System.out.println("element is not visible");
		}

	}
	//for javascript click
	public void jsclick(String locator) {

	    WebDriverWait wait =
	            new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {

	        WebElement element =
	                wait.until(ExpectedConditions
	                .elementToBeClickable(
	                ObjectRepository.getLocator(locator)));

	        element.click();

	    } catch(Exception e) {

	        try {

	            WebElement element =
	                    driver.findElement(
	                    ObjectRepository.getLocator(locator));

	            JavascriptExecutor js =
	                    (JavascriptExecutor) driver;

	            js.executeScript(
	                    "arguments[0].click();",
	                    element);

	            System.out.println("JS click used");

	        } catch(Exception ex) {

	            System.out.println(ex.getMessage());
	        }
	    }
	}
	//checkVisibility only
	public void checkvisibility(String locator)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	  try {
		  wait.until(ExpectedConditions.presenceOfElementLocated(ObjectRepository.getLocator(locator)));
		  System.out.println("element is visible");
	  }
	  catch(Exception e)
	  {
		  System.out.println("element not visible");
	  }
	}
}