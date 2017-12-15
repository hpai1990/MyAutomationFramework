package com.poc.ui.test;

import java.net.MalformedURLException;

import org.testng.annotations.Test;

import com.poc.ui.pagetasks.JobListingPageTasks;
import com.poc.ui.pagetasks.MenuPageTasks;
import com.poc.utils.BaseTest;
import com.poc.utils.CustomDataLogger;
import com.poc.utils.WebDriverTasks;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("POC Test Framework")
@Feature("App feature test")

public class AngularAppTests extends BaseTest {
	//@Parameters ( {"browser"})
	@Test(groups = {"bvt","regression"} , description = "Validate Edit User Record operation" , priority=0)
	@Story("Angular App Test")
	public void TestUI001() throws MalformedURLException{
		
		//WebDriverTasks.setUp(browser);
		WebDriverTasks.loadURL("http://10.51.236.53:9000/joblisting");
		JobListingPageTasks.UpdateUserFirstName("Dario", "10");
		CustomDataLogger.saveScreenshotPNG(WebDriverTasks.getWebdriverSession());
		WebDriverTasks.tearDown();
	}
	
	@Test(groups = {"bvt","regression"} , description = "Get star counts of Menu" , priority=1)
	@Story("Angular App Test")
	public void TestUI002() throws MalformedURLException{
		
		//WebDriverTasks.setUp(browser);
		WebDriverTasks.loadURL("http://10.51.236.53:9000/");
		MenuPageTasks.GetNoOfStars("UTHAPPIZZA");
		WebDriverTasks.tearDown();
	}
	
	@Test(groups = {"bvt","regression"} , description = "Get star counts of Menu: fail Case" , priority=2)
	@Story("Angular App Test")
	public void TestUI003() throws MalformedURLException{
		
		//WebDriverTasks.setUp(browser);
		WebDriverTasks.loadURL("http://10.51.236.53:9000/");
		MenuPageTasks.GetNoOfStars("ZUCCHIPAKODA");
		WebDriverTasks.tearDown();
	}

}