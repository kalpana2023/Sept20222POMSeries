package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {
	private WebDriver driver; 
	private ElementUtil eleUtil;
	
	private By productHeader=By.cssSelector("div#content h1");
	private By productImages=By.cssSelector("a.thumbnail");
	private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	
	private Map<String,String> productMap;

	
	
	public  ProductInfoPage(WebDriver driver) {
	this.driver=driver;	
	eleUtil =new ElementUtil(driver);
	}
	public  String getProductHeader() {
		return eleUtil.doGetElementText(productHeader);
	}
	public int getProductImagesCount() {
		int imagesCount= eleUtil.waitForElementsVisible(productImages, TimeUtil.DEFAULT_TIME_OUT).size();
		 System.out.println("the images count="+ imagesCount);
		 return imagesCount;
	}
	
	
	public Map<String, String> getProductInfo() {
		productMap=new HashMap<String,String>();
		getProductMetaData();
		getProductPriceData();
		System.out.println(productMap);
		return productMap;
		
	}
	private void getProductMetaData() {
		List<WebElement>metaDataList=  eleUtil.getElements(productMetaData);
		System.out.println("Meta data count ="+metaDataList.size());
		
		productMap=new HashMap<String,String>();
		for (WebElement e:metaDataList) {
			String meta=e.getText();
			System.out.println(meta);	
			String metaData[]=meta.split(":");
			String metaKey=metaData[0].trim();
			String metaValue=metaData[1].trim();
			productMap.put(metaKey, metaValue);
			
			
		   }
		
		}
	
	
	private void getProductPriceData() {
		List<WebElement>metaPriceList=  eleUtil.getElements(productPriceData);
		System.out.println("Meta price count ="+metaPriceList.size());
		
	      String price= metaPriceList.get(0).getText().trim();
	      System.out.println(price);
	      String exTaxPrice=metaPriceList.get(1).getText().trim();
	      System.out.println(exTaxPrice);
			productMap.put("actual price", price);
			productMap.put("actual ExTax price", exTaxPrice);
			
			
		   }
		
	
	
	

}
