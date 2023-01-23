package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest  extends BaseTest{
	
	@BeforeClass
	public void productInfoSetUp() {
	accPage= loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		
		
	}
	@DataProvider
	
	public Object[][]  getProductTestData(){
		return new Object[][] {
			
			
			{"MacBook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"iMac","iMac"},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Samsung","Samsung Galaxy Tab 10.1"}
			
		};
	}
	
	@Test(dataProvider = "getProductTestData")
	   public void productHeaderTest(String searchKey,String mainProductName) {
		   resultsPage=accPage.performSearch(searchKey);
		   prodInfoPage=resultsPage.selectProduct(mainProductName); 
		   String actHeader=prodInfoPage.getProductHeader();
		   System.out.println(actHeader);
		   Assert.assertEquals(actHeader,mainProductName);
		   		
	   }
	@DataProvider
	
	
	public Object[][]  getProductImagesTestData(){
		return new Object[][] {
			
			
			{"MacBook","MacBook Pro",4},
			{"Macbook","MacBook Air",4},
			{"iMac","iMac",3},
			{"Samsung","Samsung SyncMaster 941BW",1},
			{"Samsung","Samsung Galaxy Tab 10.1",7}
			
		};
	}
	
	
	@Test(dataProvider = "getProductImagesTestData")
	public void productImagesTest(String searchKey,String mainProductName,int imagesCount) {
		 resultsPage=accPage.performSearch(searchKey);
		   prodInfoPage=resultsPage.selectProduct( mainProductName);
		   int actImagesCount=prodInfoPage.getProductImagesCount();
		   Assert.assertEquals(actImagesCount,imagesCount);
		   
		
	}
	
	@Test
	public void productMetaDataTest() {
		resultsPage=accPage.performSearch("MacBook");
		   prodInfoPage=resultsPage.selectProduct("MacBook Pro");
		 Map<String,String>actProdInfoMap=  prodInfoPage.getProductInfo();
         softAssert.assertEquals(actProdInfoMap.get("Brand"),"Apple");
         softAssert.assertEquals(actProdInfoMap.get("Availability"),"In Stock"); 
         softAssert.assertEquals(actProdInfoMap.get("Product Code"),"Product 18");
         softAssert.assertEquals(actProdInfoMap.get("Reward Points"),"800");
         softAssert.assertEquals(actProdInfoMap.get("actual price"),"$2,000.00");
         softAssert.assertAll();
		   
	}
	
	
	
	
}
