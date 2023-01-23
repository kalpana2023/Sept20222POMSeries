package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.AppErrors;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetup()
	{
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPageTitleTest() {
		String actTitle=accPage.getAccPageTitle();
		Assert.assertEquals(actTitle,AppConstants.ACC_PAGE_TITLE);
	}
	
	@Test
	public void accPageURLTest() {
		String accURL=accPage.getAccPageURL();
	    Assert.assertTrue(accURL.contains(AppConstants.ACC_PAGE_FRACTION_URL),AppErrors.NO_URL_MATCHED);
	}
	
	@Test
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	@Test
	
	public void logoutExistTest() {
		Assert.assertTrue(accPage.isLogoutBtnExist());
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> accHeaderList=accPage.getAccPageHeaders();
		Assert.assertEquals(accHeaderList, AppConstants.EXPECTED_ACC_HEADERS_LIST);
		
	}
	@DataProvider
	
	public Object[][] getProductName() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Samsung"}
		};
	}
	@Test(dataProvider = "getProductName")
	//TDD

	public void productSearchTest(String productName) {
	
		resultsPage=accPage.performSearch(productName);
		String actTitle=resultsPage.getSearchPageTitle(productName);
		System.out.println("search results ="+actTitle);
		softAssert.assertEquals(actTitle, AppConstants.SEARCH_PAGE_TITLE+" "+productName);
		Assert.assertTrue(resultsPage.getSearchProductsCount()>0);
		
		
	}
	
	
}
