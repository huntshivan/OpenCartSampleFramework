package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void accountPageTestSetup() {
		acctPage = loginPage.signIn(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test (priority = 1)
	public void verifyAcctPageTitle() {
		String acctPageTitle = acctPage.getAcctPageTitle();
		Assert.assertEquals(acctPageTitle, "My Account");
	}
	
	@Test (priority = 2)
	public void verifyAcctPageURL() {
		String acctPageTitle = acctPage.getAcctPageURL();
		Assert.assertTrue(acctPageTitle.contains("account/account"));
	}
	
	@Test (priority = 3)
	public void verifyLogoutPageDisplayed() {
		Assert.assertTrue(acctPage.isLogoutLinkDisplayed());
	}
	
	@Test (priority = 4)
	public void verifySearchResults() {
		acctPage.accountPageSearchProduct("macbook");
	}

}
