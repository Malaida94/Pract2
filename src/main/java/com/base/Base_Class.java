package com.base;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import junit.framework.Assert;

public class Base_Class {
	public static WebDriver driver;

	public static ExtentReports extentReports;
	
	public static File file;

	public static JavascriptExecutor javaScriptExecutor;


	protected static WebDriver launchBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
			}
		} catch (Exception e) {
			Assert.fail("ERROR :  OCCUR DURING BROWSER LAUNCH");
		}
		driver.manage().window().maximize();
		return driver;
	}

	protected static WebDriver launchUrl(String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING URL LAUNCH");
		}
		return driver;
	}

	protected static void closeBrowser() {
		try {
			driver.close();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING BROWSER CLOSE");
		}
	}

	protected static void terminateBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING TERMINATE BROWSER");
		}
	}

	protected static void passInput(WebElement element, String input) {
		try {
			element.sendKeys(input);

		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING VALUE PASSING");
		}
	}

	protected static void navigatePage(String url) {
		try {
			driver.navigate().to(url);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING NAVIGATE TO OTHER URL");
		}
	}

	protected static void clickElement(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING ELEMENT CLICK");
		}
	}

	protected static Select dropDownObject(WebElement element) {
		Select select = new Select(element);
		return select;
	}

	protected static void selectByVisibleText(WebElement element, String text) {
		try {
			dropDownObject(element).selectByVisibleText(text);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING SELECT BY VISIBLE TEXT");
		}
	}

	protected static void selectByIndex(WebElement element, int num) {
		try {
			dropDownObject(element).selectByIndex(num);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING SELECT INDEX");
		}

	}

	protected static void validation(String actual, String excepected) {
		try {
			Assert.assertEquals(actual, excepected);
		} catch (Exception e) {

			Assert.fail("ERROR : OCCUR DURING VALIDATION");
		}
	}

	protected static void windowsHandles(int num) {
		try {
			List<String> allWindow = new ArrayList(driver.getWindowHandles());
			driver.switchTo().window(allWindow.get(num));

		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING WINDO HANDLE");
		}
	}

	protected static void implicitWait(String type, int num) {
		try {
			if (type.equalsIgnoreCase("sec")) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(num));
			} else if (type.equalsIgnoreCase("min")) {
				driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(num));
			}
		} catch (Exception e) {
			Assert.fail("ERROR :OCCURED DURING VALUE DESELECTION");
		}
	}
	protected static void localWait(int sec) throws InterruptedException {
		try {
			Thread.sleep(sec);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING ELEMENT WAIT");
		}
	}

	public static void extentReportStart(String location) {
		try {
			extentReports = new ExtentReports();
			file = new File(location);
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
			extentReports.attachReporter(sparkReporter);
			extentReports.setSystemInfo("OS", System.getProperty("os.name"));
			extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail("ERROR : OCCUR DURING EXTENT REPORT START");
		}
	}

	public static void extentReportTearDown(String location) throws IOException {
		try {
			extentReports.flush();
			file = new File(location);
			Desktop.getDesktop().browse((file).toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Assert.fail("ERROR : OCCUR DURING EXTENT REPORT TEAR DOWN");
		}
	}

	public String takeScreenshot() throws IOException {
		try {
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			File scrfile = screenshot.getScreenshotAs(OutputType.FILE);
			File destfile = new File("Screenshort\\.png" + "_" + timeStamp + ".png");
			FileUtils.copyFile(scrfile, destfile);
			return destfile.getAbsolutePath();
		} catch (WebDriverException e) {
			
			Assert.fail("ERROR: OCCURE DURING TAKESCREENSHOT ");
		}
		return null;
		
	}
	
	public static void javaScriptSenkeys(WebElement element, String value) {
		try {
			javaScriptExecutor = (JavascriptExecutor) driver;
			javaScriptExecutor.executeScript("arguments[0].value=arguments[1];", element, value);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING JAVASCRIPT SENDKEYS");
		}
	}

	public static void javaScriptClick(WebElement element) {
		try {
			javaScriptExecutor = (JavascriptExecutor) driver;
			javaScriptExecutor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING JAVASCRIPT CLICK");
		}
	}

	public static void scrollDown(int pixels) {
		try {
			javaScriptExecutor = (JavascriptExecutor) driver;
			javaScriptExecutor.executeScript("window.scrollBy(0," + pixels + ")", ""); // Scrolls down by the specified
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING JAVASCRIPT SCROLLDOWN");
		}
	}

	// Method to scroll up the page by a specified number of pixels
	public static void scrollUp(int pixels) {
		try {
			javaScriptExecutor = (JavascriptExecutor) driver;
			javaScriptExecutor.executeScript("window.scrollBy(0,-" + pixels + ")", ""); // Scrolls up by the specified
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING JAVASCRIPT SCROLLUP");
		}
																					// number of pixels
	}

	public static void horizontalRight(int pixels) {
		try {
			javaScriptExecutor = (JavascriptExecutor) driver;
			javaScriptExecutor.executeScript("window.scrollBy(arguments[0], 0)", pixels);
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING JAVASCRIPT HORIZONTAL RIGHT");
		}
		; // Scrolls up by the specified
	}

	public static void calendarClick(WebElement datePickerElement) {
		try {
			// Open the calendar
			datePickerElement.click();

			// Get the current day
			LocalDate currentDate = LocalDate.now();
			int currentDay = currentDate.getDayOfMonth();

			List<WebElement> dateElements = driver.findElements(By.xpath("//table/tbody/tr/td"));
			// Iterate through the calendar dates and click on the current day
			for (WebElement dateElement : dateElements) {
				String day = dateElement.getText();
				if (!day.isEmpty() && Integer.parseInt(day) == currentDay) {
					dateElement.click();
					System.out.println("Selected date: " + currentDay);
					break;
				}
			}
		} catch (Exception e) {
			System.err.println("Error selecting the current date: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void allTextGet(List<WebElement> Elements) {
		try {
			for (WebElement webElement : Elements) {
				String text = webElement.getText();
				System.out.println(text);
			}
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING GET ALL TEXT");
		}
	}

	protected static void selectOptions(WebElement element, String type, String value) {
		try {

			Select select = new Select(element);
			if (type.equalsIgnoreCase("text")) {
				select.selectByVisibleText(value);
			} else if (type.equalsIgnoreCase("index")) {
				select.selectByIndex(Integer.parseInt(value));
			} else if (type.equalsIgnoreCase("value")) {
				select.selectByValue(value);
			} else {
				System.out.println("Invalid selection type. Use 'text', 'index', or 'value'.");
			}
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING VALUE SELECT");
		}
	}

	protected static void deselectOption(WebElement element, String type, String value) {
		try {
			Select select = new Select(element);
			if (select.isMultiple()) {
				if (type.equalsIgnoreCase("text")) {
					select.deselectByVisibleText(value);
				} else if (type.equalsIgnoreCase("index")) {
					select.deselectByIndex(Integer.parseInt(value));
				} else if (type.equalsIgnoreCase("value")) {
					select.deselectByValue(value);
				} else {
					System.out.println("Invalid deselection type. Use 'text', 'index', or 'value'.");
				}
			} else {
				System.out.println("Deselect operation not supported for single-select dropdown.");
			}
		} catch (NumberFormatException e) {
			Assert.fail("ERROR : OCCUR DURING DE-SELECT OPTIONS");
		}
	}

	protected static void switchToFrame(WebElement element, String frameType, int num) {
		try {
			
		if (frameType.equalsIgnoreCase("index")) {
			driver.switchTo().frame(Integer.parseInt(frameType));

		} else if (frameType.equalsIgnoreCase("name")) {
			driver.switchTo().frame(element);

		} else if (frameType.equalsIgnoreCase("element")) {
			driver.switchTo().frame(element);
		}
		} catch (Exception e) {
			Assert.fail("ERROR : OCCUR DURING SWITCH TO FRAME");
			
		}
	}
	
	protected static void text(WebElement element) {

		try {
			Thread.sleep(5000);
			String textValue = element.getText();
			System.out.println("Current Text  is : " + textValue);

		} catch (Exception e) {
			Assert.fail("ERROR :OCCURED DURING GETTING TITLE");
		}
	}

}

