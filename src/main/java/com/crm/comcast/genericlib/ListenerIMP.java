package com.crm.comcast.genericlib;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerIMP implements ITestListener{
	public ExtentReports report;
	public ExtentTest test;
	    public void onFinish(ITestContext arg0) {					
	        	report.flush();	
	    }		

	    		
	    public void onStart(ITestContext arg0) {					
	    	ExtentSparkReporter htmlReporter=new ExtentSparkReporter("./ExtentReport.html");
	    	htmlReporter.config().setTheme(Theme.DARK);
	    	htmlReporter.config().setDocumentTitle("VTiger App");
	    	htmlReporter.config().setReportName("Full regression test");
	    	report = new ExtentReports();
	    	report.attachReporter(htmlReporter);
	    	report.setSystemInfo("Platform", "Windows");
	    	report.setSystemInfo("OS", "Windows 10 Pro");
	    	report.setSystemInfo("Environment", "Testing Environment");
	    	report.setSystemInfo("Reporter", "Nithesh");
	        		
	    }		

	   		
	    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
	        // TODO Auto-generated method stub				
	        		
	    }		

	    		
	    public void onTestFailure(ITestResult result) {					
	    	test.log(Status.FAIL, result.getMethod().getMethodName()+" is failed");
	    	test.log(Status.FAIL, result.getThrowable());
	    	WebDriverUtiles wUtiles=new WebDriverUtiles();
	    	try {
			String	path=wUtiles.takeScreenshot(BaseClass.staticDriver, result.getMethod().getMethodName());
			test.addScreenCaptureFromPath(path);
	    	} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	        		
	    }		

	    		
	    public void onTestSkipped(ITestResult result) {					
	        test.log(Status.SKIP, result.getMethod().getMethodName()+" is skipped");
	        test.log(Status.SKIP,result.getThrowable());
	    }		

	    		
	    public void onTestStart(ITestResult result) {					
	        test=report.createTest(result.getMethod().getMethodName());		
	    }		

	    		
	    public void onTestSuccess(ITestResult result) {					
	        test.log(Status.PASS, result.getMethod().getMethodName()+" is passed");		
	    }		

}
