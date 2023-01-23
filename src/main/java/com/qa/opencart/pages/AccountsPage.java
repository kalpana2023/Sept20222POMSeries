package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By search=By.name("search");
	private By searchIcon=By.cssSelector("div#search button");
	private By logoutLink=By.linkText("Logout");
	private By accSecHeaders=By.cssSelector("div#content h2");

	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getAccPageTitle() {
		return eleUtil.waitForTitleIs(AppConstants.ACC_PAGE_TITLE, TimeUtil.DEFAULT_TIME_OUT);
	}
	public String getAccPageURL() {
		return eleUtil.waitForUrlContains(AppConstants.ACC_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME_OUT);
	}
	public boolean isSearchExist() {
	return eleUtil.waitForElementVisible(search, TimeUtil.DEFAULT_TIME_OUT).isDisplayed()	;
	 //return driver.findElement(search).isDisplayed();
	 
	}
	
	public boolean isLogoutBtnExist() {
		return eleUtil.waitForElementVisible(logoutLink, TimeUtil.DEFAULT_TIME_OUT).isDisplayed();
	}
	
	public boolean isSearchIconExist() {
		return eleUtil.waitForElementVisible(searchIcon, TimeUtil.DEFAULT_TIME_OUT).isDisplayed();
	}
	
	public List<String> getAccPageHeaders(){
		
		List<WebElement>accHeadersList=eleUtil.waitForElementsVisible(accSecHeaders, TimeUtil.DEFAULT_TIME_OUT);
		List<String>accSecHeadersList=new ArrayList<String>();
		for(WebElement e:accHeadersList) {
			String text=e.getText();
		  accSecHeadersList.add(text);
			
	}
		
		  return accSecHeadersList;
		
		
	}

	public ResultsPage performSearch(String productName) {
		System.out.println("product  search is="+productName);
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, productName);
			eleUtil.doClick(searchIcon);
			return  new ResultsPage(driver);
		}
		return null;
		
	}

	 
}
