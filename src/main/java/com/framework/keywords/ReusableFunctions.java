package com.framework.keywords;

import com.framework.engine.KeywordEngine;
import com.framework.utils.ObjectRepository;

import jakarta.mail.BodyPart;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Part;
import jakarta.mail.Session;
import jakarta.mail.Store;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.framework.utils.ExcelWriter;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableFunctions {
//	public static  String NewCustomFunction = null;
	String TestValue;
	String current = "";

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

	// **************** click the element if the element is visible

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

	// this is for like u click something and it move to the new tab
	public void SwitchOnwindow(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			WebElement element = wait
					.until(ExpectedConditions.visibilityOfElementLocated(ObjectRepository.getLocator(locator)));

			Set<String> oldTabs = driver.getWindowHandles();

			element.click();

			Set<String> newTabs = oldTabs;
			for (int i = 0; i < 10; i++) {
				newTabs = driver.getWindowHandles();
				if (newTabs.size() > oldTabs.size()) {
					break;
				}
				Thread.sleep(500);
			}

			for (String tab : newTabs) {
				if (!oldTabs.contains(tab)) {
					driver.switchTo().window(tab);
					driver.navigate().refresh();
					break;
				}
			}
			System.out.println("----------------------------------------------------------------------------------");
   			System.out.println(driver.getTitle());

		} catch (Exception e) {
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
			System.out.println("element displayed");
		} catch (Exception e) {
			System.out.println("element is not visible");
		}

	}

	// for javascript click
	public void jsclick(String locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {

			WebElement element = wait
					.until(ExpectedConditions.elementToBeClickable(ObjectRepository.getLocator(locator)));

			element.click();

		} catch (Exception e) {

			try {

				WebElement element = driver.findElement(ObjectRepository.getLocator(locator));

				JavascriptExecutor js = (JavascriptExecutor) driver;

				js.executeScript("arguments[0].click();", element);

				System.out.println("JS click used");

			} catch (Exception ex) {

				System.out.println(ex.getMessage());
			}
		}
	}

	// checkVisibility only
	public void checkvisibility(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(ObjectRepository.getLocator(locator)));
			System.out.println("element is visible");
		} catch (Exception e) {
			System.out.println("element not visible");
		}
	}

	// make sure this is currect
	public String getText(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			WebElement ele = wait
					.until(ExpectedConditions.presenceOfElementLocated(ObjectRepository.getLocator(locator)));
			TestValue = ele.getText();
			System.out.println(TestValue);
		} catch (Exception e) {
			System.out.println("unable to get the text");
		}
		return TestValue;
	}

	public void navigateback(String value) {
		if (value.equalsIgnoreCase("Back")) {
			driver.navigate().back();
			System.out.println("-------------------yes it navigated perfectfully---------------------");
		} else {
			driver.navigate().to(value);
			System.out.println("---------------------yes the else block navigated back successfully----------------------");
		}
	}

