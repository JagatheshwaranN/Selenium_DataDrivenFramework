package com.jtaf.w2a.listeners;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.jtaf.w2a.base.TestBase;
import com.jtaf.w2a.common.ReusableComponent;
import com.jtaf.w2a.utils.EmailConfig;
import com.jtaf.w2a.utils.EmailTriggerUtil;

public class ReportListener extends ReusableComponent implements ITestListener, ISuiteListener {

	public static String messageBody;

	public void onTestStart(ITestResult result) {

		test.log(Status.INFO, result.getName().toUpperCase() + " Test Started");
	}

	public void onTestSuccess(ITestResult result) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		String passTestCaseBase64Snapshot = "data:image/png;base64,"
				+ ((TakesScreenshot) Objects.requireNonNull(TestBase.driver)).getScreenshotAs(OutputType.BASE64);
		test.log(Status.PASS, result.getName().toUpperCase() + " Test Passed",
				test.addScreenCaptureFromBase64String(passTestCaseBase64Snapshot).getModel().getMedia().get(0));
		String snaptoAttach = captureSnapShot();
		Reporter.log("<br>");
		Reporter.log(result.getMethod().getMethodName() + " Test Passed..!!");
		Reporter.log("<br>");
		Reporter.log("<a target='_blank' href='" + snaptoAttach + "'><img src='" + snaptoAttach
				+ "' height='100' width='100'/></a>");
	}

	public void onTestFailure(ITestResult result) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		// Object testcase = result.getInstance();
		// WebDriver driver = ((TestBase) testcase).getDriver();
		// WebDriver driver = TestBase.driver;
		String failTestCaseBase64Snapshot = "data:image/png;base64,"
				+ ((TakesScreenshot) Objects.requireNonNull(TestBase.driver)).getScreenshotAs(OutputType.BASE64);
		test.log(Status.FAIL, result.getName().toUpperCase() + " Test Failed",
				test.addScreenCaptureFromBase64String(failTestCaseBase64Snapshot).getModel().getMedia().get(0));
		String snaptoAttach = captureSnapShot();
		Reporter.log("<br>");
		Reporter.log(result.getMethod().getMethodName() + " Test Failed..!!");
		Reporter.log("<br>");
		Reporter.log("<a target='_blank' href='" + snaptoAttach + "'><img src='" + snaptoAttach
				+ "' height='100' width='100'/></a>");
	}

	public void onTestSkipped(ITestResult result) {

		test.log(Status.SKIP, result.getName().toUpperCase() + " Test Skipped. As the RunMode is set to N");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	public void onStart(ITestContext context) {

		test = report.createTest(context.getName());
	}

	public void onFinish(ITestContext context) {

		report.flush();
	}

	public void onStart(ISuite suite) {
		// not implemented
	}

	public void onFinish(ISuite suite) {

//		EmailTriggerUtil emailTriggerUtil = new EmailTriggerUtil();
		try {
			String[] reportPath = getDataFromPropFile("jenkinsAutomationReportPath").split("\\$");
			messageBody = reportPath[0] + InetAddress.getLocalHost().getHostAddress() + reportPath[1];
//			emailTriggerUtil.sendEmail(EmailConfig.mailServer, EmailConfig.from, EmailConfig.to, EmailConfig.subject,
//					messageBody);
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
	}
}
