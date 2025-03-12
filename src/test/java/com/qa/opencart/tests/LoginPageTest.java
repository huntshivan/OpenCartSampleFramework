package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 100: Design open cart login page")
@Story("US 101: Design login page features for open cart application")
@Feature("Feature 201: Adding login features")
public class LoginPageTest extends BaseTest {
	
	@Description("Checking login page Title....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void verifyLoginPageTitle() {
		String loginPageTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(loginPageTitle, "Account Login");
	}
	
	@Description("Checking login page URL....")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void verifyLoginPageURL() {
		String loginPageURL = loginPage.getLoginPageURL();
		Assert.assertTrue(loginPageURL.contains("account/login"));
	}
	
	@Description("Checking forgot pwd link on login page ....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void verifyForgotPwdLink() {
		Assert.assertTrue(loginPage.isForgotPwdAvailable());
	}
	
	@Description("Checking user is able to login....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void verifyUserLogin() throws InterruptedException {
		acctPage = loginPage.signIn(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(acctPage.getAcctPageTitle(), "My Account");
	}

}