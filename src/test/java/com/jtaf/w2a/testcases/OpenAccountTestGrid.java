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

public class OpenAccountTestGrid extends ReusableComponent {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dataFetch")
	public static void openAccountTest(Hashtable<String, String> data) throws InterruptedException {

		Class<OpenAccountTestGrid> classObj = OpenAccountTestGrid.class;
		Method[] methods = classObj.getMethods();
		if (!TestUtil.isTestRunnable(methods[0].getName(), excelReaderUtil)) {
			throw new SkipException("Skipping the Test " + methods[0].getName() + " as the RunMode is N");
		}
		elementClick("BankManagerLogin");
		log.debug("Logged in as Bank Manager");
		Reporter.log("Logged in as Bank Manager is successful");
		Thread.sleep(3000);
		elementClick("OpenAccountButton");
		Thread.sleep(3000);
		Reporter.log("Navigated to Open Account page");
		elementSelect("OA_Customer", "OA_Customer_Options", data.get("Customer"));
		elementSelect("OA_Currency", data.get("Currency"));
		Thread.sleep(3000);
		elementClick("OA_Process");
		Alert alertObj = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alertObj.getText().contains(data.get("SuccessMessage")));
		alertObj.accept();
		Thread.sleep(3000);
		Reporter.log("Open Account is successful");
	}
}
