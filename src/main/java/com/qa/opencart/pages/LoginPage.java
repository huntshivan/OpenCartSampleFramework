package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.loggers.Log;
import com.qa.opencart.utils.ElementsUtil;

import io.qameta.allure.Step;

public class LoginPage { 
	
	/*
	 * 1. Private WebDriver
	 * 2. Private By locators
	 * 3. Public Page Class Constructor
	 * 4. Public Page Actions/Methods
	 */
	
	private By email = By.id(("input-email"));
	private By password = By.id("input-password");
	private By forgotPasswordLink = By.linkText("Forgotten Password");
	private By signInBtn = By.cssSelector("input[type='submit']");
	private By registrationLink = By.linkText("Register");
	private By accountPageHeaders = By.xpath("//div/h2");
	
	private WebDriver driver;
	private ElementsUtil eleUtil;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementsUtil(driver);
	}
	
	@Step("getting login page title....")
	public String getLoginPageTitle() {
		String loginPageTitle = driver.getTitle();
		//System.out.println("Login Page Title: "+loginPageTitle);
		Log.info("Login Page Title: "+loginPageTitle);
		return loginPageTitle;
	}
	
	@Step("getting login page url....")
	public String getLoginPageURL() {
		String loginPageURL = driver.getCurrentUrl();
		//System.out.println("Login Page URL: "+loginPageURL);
		Log.info("Login Page URL: "+loginPageURL);
		return loginPageURL;
	}
	
	@Step("getting the state of forgot pwd link...")
	public boolean isForgotPwdAvailable() {
		return driver.findElement(forgotPasswordLink).isDisplayed();
	}
	
	@Step("navigating to registration page...")
	public RegistrationPage navigateToRegistrationLink( ) {
		eleUtil.waitForElementPresence(registrationLink, 10).click();
		return new RegistrationPage(driver);
	}
	
	@Step("login with username: {0} and password: {1}")
	public AccountPage signIn(String uname, String pass) {
		eleUtil.waitForElementPresence(email,10).sendKeys(uname);
		eleUtil.waitForElementPresence(password,10).sendKeys(pass);
		eleUtil.waitForElementPresence(signInBtn,10).click();
		eleUtil.waitForTitleContains("My Account",10);
		return new AccountPage(driver);
		
	}

}
