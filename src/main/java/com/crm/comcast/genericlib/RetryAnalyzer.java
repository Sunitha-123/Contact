package com.crm.comcast.genericlib;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{

	int counter=0;
	int retryCount=4;
	public boolean retry(ITestResult result) {
		while(counter<retryCount) {
			counter++;
			return true;
		}
		return false;
	}

}
