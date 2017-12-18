package com.poc.ui.pagetasks;

import io.qameta.allure.Step;

import com.poc.ui.pageobjects.MenuPageObjects;
import com.poc.utils.CustomDataLogger;
import com.poc.utils.WebDriverTasks;



public class MenuPageTasks {
	//public static WebDriverWait wait = new WebDriverWait(WebDriverTasks.getWebdriverSession(),10);
	
	@Step(value="Clicking on Menu")
	public static synchronized void clickMenuPage(){
		//WebDriverWait wait = new WebDriverWait(WebDriverTasks.getWebdriverSession(),10);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MenuPageObjects.MenuList_locator)));
		WebDriverTasks.clickObject(MenuPageObjects.MenuList_locator);
		CustomDataLogger.saveScreenshotPNG(WebDriverTasks.getWebdriverSession());
	}
	
	@Step(value="Clicking on Item")
	public static synchronized void ClickItem(String menuItem){
		//WebDriverWait wait = new WebDriverWait(WebDriverTasks.getWebdriverSession(),10);
		
		switch(menuItem) {
		
		case "VADONUT":
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MenuPageObjects.Vadonaut_locator)));
			WebDriverTasks.clickObject(MenuPageObjects.Vadonaut_locator);
			break;

		case "UTHAPPIZZA":
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MenuPageObjects.UTHAPPIZZA_locator)));
			WebDriverTasks.clickObject(MenuPageObjects.UTHAPPIZZA_locator);
			break;

		case "ZUCCHIPAKODA":
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MenuPageObjects.ZUCCHIPAKODA_locator)));
			WebDriverTasks.clickObject(MenuPageObjects.ZUCCHIPAKODA_locator);
			break;

		case "ELAICHEESE_CAKE":
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MenuPageObjects.ELAICHEESE_CAKE_locator)));
			WebDriverTasks.clickObject(MenuPageObjects.ELAICHEESE_CAKE_locator);
			break;
		}
		
		CustomDataLogger.saveScreenshotPNG(WebDriverTasks.getWebdriverSession());
	}

	public static synchronized void GetStarCount(){
		//WebDriverWait wait = new WebDriverWait(WebDriverTasks.getWebdriverSession(),10);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MenuPageObjects.StarCount_locator)));
		WebDriverTasks.getTextOfObject(MenuPageObjects.StarCount_locator);
	}

	public static  void GetNoOfStars(String menuItem){
		//WebDriverWait wait = new WebDriverWait(WebDriverTasks.getWebdriverSession(),10);
		//wait.until(ExpectedConditions.elementToBeClickable(JobListingPageObjects.getTableUserIDTextBox()));
		//WebDriverTasks.waitForElementToBeClickable(JobListingPageObjects.getTableUserIDTextBox());
		clickMenuPage();
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(JobListingPageObjects.editlink_locator)));
		//wait.until(ExpectedConditions.elementToBeClickable(JobListingPageObjects.getEditLink()));
		//WebDriverTasks.waitForElementToBeClickable(JobListingPageObjects.getEditLink());
		ClickItem(menuItem);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(JobListingPageObjects.rowusername_locator)));
		//wait.until(ExpectedConditions.elementToBeClickable(JobListingPageObjects.getRecordUserNameTextBox()));
		//WebDriverTasks.waitForElementToBeClickable(JobListingPageObjects.getRecordUserNameTextBox());
		GetStarCount();
		//wait.until(ExpectedConditions.elementToBeClickable(JobListingPageObjects.getUpdateLink()));
		//WebDriverTasks.waitForElementToBeClickable(JobListingPageObjects.getUpdateLink());
		WebDriverTasks.clickObject(MenuPageObjects.BackButton_locator);

	}
}
