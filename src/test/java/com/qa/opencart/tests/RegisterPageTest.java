package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regPageSetup() {
		registerPage = loginPage.goToRegisterPage();

	}
	
	public String getRandomMail() {
		Random randomGenerator = new Random();
		String email = "Noorautomation"+randomGenerator.nextInt(1000)+"@gmail.com";
		return email;
	}

	@DataProvider
	public Object[][] getRegisterData() {
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}

	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, String contactNo, String password,
			String subsribe) {
		Assert.assertTrue(registerPage.accountRegistration(firstName, lastName, getRandomMail(), contactNo, password, subsribe));
	}

}
