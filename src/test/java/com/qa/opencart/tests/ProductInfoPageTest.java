package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void ProductInfoPageSetup() {
		acctPage = loginPage.signIn(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void verifyProductInfo() {
		searchPage = acctPage.accountPageSearchProduct("macbook");
		productInfo = searchPage.selectProductFromSearch("MacBook Air");
		Map<String,String> productActDetailsMap = productInfo.getProductDetailsMap();
		softAssert.assertEquals(productActDetailsMap.get("productName"), "MacBook Air");
		softAssert.assertEquals(productActDetailsMap.get("totalImages"), "4");
		softAssert.assertEquals(productActDetailsMap.get("Brand"), "Apple");
		softAssert.assertAll();
	}

}