//	public  String GetwindowHandle()
//	
//	{
//	 current=driver.getWindowHandle().trim();
//	 System.out.println(current);
//		return current;
//	
//	}
//	public void  SwitchtoParent()
//	{
//		String value=GetwindowHandle();
//		
//		
//		
//		
//	}
	public void windowswitchtoparent(String value) {


		System.out.println("Total Window Is :- " + driver.getWindowHandles());
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		try {
			if(tabs.size() >0)
			{
				driver.switchTo().window(tabs.get(Integer.parseInt(value)));
				System.out.println("=== Window Switch Successfully ===");
				
			}
		}
		catch(Exception e)
		{
			driver.switchTo().window(tabs.get(0));
			System.out.println("------------------it switched successfully to the parent window--------------------------");
		}
//		driver.switchTo().window(tabs.get(Integer.parseInt(value)));
//		System.out.println("=== Window Switch Successfully ===");

	}

	// for gmail otp

	public static String fetchOtp(String email, String appPassword) throws Exception {
		String Value = KeywordEngine.value.trim();
		String[] value = Value.split("#");
		email = value[0];
		appPassword = value[1];

		Properties props = new Properties();
		props.put("mail.store.protocol", "imaps");

		Session session = Session.getDefaultInstance(props);

		Store store = session.getStore("imaps");
		store.connect("imap.gmail.com", email, appPassword);

		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);

		Message[] messages = inbox.getMessages();

		for (int i = messages.length - 1; i >= 0; i--) {

			Message msg = messages[i];

			// ---------------- EXTRACT TEXT (INLINE) ----------------
			String body = "";

			if (msg.isMimeType("text/plain") || msg.isMimeType("text/html")) {
				body = msg.getContent().toString();
			} else if (msg.isMimeType("multipart/*")) {

				Multipart multipart = (Multipart) msg.getContent();
				StringBuilder result = new StringBuilder();

				for (int j = 0; j < multipart.getCount(); j++) {

					BodyPart part = multipart.getBodyPart(j);

					if (part.isMimeType("text/plain") || part.isMimeType("text/html")) {
						result.append(part.getContent().toString());
					} else if (part.getContent() instanceof Multipart) {

						Multipart inner = (Multipart) part.getContent();

						for (int k = 0; k < inner.getCount(); k++) {
							BodyPart innerPart = inner.getBodyPart(k);

							if (innerPart.isMimeType("text/plain") || innerPart.isMimeType("text/html")) {
								result.append(innerPart.getContent().toString());
							}
						}
					}
				}

				body = result.toString();
			}

			// ---------------- OTP EXTRACTION (INLINE) ----------------
			if (body != null) {

				Pattern pattern = Pattern.compile("\\b(OTP|verification code|passcode)[^\\d]{0,10}(\\d{4,8})\\b",
						Pattern.CASE_INSENSITIVE);

				Matcher matcher = pattern.matcher(body);

				if (matcher.find()) {

					inbox.close(false);
					store.close();

					return matcher.group(2);
				}

				// fallback OTP
				Pattern fallback = Pattern.compile("\\b\\d{4,8}\\b");
				Matcher m2 = fallback.matcher(body);

				if (m2.find()) {

					inbox.close(false);
					store.close();

					return m2.group();
				}
			}
		}

		inbox.close(false);
		store.close();

		return null;
	}

	public static void movepage(String pagename) {
		try {
			if (pagename.equals(null) && pagename.equals("")) {
				System.out.println("move next");
			} else if (!pagename.equalsIgnoreCase("hme")) {

				KeywordEngine.run = "N";
			} else {
				System.out.println();
			}
		} catch (Exception e) {
			e.getMessage();

		}
	}

	//

	// ==================== COMPARE AND ADD TO CART ====================

	/**
	 * Iterates through N product cards from search results, opens each product,
	 * checks price for each available size, compares all prices, and adds to cart
	 * the product with the lowest price. If prices are equal, picks the highest size.
	 * Writes all data to an Excel file and prints to console.
	 */
	public void compareAndAddToCart(int productCount) {

		List<Map<String, String>> allProductData = new ArrayList<>();

		String searchWindowHandle = driver.getWindowHandle();

		try {
			// Wait for search results to load
			Thread.sleep(3000);

			// Get all product card links
			List<WebElement> productCards = driver.findElements(
					By.xpath("//a[contains(@class,'CIaYa1')]"));

			if (productCards.isEmpty()) {
				// Try alternate selector
				productCards = driver.findElements(
						By.xpath("//div[contains(@class,'tUxRFH')]//a"));
			}

			int count = Math.min(productCount, productCards.size());
			System.out.println("\n=== Found " + productCards.size() + " products, will check " + count + " ===");

			// Collect product URLs first (elements go stale after navigation)
			List<String> productUrls = new ArrayList<>();
			for (int i = 0; i < count; i++) {
				try {
					String href = productCards.get(i).getAttribute("href");
					if (href != null && !href.isEmpty()) {
						productUrls.add(href);
					}
				} catch (Exception e) {
					System.out.println("Could not get URL for product " + (i + 1));
				}
			}

			// Iterate through each product
			for (int i = 0; i < productUrls.size(); i++) {

				System.out.println("\n--- Processing Product " + (i + 1) + " of " + productUrls.size() + " ---");

				try {
					// Open product in new tab
					((JavascriptExecutor) driver).executeScript(
							"window.open('" + productUrls.get(i) + "','_blank');");
					Thread.sleep(2000);

					// Switch to new tab
					ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
					driver.switchTo().window(tabs.get(tabs.size() - 1));
					Thread.sleep(3000);

					// Get product name
					String productName = "Unknown";
					try {
						WebElement nameElem = driver.findElement(
								By.xpath("//div[@class=\"Fo1I0b\"]"));
						productName = nameElem.getText().trim();
					} catch (Exception e) {
						try {
							WebElement nameElem = driver.findElement(
									By.xpath("//h1[contains(@class,'yhB1nd')]//span"));
							productName = nameElem.getText().trim();
						} catch (Exception ex) {
							System.out.println("Could not get product name");
						}
					}

					// Get rating
					String rating = "N/A";
					try {
						WebElement ratingElem = driver.findElement(
								By.xpath("//div[contains(@class,'XQDdHH')]"));
						rating = ratingElem.getText().trim();
					} catch (Exception e) {
						System.out.println("No rating found");
					}

					// Check for size options
					List<WebElement> sizeButtons = driver.findElements(
							By.xpath("(//div[@class='css-g5y9jx' and contains(@style,'align-items:stretch')])[2]"));

					if (sizeButtons.isEmpty()) {
						sizeButtons = driver.findElements(
								By.xpath("//a[contains(@class,'CDDksN')]"));
					}

					if (sizeButtons.isEmpty()) {
						// No sizes - free size product, get current price
						String price = getCurrentPrice();
						Map<String, String> data = new LinkedHashMap<>();
						data.put("productName", productName);
						data.put("size", "Free Size");
						data.put("price", price);
						data.put("rating", rating);
						data.put("productUrl", productUrls.get(i));
						data.put("productIndex", String.valueOf(i));
						data.put("selected", "N");
						allProductData.add(data);

						System.out.println("  " + productName + " | Free Size | " + price + " | Rating: " + rating);
					} else {
						// Iterate through each size
						for (int s = 0; s < sizeButtons.size(); s++) {
							try {
								// Re-find size buttons (DOM may refresh)
								List<WebElement> currentSizeButtons = driver.findElements(
										By.xpath("//ul[contains(@class,'hSEbzK')]//li//a"));
								if (currentSizeButtons.isEmpty()) {
									currentSizeButtons = driver.findElements(
											By.xpath("//a[contains(@class,'CDDksN')]"));
								}

								if (s < currentSizeButtons.size()) {
									WebElement sizeBtn = currentSizeButtons.get(s);
									String sizeName = sizeBtn.getText().trim();

									// Click the size button
									try {
										sizeBtn.click();
									} catch (Exception clickEx) {
										((JavascriptExecutor) driver).executeScript(
												"arguments[0].click();", sizeBtn);
									}
									Thread.sleep(1500);

									String price = getCurrentPrice();

									Map<String, String> data = new LinkedHashMap<>();
									data.put("productName", productName);
									data.put("size", sizeName);
									data.put("price", price);
									data.put("rating", rating);
									data.put("productUrl", productUrls.get(i));
									data.put("productIndex", String.valueOf(i));
									data.put("sizeIndex", String.valueOf(s));
									data.put("selected", "N");
									allProductData.add(data);

									System.out.println("  " + productName + " | Size: " + sizeName
											+ " | Price: " + price + " | Rating: " + rating);
								}
							} catch (Exception sizeEx) {
								System.out.println("  Error processing size " + s + ": " + sizeEx.getMessage());
							}
						}
					}

					// Close product tab and switch back to search
					driver.close();
					driver.switchTo().window(searchWindowHandle);
					Thread.sleep(1000);

				} catch (Exception productEx) {
					System.out.println("Error processing product " + (i + 1) + ": " + productEx.getMessage());
					// Make sure we switch back to search
					try {
						driver.switchTo().window(searchWindowHandle);
					} catch (Exception e) {
						// ignore
					}
				}
			}

			// ======== FIND THE BEST PRODUCT (lowest price, highest size if tied) ========

			if (allProductData.isEmpty()) {
				System.out.println("\n=== No product data collected ===");
				return;
			}

			int bestIndex = 0;
			int bestPrice = parsePrice(allProductData.get(0).get("price"));
			int bestSizeRank = getSizeRank(allProductData.get(0).get("size"));

			for (int i = 1; i < allProductData.size(); i++) {
				int currentPrice = parsePrice(allProductData.get(i).get("price"));
				int currentSizeRank = getSizeRank(allProductData.get(i).get("size"));

				if (currentPrice < bestPrice) {
					bestPrice = currentPrice;
					bestSizeRank = currentSizeRank;
					bestIndex = i;
				} else if (currentPrice == bestPrice && currentSizeRank > bestSizeRank) {
					bestSizeRank = currentSizeRank;
					bestIndex = i;
				}
			}

			// Mark the selected product
			allProductData.get(bestIndex).put("selected", "Y");

			Map<String, String> winner = allProductData.get(bestIndex);
			System.out.println("\n============================================");
			System.out.println("  BEST DEAL FOUND!");
			System.out.println("  Product : " + winner.get("productName"));
			System.out.println("  Size    : " + winner.get("size"));
			System.out.println("  Price   : " + winner.get("price"));
			System.out.println("  Rating  : " + winner.get("rating"));
			System.out.println("============================================\n");

			// ======== ADD THE BEST PRODUCT TO CART ========

			String winnerUrl = winner.get("productUrl");
			((JavascriptExecutor) driver).executeScript(
					"window.open('" + winnerUrl + "','_blank');");
			Thread.sleep(3000);

			ArrayList<String> allTabs = new ArrayList<>(driver.getWindowHandles());
			driver.switchTo().window(allTabs.get(allTabs.size() - 1));
			Thread.sleep(3000);

			// Select the correct size if applicable
			String winnerSize = winner.get("size");
			if (!winnerSize.equals("Free Size")) {
				String sizeIndex = winner.getOrDefault("sizeIndex", "0");
				try {
					List<WebElement> sizeBtns = driver.findElements(
							By.xpath("//ul[contains(@class,'hSEbzK')]//li//a"));
					if (sizeBtns.isEmpty()) {
						sizeBtns = driver.findElements(
								By.xpath("//a[contains(@class,'CDDksN')]"));
					}
					int idx = Integer.parseInt(sizeIndex);
					if (idx < sizeBtns.size()) {
						try {
							sizeBtns.get(idx).click();
						} catch (Exception e) {
							((JavascriptExecutor) driver).executeScript(
									"arguments[0].click();", sizeBtns.get(idx));
						}
						Thread.sleep(2000);
						System.out.println("Selected size: " + winnerSize);
					}
				} catch (Exception e) {
					System.out.println("Could not select size: " + e.getMessage());
				}
			}

			// Click Add to Cart
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'add to cart')]")));
				addToCartBtn.click();
				System.out.println("=== ADDED TO CART SUCCESSFULLY ===");
				Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("Could not click Add to Cart: " + e.getMessage());
			}

			// ======== WRITE TO EXCEL ========

			String excelPath = System.getProperty("user.dir") + "/src/test/resources/ProductComparison.xlsx";
			ExcelWriter.writeProductComparison(excelPath, allProductData);

			// ======== PRINT SUMMARY TABLE TO CONSOLE ========

			System.out.println("\n================== PRODUCT COMPARISON TABLE ==================");
			System.out.printf("%-5s %-40s %-10s %-12s %-8s %-8s%n",
					"Sr", "Product Name", "Size", "Price", "Rating", "Selected");
			System.out.println("--------------------------------------------------------------");

			for (int i = 0; i < allProductData.size(); i++) {
				Map<String, String> p = allProductData.get(i);
				String name = p.get("productName");
				if (name.length() > 38) name = name.substring(0, 38) + "..";
				System.out.printf("%-5d %-40s %-10s %-12s %-8s %-8s%n",
						(i + 1), name, p.get("size"), p.get("price"),
						p.get("rating"), p.get("selected"));
			}
			System.out.println("==============================================================\n");

		} catch (Exception e) {
			System.out.println("Error in compareAndAddToCart: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Gets the current price displayed on the product detail page.
	 */
	private String getCurrentPrice() {
		try {
			WebElement priceElem = driver.findElement(
					By.xpath("//div[contains(@class,'Nx9bqj') and contains(@class,'CxhGGd')]"));
			return priceElem.getText().trim();
		} catch (Exception e) {
			try {
				WebElement priceElem = driver.findElement(
						By.xpath("//div[contains(@class,'Nx9bqj')]"));
				return priceElem.getText().trim();
			} catch (Exception ex) {
				return "0";
			}
		}
	}

	/**
	 * Parses price text like "₹1,299" to integer 1299.
	 */
	public static int parsePrice(String priceText) {
		if (priceText == null || priceText.isEmpty()) return Integer.MAX_VALUE;
		try {
			String cleaned = priceText.replaceAll("[^0-9]", "");
			if (cleaned.isEmpty()) return Integer.MAX_VALUE;
			return Integer.parseInt(cleaned);
		} catch (Exception e) {
			return Integer.MAX_VALUE;
		}
	}

	/**
	 * Returns a numeric rank for size comparison.
	 * Higher rank = bigger size. Used to pick the biggest size when prices are tied.
	 */
	public static int getSizeRank(String size) {
		if (size == null) return 0;
		String s = size.trim().toUpperCase();
		switch (s) {
			case "XS": return 1;
			case "S": return 2;
			case "M": return 3;
			case "L": return 4;
			case "XL": return 5;
			case "XXL": case "2XL": return 6;
			case "XXXL": case "3XL": return 7;
			case "4XL": return 8;
			case "5XL": return 9;
			case "FREE SIZE": return 5; // treat free size as middle
			default:
				// Try to parse numeric sizes (e.g., "28", "30", "32")
				try {
					return Integer.parseInt(s);
				} catch (Exception e) {
					return 0;
				}
		}
	}
}