package com.jtaf.w2a.utils;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtil {

	public static ExtentSparkReporter extentSparkReporter;
	public static ExtentReports extentReports;

	public static ExtentReports getInstance() {
		if (extentSparkReporter == null) {
			extentSparkReporter = new ExtentSparkReporter(
					System.getProperty("user.dir") + "//report//ExtentSpark.html");
			try {
				extentSparkReporter.loadXMLConfig(
						new File(System.getProperty("user.dir") + "//src//test//resources//extent//report-config.xml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			extentReports = new ExtentReports();
			extentReports.attachReporter(extentSparkReporter);
		}
		return extentReports;
	}

}
