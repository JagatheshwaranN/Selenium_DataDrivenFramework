package com.jtaf.w2a.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.jtaf.w2a.common.ReusableComponent;
import com.jtaf.w2a.utils.TestUtil;

public class OpenAccountTest extends ReusableComponent {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dataFetch")
	public static void openAccountFlow(String customerName, String currencyType, String successMessage)
			throws InterruptedException {

		elementClick("BankManagerLogin");
		log.debug("Logged in as Bank Manager");
		Reporter.log("Logged in as Bank Manager is successful");
		Thread.sleep(3000);
		elementClick("OpenAccountButton");
		Thread.sleep(3000);
		Reporter.log("Navigated to Open Account page");
		elementSelect("OA_Customer", "OA_Customer_Options", customerName);
		elementSelect("OA_Currency", currencyType);
		Thread.sleep(3000);
		elementClick("OA_Process");
		Alert alertObj = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alertObj.getText().contains(successMessage));
		alertObj.accept();
		Thread.sleep(3000);
		Reporter.log("Open Account is successful");
	}
}
