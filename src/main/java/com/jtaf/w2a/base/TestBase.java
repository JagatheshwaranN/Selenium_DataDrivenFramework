package com.jtaf.w2a.base;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.jtaf.w2a.utils.FileReader;

public class TestBase extends FileReader {

	/**
	 * WebDriver Properites Logs ExtentReports DB Excel Mail
	 * 
	 */

	public static WebDriver driver;

	public static Logger log = Logger.getLogger("devpinoyLogger");
	
	@BeforeSuite
	public static void setUp() {

		FileReader.loadPropertyFiles();
		if (getDataFromPropFile("browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			log.debug(getDataFromPropFile("browser") + " driver started");
		} else if (getDataFromPropFile("browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			log.debug(getDataFromPropFile("browser") + " driver started");
		} else if (getDataFromPropFile("browser").equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			log.debug(getDataFromPropFile("browser") + " driver started");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofMillis(Integer.parseInt(getDataFromPropFile("implicit.wait"))));
		driver.get(getDataFromPropFile("url"));
		log.debug("Driver lauches the application " + getDataFromPropFile("url"));
	}

	@AfterSuite
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
