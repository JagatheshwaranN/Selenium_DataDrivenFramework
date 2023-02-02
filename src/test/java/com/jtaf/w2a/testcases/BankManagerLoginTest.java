package com.jtaf.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.jtaf.w2a.common.ReusableComponent;

public class BankManagerLoginTest extends ReusableComponent {

	@Test
	public void LoginAsManager() throws InterruptedException {

		driver.findElement(By.cssSelector(getDataFromPropFile("BankManagerLogin"))).click();
		log.debug("Logged in as Bank Manager");
		Thread.sleep(5000);
		Assert.assertTrue(isElementPresent(By.cssSelector(getDataFromPropFile("AddCustomerButton"))));
		log.debug("Add Customer Button is available");
		Reporter.log("Logged in as Bank Manager is successful");
	}
}
