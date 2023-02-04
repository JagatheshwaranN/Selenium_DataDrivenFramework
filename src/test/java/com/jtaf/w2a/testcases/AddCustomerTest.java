package com.jtaf.w2a.testcases;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.jtaf.w2a.common.ReusableComponent;
import com.jtaf.w2a.utils.TestUtil;

public class AddCustomerTest extends ReusableComponent {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dataFetch")
	public static void addCustomerTest(Hashtable<String, String> data) throws InterruptedException {

		Class<AddCustomerTest> classObj = AddCustomerTest.class;
		Method[] methods = classObj.getMethods();
		if (!TestUtil.isTestRunnable(methods[0].getName(), excelReader)) {
			throw new SkipException("Skipping the Test " + methods[0].getName() + " as the RunMode is set to N");
		}
		if (!data.get("RunMode").equalsIgnoreCase("Y")) {
			throw new SkipException("Skipping the Test Case as the RunMode for the Data is set to N");
		}
//		elementClick("BankManagerLogin");
//		log.debug("Logged in as Bank Manager");
//		Reporter.log("Logged in as Bank Manager is successful");
//		Thread.sleep(3000);
		elementClick("AddCustomerButton");
		Thread.sleep(3000);
		Reporter.log("Navigated to Add Customer page");
		elementType("AC_FirstName", data.get("FirstName"));
		elementType("AC_LastName", data.get("LastName"));
		elementType("AC_PostCode", data.get("PostCode"));
		Thread.sleep(3000);
		elementClick("AC_AddButton");
		Alert alertObj = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alertObj.getText().contains(data.get("SuccessMessage")));
		alertObj.accept();
		Thread.sleep(3000);
		Reporter.log("Add Customer is successful");
	}
}
