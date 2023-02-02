package com.jtaf.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.jtaf.w2a.base.TestBase;

public class LoginTest extends TestBase {

	@Test
	public void LoginAsManager() {

		driver.findElement(By.cssSelector(getDataFromPropFile("BankManagerLogin"))).click();
		log.debug("Logged in as Bank Manager");
	}
}
