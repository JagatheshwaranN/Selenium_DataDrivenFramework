package com.jtaf.w2a.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.jtaf.w2a.common.ReusableComponent;
import com.jtaf.w2a.utils.TestUtil;

public class AddCustomerTest extends ReusableComponent {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dataFetch")
	public static void addCustomerFlow(String firstName, String lastName, String postCode, String successMessage)
			throws InterruptedException {

		elementClick("BankManagerLogin");
		log.debug("Logged in as Bank Manager");
		Reporter.log("Logged in as Bank Manager is successful");
		Thread.sleep(3000);
		elementClick("AddCustomerButton");
		Thread.sleep(3000);
		Reporter.log("Navigated to Add Customer page");
		elementType("AC_FirstName", firstName);
		elementType("AC_LastName", lastName);
		elementType("AC_PostCode", postCode);
		Thread.sleep(3000);
		elementClick("AC_AddButton");
		Alert alertObj = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alertObj.getText().contains(successMessage));
		alertObj.accept();
		Thread.sleep(3000);
		Reporter.log("Add Customer is successful");
	}
}
