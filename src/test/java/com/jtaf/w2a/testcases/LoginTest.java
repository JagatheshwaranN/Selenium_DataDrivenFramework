package com.jtaf.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.jtaf.w2a.base.TestBase;
import com.jtaf.w2a.utils.FileReader;

public class LoginTest extends TestBase {

	@Test
	public void LoginAsManager() {

		driver.findElement(By.cssSelector(FileReader.getDataFromPropFile("BankManagerLogin"))).click();
	}
}
