package com.jtaf.w2a.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.jtaf.w2a.common.ReusableComponent;

public class ReportListener extends ReusableComponent implements ITestListener {

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

		test.log(Status.SKIP, result.getThrowable());
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

}
