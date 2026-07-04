package com.example.playwright.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestNGListener implements ITestListener {
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	//@BeforeTest
	public void onStart(ITestContext context) {
		try {
			/*     ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("target/extent-report/extent.html");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Playwright TestNG Extent Report");
        htmlReporter.config().setReportName("Playwright Automation Results");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
			 */
			ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
			ExtentReports extent = new ExtentReports();
			extent.attachReporter(spark);

			/*   ExtentTest test = extent.createTest("My Test");
        test.pass("Test passed");
			 */
		} catch (Exception e) {
			System.out.println("Report setup failed: " + e.getMessage());
		}


	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip(result.getThrowable());
	}

	@Override
	//@AfterTest
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
