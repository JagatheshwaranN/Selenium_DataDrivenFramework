package com.jtaf.w2a.base;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;

import com.jtaf.w2a.utils.ExcelReader;
import com.jtaf.w2a.utils.FileReader;

public class TestBase extends FileReader {

	/**
	 * WebDriver Properites Logs ExtentReports DB Excel Mail
	 * 
	 */

	public static WebDriver driver;

	public static Logger log = Logger.getLogger("devpinoyLogger");

	public static ExcelReader excelReader = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/resources/excel/testData.xlsx");
	public static WebDriverWait wait;

	@BeforeTest
	public static void setUp() {

		FileReader.loadPropertyFiles();
		if (getDataFromPropFile("browser").equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
			log.debug(getDataFromPropFile("browser") + " driver started");
		} else if (getDataFromPropFile("browser").equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
			log.debug(getDataFromPropFile("browser") + " driver started");
		} else if (getDataFromPropFile("browser").equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
			log.debug(getDataFromPropFile("browser") + " driver started");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofMillis(Integer.parseInt(getDataFromPropFile("implicit.wait"))));
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get(getDataFromPropFile("url"));
		log.debug("Driver lauches the application " + getDataFromPropFile("url"));
	}

	@AfterTest
	public static void setDown() {

		if (driver != null) {
			driver.quit();
			log.debug("Driver session completed");
		}
	}

	public static void main(String[] args) {
		setUp();
		setDown();
	}
}
