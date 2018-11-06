package com.test.utilities;

import java.awt.Desktop.Action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.actions.SeleniumActionsKeyWords;
import com.test.pageobjects.GooglePageObject;
import com.test.testcase.GoogleSearchTestCase;

/*
 * This class regrouos all utili
 */
public class Utils {

	public final static Log logger = LogFactory.getLog(GoogleSearchTestCase.class);
	
	public static void setDataSourceFile() {
		
		ExcelUtils.setExcelFile(Constants.PATH_TESTDATA + Constants.FILE_TESTDATA, Constants.BOOK_NAME);
	}
	public static void startTestCase(String sTestCaseName){

		logger.info("****************************************************************************************");

		logger.info("****************************************************************************************");

		logger.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");

		logger.info("****************************************************************************************");

		logger.info("****************************************************************************************");

		}
	public static void verifySearchResult()
	{
		try {
		int total_rows=GooglePageObject.result_container.findElements(SeleniumActionsKeyWords.getLocator(GooglePageObject.result_entry)).size();

	if(total_rows!=10) {
		logger.error("Result page should display 10 result entries");
		GoogleSearchTestCase.ispassed=false;
	}
	else {
		logger.info("Checkpoint is PASSED");
		
	}
		}
		catch(Exception e) {
			GoogleSearchTestCase.ispassed=false;
			logger.error("Class Utils | Method verifySearchResult | Exception desc : " +e.getMessage());
		}
	}

}
