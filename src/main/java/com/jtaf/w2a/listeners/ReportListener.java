package com.jtaf.w2a.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.jtaf.w2a.common.ReusableComponent;

public class ReportListener extends ReusableComponent implements ITestListener {

	public void onTestStart(ITestResult result) {
		// not implemented
	}

	public void onTestSuccess(ITestResult result) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("<br>");
		Reporter.log(result.getMethod().getMethodName() + " Test Passed..!!");
		Reporter.log("<br>");
		Reporter.log("<a target='_blank' href='" + captureSnapShot() + "'><img src='" + captureSnapShot()
				+ "' height='100' width='100'/></a>");
	}

	public void onTestFailure(ITestResult result) {
		// not implemented
	}

	public void onTestSkipped(ITestResult result) {
		// not implemented
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	public void onStart(ITestContext context) {
		// not implemented
	}

	public void onFinish(ITestContext context) {
		// not implemented
	}

}
