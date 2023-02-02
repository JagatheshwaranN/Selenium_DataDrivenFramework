package com.jtaf.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jtaf.w2a.common.ReusableComponent;

public class AddCustomerTest extends ReusableComponent {

	@Test(dataProvider = "getData")
	public static void addCustomer(String firstName, String lastName, String postCode) throws InterruptedException {

		driver.findElement(By.cssSelector(getDataFromPropFile("BankManagerLogin"))).click();
		log.debug("Logged in as Bank Manager");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(getDataFromPropFile("AddCustomerButton"))).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(getDataFromPropFile("AC_FirstName"))).sendKeys(firstName);
		driver.findElement(By.cssSelector(getDataFromPropFile("AC_LastName"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(getDataFromPropFile("AC_PostCode"))).sendKeys(postCode);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(getDataFromPropFile("AC_AddButton"))).click();
		Thread.sleep(5000);
	}

	@DataProvider
	public static Object[][] getData() {

		String sheetName = getDataFromPropFile("customerAddTest");
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

}
