package com.poc.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Attachment;

public class CustomDataLogger {
	
	@Attachment(value = "{0}", type = "plain/text")
	public static String logCustomData(String messageheader,String message){
		return message;
	}
	
	
	@Attachment(value = "Capturing screenshot for current step" , type = "image/png")
	public static byte[] saveScreenshotPNG (WebDriver driver){
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}

}
