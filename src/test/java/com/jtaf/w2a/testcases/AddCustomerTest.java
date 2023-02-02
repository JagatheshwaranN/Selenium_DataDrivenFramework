package com.jtaf.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jtaf.w2a.common.ReusableComponent;

public class AddCustomerTest extends ReusableComponent {

	@Test(dataProvider = "getData")
	public void addCustomer(String firstName, String lastName, String postCode) throws InterruptedException {
		
		driver.findElement(By.cssSelector(getDataFromPropFile("BankManagerLogin"))).click();
		log.debug("Logged in as Bank Manager");
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(getDataFromPropFile("AddCustomerButton"))).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(getDataFromPropFile("AC_FirstName"))).sendKeys(firstName);
		driver.findElement(By.cssSelector(getDataFromPropFile("AC_LastName"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(getDataFromPropFile("AC_PostCode"))).sendKeys(postCode);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(getDataFromPropFile("AC_AddButton"))).click();
		Thread.sleep(5000);
		
	}

	@DataProvider
	public Object[][] getData() {

		String sheetName = getDataFromPropFile("customerAddTest");
		System.out.println("sheetName ............."+sheetName);
		int rowCount = excelReader.getRowCount(sheetName);
		int colCount = excelReader.getColumnCount(sheetName);
		Object[][] data = new Object[rowCount - 1][colCount];
		for (int rownum = 2; rownum <= rowCount; rownum++) {
			for (int colnum = 0; colnum < colCount; colnum++) {
				data[rownum - 2][colnum] = excelReader.getCellData(sheetName, colnum, rownum);
			}
		}
		System.out.println("control reach before end");
		return data;
	}
}
