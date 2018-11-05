package com.test.actions;

import java.util.concurrent.TimeUnit;

import javax.swing.text.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.lang3.reflect.FieldUtils;
import com.test.pageobjects.GooglePageObject;
import com.test.testcase.GoogleSearchTestCase;
import com.test.utilities.Constants;
import com.test.utilities.Utils;

/*
 * This class is the master class of all Test Cases Classes : it conains all UI actions
 */
public class SeleniumActionsKeyWords {

	protected static WebDriver driver;
	protected static WebDriverWait wait;

	public static void initAppModel() {

		PageFactory.initElements(driver, GooglePageObject.class);
	}

	/*
	 * Set up webdriver and implicit wait
	 */
	public static void initWebDriver(String sBrowserName) {

		try {

			Utils.logger.info("Initializing " + sBrowserName + " driver");

			switch (sBrowserName) {

			case Constants.CHROME:
				driver = new ChromeDriver();
				break;
			case Constants.IE:
				driver = new InternetExplorerDriver();
				break;

			default:
				driver = new FirefoxDriver();
				break;
			}

			Utils.logger.info("New driver instantiated");
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, Constants.TIME_OUT_SECONDS);
			Utils.logger.info("Navigation To  " + Constants.URL + " URL");
			driver.navigate().to(Constants.URL);
		} catch (Exception e) {
			Utils.logger.error("Class Utilities | Method OpenBrowser | Exception desc : " + e.getMessage());
			GoogleSearchTestCase.ispassed = false;
		}
	}

	public static void cleanDriver() {
		Utils.logger.info("Driver will be closed");
		driver.quit();
	}
	public static void KeyClick(WebElement obj) {

		if (WaitForElementToBePresent(obj) & WaitForElementToBeEnabled(obj)) {
			Utils.logger.info("Click over " + obj.toString());
			obj.click();
		}

		else {
			Utils.logger.error("Not able to click over :" + getLocator(obj));
			GoogleSearchTestCase.ispassed = false;
		}
	}
	
	public static By getLocator(WebElement element) {
		Object findBy = null;
		try {
			Object proxyOrigin = FieldUtils.readField(element, "h", true);
			Object locator = FieldUtils.readField(proxyOrigin, "locator", true);
			findBy = FieldUtils.readField(locator, "by", true);
			if (findBy != null) {
				return (By) findBy;
			}
		} catch (IllegalAccessException ignored) {
		}
		return (By) findBy;
	}
	public static boolean WaitForElementToBePresent(WebElement elt) {

		try {
			Utils.logger.info("Wait for element with locator " +getLocator(elt) + " to be present");
			wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(elt)));
			return true;
		} catch (Exception e) {
			Utils.logger.error("No such element with locator  " + getLocator(elt) + " is present | Exception desc :"
					+ e.toString());
			GoogleSearchTestCase.ispassed = false;
			return false;
		}
	}
	public static boolean WaitForElementToBeEnabled(WebElement elt) {

		try {
			Utils.logger.info("Wait for element with locator: " + getLocator(elt) + " to be enabled");
			wait.until(ExpectedConditions.elementToBeClickable(getLocator(elt)));
			return true;
		} catch (Exception e) {
			Utils.logger.error("The webelement with locator: " + getLocator(elt) + " is not enabled| Exception desc :"
					+ e.toString());
			GoogleSearchTestCase.ispassed = false;
			return false;
		}
	}
	
	public static void SetText(WebElement obj, String text) throws InterruptedException {

		if (WaitForElementToBePresent(obj)) {
			Utils.logger.info("Enter text :" + text + " on element with locator: " +getLocator(obj));
			obj.clear();
			
			//***** some times Selenium is so fast in setting text so we put a small wait
			Thread.sleep(500);
			obj.sendKeys(text);
		} else {
			Utils.logger.error("Setting text of " +getLocator(obj) + " fail");
			GoogleSearchTestCase.ispassed = false;
		}
	}
}
