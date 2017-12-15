package com.poc.utils;


import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public class CustomErrorReporter implements IHookable {

	public void run(IHookCallBack callback, ITestResult testresult) {
		
		callback.runTestMethod(testresult);
        if ((testresult.getThrowable() != null) && WebDriverTasks.VerifyWebdriverSession()) {
            try {
                takeScreenShot();
                WebDriverTasks.tearDown();
    			
            } catch (Exception e) {
                e.printStackTrace();
            }
        }   
		
	}
	
	@Attachment(value = "Test failure Screenshot", type = "image/png")
    private byte[] takeScreenShot(){
    	return ((TakesScreenshot)WebDriverTasks.getWebdriverSession()).getScreenshotAs(OutputType.BYTES);
    }
	
	@Attachment(value = "Test Step Screenshot", type = "image/png")
    private byte[] takeStepScreenShot(){
    	return ((TakesScreenshot)WebDriverTasks.getWebdriverSession()).getScreenshotAs(OutputType.BYTES);
    }

}
