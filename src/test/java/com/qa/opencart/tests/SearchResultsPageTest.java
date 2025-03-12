package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultsPageTest extends BaseTest {
	
	@BeforeClass
	public void searchPageTestSetup() {
		acctPage = loginPage.signIn(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test (priority = 1)
	public void verifySearchPageTitle() {
		searchPage = acctPage.accountPageSearchProduct("imac");
		String actualTitle = searchPage.getSearchPageTitle();
		Assert.assertTrue(actualTitle.contains("Search -"));
		
	}
	
	@Test (priority = 2)
	public void verifySearchPageURL() {
		searchPage = acctPage.accountPageSearchProduct("imac");
		String actualURL = searchPage.getSearchPageURL();
		Assert.assertTrue(actualURL.contains("route=product/search"));
		
	}
	
	@Test (priority = 3)
	public void verifySearchedText() {
		searchPage = acctPage.accountPageSearchProduct("imac");
		Assert.assertEquals(searchPage.getSearchBoxValue(),"imac");
	}
	
	@Test (priority = 4)
	public void verifySearchPageHeader() {
		searchPage = acctPage.accountPageSearchProduct("imac");
		Assert.assertEquals(searchPage.getSearchPageHeader(), "Products meeting the search criteria");
	}
	
	@Test (priority = 5)
	public void verifyTotalProductsSearched( ) {
		searchPage = acctPage.accountPageSearchProduct("imac");
		Assert.assertEquals(searchPage.getSearchResultsList(), 1);
	}
	
	@Test (priority = 6)
	public void selectSearchedProduct() {
		searchPage = acctPage.accountPageSearchProduct("imac");
		searchPage.selectProductFromSearch("iMac");
		
	}

}
