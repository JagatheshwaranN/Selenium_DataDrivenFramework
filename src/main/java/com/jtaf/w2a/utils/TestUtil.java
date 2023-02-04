package com.jtaf.w2a.utils;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.jtaf.w2a.common.ReusableComponent;

public class TestUtil extends ReusableComponent {

	@DataProvider(name = "dataFetch")
	public static Object[][] getData(Method method) {

		String sheetName = method.getName();
		int totalRows = excelReader.getRowCount(sheetName);
		int totalCols = excelReader.getColumnCount(sheetName);
		Object[][] data = new Object[totalRows - 1][totalCols];

		for (int rowNum = 2; rowNum <= totalRows; rowNum++) {
			for (int colNum = 0; colNum < totalCols; colNum++) {
				data[rowNum - 2][colNum] = excelReader.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}

	public static boolean isTestRunnable(String testName, ExcelReader excel) {

		String sheetName = getDataFromPropFile("suiteSheet");
		String testCaseColumn = getDataFromPropFile("testColumn");
		String runTypeColumn = getDataFromPropFile("runColumn");
		System.out.println("=========>>>>" + sheetName + " " + testCaseColumn + " " + runTypeColumn);
		int rows = excel.getRowCount(sheetName);
		for (int row = 2; row <= rows; row++) {
			String testCase = excel.getCellData(sheetName, testCaseColumn, row);
			System.out.println("=========>>>>" + testCase);
			System.out.println("=========>>>>" + testName);
			if (testCase.equalsIgnoreCase(testName)) {
				
				String runMode = excel.getCellData(sheetName, runTypeColumn, row);
				if (runMode.equalsIgnoreCase("Y")) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
