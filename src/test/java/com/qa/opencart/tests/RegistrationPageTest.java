package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtility;
import com.qa.opencart.utils.StringUtil;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void registrationPageSetup() {
		registrationPage = loginPage.navigateToRegistrationLink();
		
	}
	
	@DataProvider
	public Object[][] userRegistrationData() {
		return new Object[][] { {"fn","ln","123456","Test@123", "Test@123", "Yes", true} 
		};
	}
	
	@DataProvider
	public Object[][] userRegistrationDataFromExcel() {
		return ExcelUtility.getTestData("Registration");
	}
	
	@DataProvider(name="csvregdata")
	public Object[][] userRegistrationDataFromCSV() {
		return CSVUtil.csvData("register");
	}
	
	@Test(dataProvider = "csvregdata")
	public void verifyUserRegistration(String fname, String lname, String telephone, String pass, String confirm, String subscribe, String news) {
		registrationPage.doUserRegistration(fname, lname, StringUtil.randomEmail, telephone, pass, confirm, subscribe, news);
		
	}

}
