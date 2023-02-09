package com.jtaf.w2a.listeners;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.jtaf.w2a.common.ReusableComponent;
import com.jtaf.w2a.utils.EmailConfig;
import com.jtaf.w2a.utils.EmailTriggerUtil;

public class ReportListener extends ReusableComponent implements ITestListener, ISuiteListener {

	public static String messageBody;

	public void onTestStart(ITestResult result) {

		test.log(Status.INFO, result.getName().toUpperCase() + " STARTED");
	}

	public void onTestSuccess(ITestResult result) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		test.log(Status.PASS, result.getName().toUpperCase() + " PASS");
		test.addScreenCaptureFromPath(captureSnapShot());
		Reporter.log("<br>");
		Reporter.log(result.getMethod().getMethodName() + " Test Passed..!!");
		Reporter.log("<br>");
		Reporter.log("<a target='_blank' href='" + captureSnapShot() + "'><img src='" + captureSnapShot()
				+ "' height='100' width='100'/></a>");
	}

	public void onTestFailure(ITestResult result) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		test.log(Status.FAIL, result.getName().toUpperCase() + " FAIL" + "\n" + result.getThrowable());
		test.addScreenCaptureFromPath(captureSnapShot());
		Reporter.log("<br>");
		Reporter.log(result.getMethod().getMethodName() + " Test Passed..!!");
		Reporter.log("<br>");
		Reporter.log("<a target='_blank' href='" + captureSnapShot() + "'><img src='" + captureSnapShot()
				+ "' height='100' width='100'/></a>");
	}

	public void onTestSkipped(ITestResult result) {

		test.log(Status.SKIP, result.getName().toUpperCase() + " SKIPPED. As the RunMode is set to N");
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

		EmailTriggerUtil emailTriggerUtil = new EmailTriggerUtil();
		try {
			String[] reportPath = getDataFromPropFile("jenkinsAutomationReportPath").split("\\$");
			messageBody = reportPath[0] + InetAddress.getLocalHost().getHostAddress() + reportPath[1];
			emailTriggerUtil.sendEmail(EmailConfig.mailServer, EmailConfig.from, EmailConfig.to, EmailConfig.subject,
					messageBody);
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
	}
}
