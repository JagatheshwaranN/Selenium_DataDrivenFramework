package com.jtaf.w2a.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jtaf.w2a.common.ReusableComponent;

public class AddCustomerTest extends ReusableComponent {

	@Test(dataProvider = "getData")
	public static void addCustomer(String firstName, String lastName, String postCode, String successMessage) throws InterruptedException {

		driver.findElement(By.cssSelector(getDataFromPropFile("BankManagerLogin"))).click();
		log.debug("Logged in as Bank Manager");
		Reporter.log("Logged in as Bank Manager is successful");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(getDataFromPropFile("AddCustomerButton"))).click();
		Thread.sleep(3000);
		Reporter.log("Navigated to Add Customer Page");
		driver.findElement(By.cssSelector(getDataFromPropFile("AC_FirstName"))).sendKeys(firstName);
		driver.findElement(By.cssSelector(getDataFromPropFile("AC_LastName"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(getDataFromPropFile("AC_PostCode"))).sendKeys(postCode);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(getDataFromPropFile("AC_AddButton"))).click();
		Alert alertObj = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alertObj.getText().contains(successMessage));
		alertObj.accept();
		Thread.sleep(3000);
		Reporter.log("Add Customer is successful");
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
