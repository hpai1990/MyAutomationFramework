package com.poc.ui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.poc.utils.WebDriverTasks;

public class MenuPageObjects {
	public static String MenuList_locator = "//span[contains(text(),'Menu')]";
	
	public static String Vadonaut_locator = "//*[contains(text(),'VADONUT')]";
	//public static String UTHAPPIZZA_locator = "//*[contains(text(),'UTHAPPIZZA')]";
	public static String UTHAPPIZZA_locator = "//img[@alt='Uthappizza']";
	public static String ZUCCHIPAKODA_locator = "//*[contains(text(),'ZUCCHIPAKODA')]";
	public static String ELAICHEESE_CAKE_locator = "//[contains(text(),'ELAICHEESE CAKE')]";
	
	
	//public static String Comments_locator = "//*[contains(text(),'Comments')]";
	
	public static String StarCount_locator = "//*[contains(text(),'Ultimate')]/../..//p[1]";
	public static String BackButton_locator = "//button//span[contains(text(),'BACK')]";
		
	
	public static synchronized  WebElement getMenuList() {
        
        return WebDriverTasks.getWebdriverSession().findElement(By.xpath(MenuList_locator));
    }

	public static synchronized  WebElement getVodunutLink() {
    
		return WebDriverTasks.getWebdriverSession().findElement(By.xpath(Vadonaut_locator));
	}
	
	public static synchronized  WebElement getUthappizzaLink() {
		return WebDriverTasks.getWebdriverSession().findElement(By.xpath(UTHAPPIZZA_locator));
	}

	public static synchronized  WebElement getZucchipakodaLink() {
		return WebDriverTasks.getWebdriverSession().findElement(By.xpath(ZUCCHIPAKODA_locator));
	}
	
	public static synchronized  WebElement getElaicheeseLink() {
		return WebDriverTasks.getWebdriverSession().findElement(By.xpath(ELAICHEESE_CAKE_locator));
	}

	public static synchronized  WebElement getStarCount() {
    
		return WebDriverTasks.getWebdriverSession().findElement(By.xpath(StarCount_locator));
	}
	
	public static synchronized  WebElement getBackButton() {
	    
		return WebDriverTasks.getWebdriverSession().findElement(By.xpath(BackButton_locator));
	}
	
//	public static synchronized  WebElement getComments() {
//  
//		return WebDriverTasks.getWebdriverSession().findElement(By.xpath(Comments_locator));
//	}

}
