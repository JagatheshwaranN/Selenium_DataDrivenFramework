package com.jtaf.w2a.testcases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;
import com.jtaf.w2a.common.ReusableComponentGrid;
import com.jtaf.w2a.utils.TestUtilGrid;

public class BankManagerLoginTestGrid extends ReusableComponentGrid {

	@Test
	public void bankManagerLoginTestGrid() throws InterruptedException {

		Class<BankManagerLoginTestGrid> classObj = BankManagerLoginTestGrid.class;
		Method[] methods = classObj.getMethods();
		if (!TestUtilGrid.isTestRunnable(methods[0].getName(), excelReaderUtil)) {
			throw new SkipException("Skipping the Test " + methods[0].getName() + " as the RunMode is N");
		}
		elementClick("BankManagerLogin");
		log.debug("Logged in as Bank Manager");
		Thread.sleep(5000);
		Assert.assertTrue(isElementPresent("AddCustomerButton"));
		log.debug("Add Customer Button is available");
		Reporter.log("Logged in as Bank Manager is successful");
	}
}
