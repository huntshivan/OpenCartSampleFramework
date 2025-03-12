package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.loggers.Log;
import com.qa.opencart.utils.ElementsUtil;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementsUtil eleUtil;
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementsUtil(driver);
	}
	
	private By regPageHeader = By.id("content");
	private By firstName = By.id("input-firstname");
	private By lastName =  By.id("input-lastname");
	private By emailId = By.id("input-email");
	private By password = By.id("input-telephone");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("//input[@name='newsletter' and @value ='1']");
	private By subscribeNo = By.xpath("//input[@name='newsletter' and @value ='0']");
	private By newsletter = By.xpath("//input[@name='agree']");
	private By continueBtn = By.xpath("//input[@type='submit']");
	
	public String getRegistrationPageTitle() {
		String regPageTitle = eleUtil.waitForTitleContains("Register", 10);
		Log.info("Registration Page Title Is: "+regPageTitle);
		return regPageTitle;
	}
	
	public String getRegistrationPageURL() {
		String regPageURL = eleUtil.waitForURLContains("account/register", 10);
		Log.info("Registration Page URL Is: "+regPageURL);
		return regPageURL;
	}
	
	public String getRegistrationPageHeader() {
		String regPageHeaderText = eleUtil.waitForElementPresence(regPageHeader, 10).getText();
		Log.info("Registration Page Header Is: "+regPageHeader);
		return regPageHeaderText;
	}
	
	public void doUserRegistration(String fname, String lname, String email, String tel, String pass, String confirmPass, String subscriber, String news) {
		eleUtil.waitForElementPresence(firstName, 10).sendKeys(fname);
		eleUtil.waitForElementPresence(lastName, 10).sendKeys(lname);
		eleUtil.waitForElementPresence(emailId, 10).sendKeys(email);
		eleUtil.waitForElementPresence(password, 10).sendKeys(pass);
		eleUtil.waitForElementPresence(confirmPassword, 10).sendKeys(confirmPass);
		if(subscriber.equals("Yes")) {
			eleUtil.waitForElementPresence(subscribeYes, 10).click();
		}
		else {
			eleUtil.waitForElementPresence(subscribeNo, 10).click();
		}
		
		if(news.equals("true")) {
			eleUtil.doClick(newsletter);
		}
		//eleUtil.doClick(continueBtn);
		
	}

}
