/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2016                                          */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.poc.utils;

import io.qameta.allure.Step;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.paulhammant.ngwebdriver.NgWebDriver;

//This class provides methods with logging for different object actions like click,send text etc
public class WebDriverTasks {
	
	//Webdribver session and the curresponding thread id's are mapped in a hashmap.This config is used for parallel execution of selenium tests
	private static Map<Integer, WebDriver> driverSessionMap = new HashMap<Integer,WebDriver>();
	private static long defaultwait = 15;
	private static long longwait = 120;
	public static int maxretry = 10;
	

	//public static WebDriverWait wait;
	
	
	
	@Step("Clicking on Web Element")
	public static synchronized void clickObject(String object_locator){
		try{
			waitForAngular();
			WebDriverWait wait = new WebDriverWait(WebDriverTasks.getWebdriverSession(),10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(object_locator)));
			WebDriverTasks.getWebdriverSession().findElement(By.xpath(object_locator)).click();
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex){
			//waitForAngular();
			WebDriverWait wait = new WebDriverWait(WebDriverTasks.getWebdriverSession(),10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(object_locator)));
			WebDriverTasks.getWebdriverSession().findElement(By.xpath(object_locator)).click();
			System.out.println("Stale State Exception handled....");
		
		}
		
		
	}
	
	@Step("Sending Text in a Web Element")
	public static synchronized void sendTextWithObject(String object_locator,String webtext){
		
		try{
			waitForAngular();
			WebDriverWait wait = new WebDriverWait(WebDriverTasks.getWebdriverSession(),10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(object_locator)));
			WebDriverTasks.getWebdriverSession().findElement(By.xpath(object_locator)).sendKeys(webtext);
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex){
			//waitForAngular();
			WebDriverWait wait = new WebDriverWait(WebDriverTasks.getWebdriverSession(),10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(object_locator)));
			WebDriverTasks.getWebdriverSession().findElement(By.xpath(object_locator)).sendKeys(webtext);
		}
		
	}
	
	@Step("Loading url...")
	public static synchronized void loadURL(String url){
		getWebdriverSession().get(url);
		
	}
	
	@Step("Get Text from the WebElement")
	public static synchronized void getTextOfObject(String object_locator) {
		WebDriverWait wait = new WebDriverWait(WebDriverTasks.getWebdriverSession(),10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(object_locator)));
		WebDriverTasks.getWebdriverSession().findElement(By.xpath(object_locator)).getText();
	}
	
	public static synchronized void actionClassClick(WebElement obj){
		Actions action = new Actions(getWebdriverSession());
		action.moveToElement(obj).click().perform();
		
	}
	
	
	public static synchronized void waitForAngular(){
		NgWebDriver ngdriver;
		JavascriptExecutor js;
		
		js = (JavascriptExecutor) getWebdriverSession();
		getWebdriverSession().manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
	    ngdriver = new NgWebDriver(js);
		
	    ngdriver.waitForAngularRequestsToFinish();
		
	}
	
	public static synchronized ExpectedCondition<Boolean> angularHasFinishedProcessing() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return Boolean.valueOf(((JavascriptExecutor) driver).executeScript("return (window.angular !== undefined) && (angular.element(document).injector() !== undefined) && (angular.element(document).injector().get('$http').pendingRequests.length === 0)").toString());
            }
        };
    }
	
	public static synchronized void assertCheckpoint(Boolean expected,Boolean actual,String message){
		Assert.assertEquals(actual,expected);
		
	}
	
	public static synchronized void setUp(String browser) throws MalformedURLException{
		DesiredCapabilities capabilities ;
		/*FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.webnotifications.enabled", false);
		profile.setPreference("dom.webnotifications.serviceworker.enabled", false);
		capabilities.setCapability(FirefoxDriver.PROFILE, profile);*/
		switch (browser) {
    	case "firefox":
    		capabilities = DesiredCapabilities.firefox();
    		capabilities.setBrowserName("firefox");
    		capabilities.setPlatform(Platform.LINUX);
    		capabilities.setVersion("56.0");
    		//FirefoxProfile profile = new FirefoxProfile();
    		//profile.setPreference("dom.webnotifications.enabled", false);
    		//profile.setPreference("dom.webnotifications.serviceworker.enabled", false);
    		//capabilities.setCapability(FirefoxDriver.PROFILE, profile);
    		break;
    		
    	case "chrome":
    		capabilities = DesiredCapabilities.chrome();
    		capabilities.setBrowserName("chrome");
    		capabilities.setPlatform(Platform.LINUX);
    		capabilities.setVersion("62.0.3202.62");
    		break;
    		
    	default:
    		capabilities = DesiredCapabilities.firefox();
    		break;
    	}
		WebDriver driver = new RemoteWebDriver(new URL ("http://10.51.236.53:32773/wd/hub"),capabilities);
		//FirefoxProfile fprofile = new FirefoxProfile();
		//fprofile.setPreference("dom.webnotifications.enabled", false);
		//fprofile.setPreference("dom.webnotifications.serviceworker.enabled", false);
		
		//WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(defaultwait, TimeUnit.SECONDS); //default page object wait of 15 seconds
		driver.manage().timeouts().pageLoadTimeout(longwait, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		addWebdriverSession(driver);
		
	}
	
	
	
	public static synchronized void tearDown(/*Boolean captureScreenshot*/){
		//if(captureScreenshot)
		//	CaptureScreenshot.captureScreenshot(getWebdriverSession());
		getWebdriverSession().quit();
		removeWebdriverSession(getWebdriverSession());
	}
	
	public static synchronized void addWebdriverSession(WebDriver driver){
	    driverSessionMap.put((int) (long)(Thread.currentThread().getId()),driver);
	}
	    
	public static synchronized void removeWebdriverSession(WebDriver driver){
	    driverSessionMap.remove((int) (long)(Thread.currentThread().getId()));
	}
	    
	public static synchronized WebDriver getWebdriverSession(){
		return driverSessionMap.get((int)(long) (Thread.currentThread().getId()));
	}
	
	public static synchronized Boolean VerifyWebdriverSession(){
		return driverSessionMap.containsKey((int)(long) (Thread.currentThread().getId()));
	}
	
		
	
}
