package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementsUtil;

public class AccountPage {
	
	private WebDriver driver;
	ElementsUtil eleUtil;
	
	private By myAccountLink = By.linkText("Account");
	private By accountPageHeaders = By.xpath("//div/h2");
	private By logoutLink = By.linkText("Logout");
	private By searchBox = By.xpath("//div/input[@name='search']");
	private By searchBtn = By.xpath("//div[@id='search']/span/button");
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementsUtil(driver);
	}
	
	public String getAcctPageTitle() {
		String loginPageTitle = driver.getTitle();
		System.out.println("Login Page Title: "+loginPageTitle);
		return loginPageTitle;
	}
	
	public String getAcctPageURL() {
		String loginPageURL = driver.getCurrentUrl();
		System.out.println("Login Page URL: "+loginPageURL);
		return loginPageURL;
	}
	
	public boolean isMyAccountLinkDisplayed() {
		return eleUtil.waitForElementPresence(myAccountLink,10).isDisplayed();
	}
	
	public boolean isLogoutLinkDisplayed() {
		return eleUtil.waitForElementPresence(logoutLink,10).isDisplayed();
	}
	
	public void userLogout() {
		eleUtil.doClick(logoutLink);
	}
	
	public SearchResultsPage accountPageSearchProduct(String product) {
		eleUtil.waitForElementPresence(searchBox, 10).clear();
		eleUtil.waitForElementPresence(searchBox, 10).sendKeys(product);;
		eleUtil.doClick(searchBtn);
		return new SearchResultsPage(driver);
	}

}
