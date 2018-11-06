package com.test.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.test.testcase.GoogleSearchTestCase;

/*
 * This class implement different methods needed to fetch Test Data of each test Case
 *  from an excel file ( Data Source) and save data into 
 * 
 */
public class ExcelUtils {

	
	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;
	
/*
 * This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
 */

	public static void setExcelFile(String Path, String SheetName) {

		try {

			// Open the Excel file

			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} catch (Exception e) {

			Utils.logger.error("Class ExcelUtils | Method setExcelFile | Exception desc :" + e.getMessage());
			GoogleSearchTestCase.ispassed = false;
		}

	}

//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

	public static String getCellData(int RowNum, int ColNum) {

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();

			return CellData;

		} catch (Exception e) {
			GoogleSearchTestCase.ispassed = false;
			Utils.logger.error("Class ExcelUtils | Method getCellData | Exception desc :" + e.getMessage());
			return "";

		}

	}

/*
 * This method is to write in the Excel cell, Row num and Col num are the parameters
 */

	@SuppressWarnings("static-access")

	public static void setCellData(String Result, int RowNum, int ColNum) {

		try {

			Row = ExcelWSheet.getRow(RowNum);

			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);

			if (Cell == null) {

				Cell = Row.createCell(ColNum);

				Cell.setCellValue(Result);

			} else {

				Cell.setCellValue(Result);

			}
			FileOutputStream fileOut = new FileOutputStream(Constants.PATH_TESTDATA + Constants.FILE_TESTDATA);

			ExcelWBook.write(fileOut);

			fileOut.flush();

			fileOut.close();

		} catch (Exception e) {
			GoogleSearchTestCase.ispassed = false;
			Utils.logger.error("Class ExcelUtils | Method setcellData | Exception desc :" + e.getMessage());

		}

	}

	public static int getRowContains(String sTestCaseName, int colNum) {

		try {
			int i = 0;
			int rowCount = ExcelUtils.getRowUsed();

			for (i = 0; i < rowCount; i++) {

				if (ExcelUtils.getCellData(i, colNum).equalsIgnoreCase(sTestCaseName)) {

					break;

				}

			}

			return i;

		} catch (Exception e) {

			Utils.logger.error("Class ExcelUtil | Method getRowContains | Exception desc : " + e.getMessage());

			GoogleSearchTestCase.ispassed = false;
			return 0;
		}

	}

	public static int getRowUsed() {

		try {

			int RowCount = ExcelWSheet.getLastRowNum();
			return RowCount;

		} catch (Exception e) {

			Utils.logger.error("Class ExcelUtil | Method getRowUsed | Exception desc : " + e.getMessage());
			GoogleSearchTestCase.ispassed = false;
			return 0;

		}

	}

	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {

		Object[] []tabArray = null;

		try {

			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int startRow = 1;
			int totalRows = ExcelUtils.getRowUsed();
			int totalCol=2;
			
			tabArray = new Object[totalRows][totalCol];

			for (int i = startRow; i <=totalRows; i++) {
				
					tabArray[i-1] [0]= i;
					tabArray[i-1] [1]= ExcelUtils.getCellData(i, 0);
				}

			

		}

		catch (FileNotFoundException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return (tabArray);

	}
}
