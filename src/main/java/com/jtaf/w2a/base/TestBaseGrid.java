package com.jtaf.w2a.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.jtaf.w2a.utils.ExcelReaderUtil;
import com.jtaf.w2a.utils.ExtentReportUtil;
import com.jtaf.w2a.utils.FileReaderUtil;

public class TestBaseGrid extends FileReaderUtil {

	public static WebDriver driver;
	public static DesiredCapabilities capabilities = new DesiredCapabilities();
	public static Logger log = Logger.getLogger("devpinoyLogger");

	public static ExcelReaderUtil excelReaderUtil = new ExcelReaderUtil(
			System.getProperty("user.dir") + "/src/test/resources/excel/testData.xlsx");
	public static WebDriverWait wait;
	public static ExtentReports report = ExtentReportUtil.getInstance();
	public static ExtentTest test;
	public static String browser;

	@Parameters({ "browser" })
	@BeforeTest
	public static void setUp(String browser) throws MalformedURLException {

		FileReaderUtil.loadPropertyFiles();
//		if (System.getenv("Browser") != null && !System.getenv("Browser").isEmpty()) {
//			browser = System.getenv("Browser");
//		} else {
//			browser = getDataFromPropFile("Browser");
//		}
//		properties.setProperty("Browser", browser);
//		if (getDataFromPropFile("Browser").equalsIgnoreCase("Chrome")) {
//			capabilities.setPlatform(Platform.ANY);
//			capabilities.setBrowserName(browser);
//			ChromeOptions options = new ChromeOptions();
//			options.merge(capabilities);
//		} else if (getDataFromPropFile("Browser").equalsIgnoreCase("Firefox")) {
//			capabilities.setPlatform(Platform.ANY);
//			capabilities.setBrowserName(browser);
//			FirefoxOptions options = new FirefoxOptions();
//			options.merge(capabilities);
//		} else if (getDataFromPropFile("Browser").equalsIgnoreCase("Edge")) {
//			capabilities.setPlatform(Platform.ANY);
//			capabilities.setBrowserName("MicrosoftEdge");
//			EdgeOptions options = new EdgeOptions();
//			options.merge(capabilities);
//		}

		if (browser.equalsIgnoreCase("chrome")) {
			capabilities.setPlatform(Platform.ANY);
			capabilities.setBrowserName(browser);
			ChromeOptions options = new ChromeOptions();
			options.merge(capabilities);
		} else if (browser.equalsIgnoreCase("firefox")) {
			capabilities.setPlatform(Platform.ANY);
			capabilities.setBrowserName(browser);
			FirefoxOptions options = new FirefoxOptions();
			options.merge(capabilities);
		} else if (browser.equalsIgnoreCase("edge")) {
			capabilities.setPlatform(Platform.ANY);
			capabilities.setBrowserName("MicrosoftEdge");
			EdgeOptions options = new EdgeOptions();
			options.merge(capabilities);
		}
		driver = new RemoteWebDriver(new URL("http://192.168.1.5:4444/"), capabilities);
		//driver = new RemoteWebDriver(new URL("http://172.24.128.1:5555"), capabilities);
		driver.manage().window().maximize();
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofMillis(Integer.parseInt(getDataFromPropFile("implicit.wait"))));
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get(getDataFromPropFile("url"));
		log.debug("Driver launches the application " + getDataFromPropFile("url"));
	}

	@AfterTest
	public static void setDown() {

		if (driver != null) {
			driver.quit();
			log.debug("Driver session completed");
		}
	}
}
