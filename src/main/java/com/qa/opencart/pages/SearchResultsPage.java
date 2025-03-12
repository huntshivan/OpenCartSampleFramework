package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementsUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementsUtil eleUtil;
	
	private By searchPageHeader = By.xpath("//div[@id='content']/h2");
	private By searchBox = By.cssSelector("input#input-search");
	private By searchResultsItems = By.xpath("//h4/a");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementsUtil(driver);
	}
	
	public String getSearchPageTitle() {
		String searchPageTitle = driver.getTitle();
		System.out.println("Login Page Title: "+searchPageTitle);
		return searchPageTitle;
	}
	
	public String getSearchPageURL() {
		String searchPageURL = driver.getCurrentUrl();
		System.out.println("Login Page URL: "+searchPageURL);
		return searchPageURL;
	}
	
	public String getSearchPageHeader() {
		String searchHeaderName = driver.findElement(searchPageHeader).getAttribute("innerHTML");
		//String searchPageHeaderName = searchHeader.getAttribute("innerHTML");
		return searchHeaderName;
	}
	
	public String getSearchBoxValue() {
		String searchBoxValue = driver.findElement(searchBox).getAttribute("value");
		return searchBoxValue;
	}
	
	public int getSearchResultsList() {
		int totalItemsCount = driver.findElements(searchResultsItems).size();
		return totalItemsCount;
	}
	
	public ProductInfoPage selectProductFromSearch(String item) {
		List<WebElement> items = driver.findElements(searchResultsItems);
		for(WebElement e : items) {
			String val = e.getText();
			if(val.equals(item)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
