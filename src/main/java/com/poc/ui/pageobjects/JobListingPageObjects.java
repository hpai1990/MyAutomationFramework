package com.poc.ui.pageobjects;



public class JobListingPageObjects {
	
	public static String editlink_locator = "//a[@href='#'][contains(text(),'Edit')]";
	public static String tableuserid_locator = "//input[@type='text'][@placeholder='ID']";
	public static String updatelink_locator = "//a[@href='#'][contains(text(),'Update')]";
	public static String rowusername_locator = "//input[@ng-reflect-name='name'][@placeholder='Full Name']";
	
	
	/*public static synchronized  WebElement getEditLink() {
        
        return WebDriverTasks.getWebdriverSession().findElement(By.xpath(editlink_locator));
    }

	public static synchronized  WebElement getTableUserIDTextBox() {
    
		return WebDriverTasks.getWebdriverSession().findElement(By.xpath(tableuserid_locator));
	}

	public static synchronized  WebElement getRecordUserNameTextBox() {
    
		return WebDriverTasks.getWebdriverSession().findElement(By.xpath(rowusername_locator));
	}

	public static synchronized  WebElement getUpdateLink() {
    
		return WebDriverTasks.getWebdriverSession().findElement(By.xpath(updatelink_locator));
	}*/

}
