package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementsUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementsUtil eleUtil;
	
	private Map<String, String> productMeta = new HashMap<String, String>();
	
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By pricingMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By productImages = By.xpath("//ul[@class='thumbnails']//img");
	private By productName = By.xpath("//div[@id='content']//h1");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementsUtil(driver);
	}
	
	public int getProductImagesCount() {
		List<WebElement> images = eleUtil.waitForElementsPresence(productImages, 10);
		return images.size();
	}
	
	public String getProductName() {
		return eleUtil.waitForElementPresence(productName,10).getAttribute("innerHTML");
	}
	
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.waitForElementsPresence(productMetaData,10);
		for (WebElement e: metaList) {
			String val = e.getText();
			String metaKey = val.split(":")[0].trim();
			String metaValue = val.split(":")[1].trim();
			productMeta.put(metaKey, metaValue);
		}
	}
	
	private void getProductPricingData() {
		List<WebElement> pricingList = eleUtil.waitForElementsPresence(pricingMetaData, 10);
			String val = pricingList.get(0).getText();
			productMeta.put("productPricing", val);
			String extax = pricingList.get(1).getText().split(":")[1].trim();
			productMeta.put("Ex Tax", extax);
			
		}
	
	public Map<String,String> getProductDetailsMap() {
		productMeta.put("productName", getProductName());
		productMeta.put("totalImages", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPricingData();
		return productMeta;
	}
	

}
