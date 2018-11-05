package com.test.testcase;

import org.testng.annotations.Test;

import com.test.actions.SeleniumActionsKeyWords;
import com.test.utilities.Constants;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
/*
 *  This class ist the test class
 *  for google search scenario
 * 
 */
public class GoogleSearchTestCase{

	
	public static String testcasename;
	public static boolean ispassed = true;

	@Test
	public void searchAction() {
		
		/*
		 * set search input of google with value from Test Data
		 */
	}

	/*
	 * This method will be called before each scenario
	 */
	@BeforeMethod
	public void beforeMethod() {
	
		SeleniumActionsKeyWords.initWebDriver(Constants.CHROME);
		SeleniumActionsKeyWords.initAppModel();
	}
	
/*
 * Would be called after each test case scenario
 */
	@AfterMethod
	public void afterMethod() {
		SeleniumActionsKeyWords.cleanDriver();
	}
	

}
