package com.jtaf.w2a.common;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.jtaf.w2a.base.TestBase;

public class ReusableComponent extends TestBase {

	public static boolean isElementPresent(By object) {

		try {
			driver.findElement(object);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}
}
