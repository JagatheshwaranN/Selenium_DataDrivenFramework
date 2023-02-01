package com.jtaf.w2a.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.jtaf.w2a.utils.FileReader;

public class TestBase {

	/**
	 * WebDriver Properites Logs ExtentReports DB Excel Mail
	 * 
	 */

	public static WebDriver driver;

	@BeforeSuite
	public static void setUp() {

		FileReader.loadPropertyFiles();
		if (FileReader.getDataFromPropFile("browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (FileReader.getDataFromPropFile("browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (FileReader.getDataFromPropFile("browser").equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofMillis(Integer.parseInt(FileReader.getDataFromPropFile("implicit.wait"))));
		driver.get(FileReader.getDataFromPropFile("url"));
	}

	@AfterSuite
	public static void setDown() {

		if (driver != null) {
			driver.quit();
		}
	}

	public static void main(String[] args) {
		setUp();
		setDown();
	}
}
