package com.test.testcase;

import org.testng.annotations.Test;

import com.test.actions.SeleniumActionsKeyWords;
import com.test.pageobjects.GooglePageObject;
import com.test.utilities.Constants;
import com.test.utilities.ExcelUtils;
import com.test.utilities.Utils;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
/*
 *  This class is the test class
 *  for google search Use case
 * 
 */
public class GoogleSearchTestCase{

	public static boolean ispassed = true;

	@Test(dataProvider="getTestCaseName_index")
	public void searchAction(int index, String name) throws Exception {

		Utils.startTestCase(name);
		/*
		 * Set the query into search input and submit the form
		 */
		
		SeleniumActionsKeyWords.SetText(GooglePageObject.search_input, ExcelUtils.getCellData(index, Constants.COL_QUERY));
		SeleniumActionsKeyWords.KeyClick(GooglePageObject.search_btn);
		
		/*
		 * Verify the Result Page with expected conditions(nbr result= 10)
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
	
	@DataProvider

	public Object[][] getTestCaseName_index() throws Exception {

		/*
		 * Setting the data source local file and return the TestCase name and index
		 */
		Utils.setDataSourceFile();
		Object[][] testObjArray = ExcelUtils.getTableArray(Constants.PATH_TESTDATA + Constants.FILE_TESTDATA,
				Constants.BOOK_NAME);
		System.out.println(testObjArray.toString());
		return testObjArray;
	}

}
