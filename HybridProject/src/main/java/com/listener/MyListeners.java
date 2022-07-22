package com.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.utility.DriverUtils;

public class MyListeners extends BaseClass implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {

		test = report.createTest(result.getName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test.log(Status.PASS, "Testcase passed with name" + result.getName());

	}

	@Override
	public void onTestFailure(ITestResult result) {

		String path = null;
		try {
			path = DriverUtils.getScreenshot(result.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		test.log(Status.FAIL, "Testcase failed with name" + result.getName());

		test.addScreenCaptureFromPath(path);

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		test.log(Status.SKIP, "Testcase skipped with name" + result.getName());

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

		log.info("test suit is ready for execution");

	}

	@Override
	public void onFinish(ITestContext context) {

		log.info("test suite is successfully executed");
		report.flush();

	}

}
