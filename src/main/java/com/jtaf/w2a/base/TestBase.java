package com.jtaf.w2a.base;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.jtaf.w2a.utils.ExcelReaderUtil;
import com.jtaf.w2a.utils.ExtentReportUtil;
import com.jtaf.w2a.utils.FileReaderUtil;

public class TestBase extends FileReaderUtil {

	/**
	 * WebDriver Properties Logs ExtentReports DB Excel Mail
	 * 
	 */

	public static WebDriver driver;

	public static Logger log = Logger.getLogger("devpinoyLogger");

	public static ExcelReaderUtil excelReaderUtil = new ExcelReaderUtil(
			System.getProperty("user.dir") + "/src/test/resources/excel/testData.xlsx");
	public static WebDriverWait wait;
	public static ExtentReports report = ExtentReportUtil.getInstance();
	public static ExtentTest test;
	public static String browser;

	@BeforeSuite
	public static void setUp() {

		FileReaderUtil.loadPropertyFiles();
		if (System.getenv("Browser") != null && !System.getenv("Browser").isEmpty()) {
			browser = System.getenv("Browser");
		} else {
			browser = getDataFromPropFile("Browser");
		}
		properties.setProperty("Browser", browser);
		if (getDataFromPropFile("Browser").equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
			log.debug(getDataFromPropFile("Browser") + " driver started");
		} else if (getDataFromPropFile("Browser").equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
			log.debug(getDataFromPropFile("Browser") + " driver started");
		} else if (getDataFromPropFile("Browser").equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
			log.debug(getDataFromPropFile("Browser") + " driver started");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofMillis(Integer.parseInt(getDataFromPropFile("implicit.wait"))));
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get(getDataFromPropFile("url"));
		log.debug("Driver launches the application " + getDataFromPropFile("url"));
	}

	@AfterSuite
	public static void setDown() {

		if (driver != null) {
			driver.quit();
			log.debug("Driver session completed");
		}
	}

	public WebDriver getDriver() {
		return driver;
	}
}
