package com.test.actions;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

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

}
