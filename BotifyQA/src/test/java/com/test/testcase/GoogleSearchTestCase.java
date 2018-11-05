package com.test.testcase;

import org.testng.annotations.Test;


import com.test.utilities.ExcelUtils;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

/*
 * This class ist the test class
 *  for google search scenario
 * 
 */
public class GoogleSearchTestCase {

	static WebDriver driver;
	static WebDriverWait wait;
	public static String sTestCaseName;
	public static boolean ispassed = true;

	@Test
	public void searchAction() {
		
	}

	/*
	 * This method will be called before each scenario
	 */
	@BeforeMethod
	public void beforeMethod() {
	

	}
	
/*
 * Would be called after each test case scenario
 */
	@AfterMethod
	public void afterMethod() {
		
		
	}
}
