package com.jtaf.w2a.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.Status;
import com.jtaf.w2a.base.TestBase;

public class ReusableComponent extends TestBase {

	public static boolean isElementPresent(String key) {

		try {
			driver.findElement(By.cssSelector(getDataFromPropFile(key)));
			test.log(Status.INFO, key + " is present on the page");
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public static void elementClick(String key) {

		if (key != null) {
			driver.findElement(By.cssSelector(getDataFromPropFile(key))).click();
			test.log(Status.INFO, "Clicked on the " + key);
		}
	}

	public static void elementType(String key, String value) {

		if (key != null) {
			driver.findElement(By.cssSelector(getDataFromPropFile(key))).sendKeys(value);
			test.log(Status.INFO, "Typed into " + key + " with value as " + value);
		}
	}

	public static String captureSnapShot() {

		Calendar calendar = Calendar.getInstance();
		// System.out.println(cal.get(Calendar.DATE)+"_"+(cal.get(Calendar.MONTH)+1)+"_"+cal.get(Calendar.YEAR));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_YYYY_hh_mm_ss");
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "/src/test/resources/screenshots/success/"
				+ simpleDateFormat.format(calendar.getTime()) + ".png");
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destination.getAbsolutePath();
	}

}
