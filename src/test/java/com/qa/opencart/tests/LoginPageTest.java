package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest {
	
	@Description("login page title test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("page title: " + actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);
		
	}
	
	@Description("login page url test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void loginPageUrlTest() {
		Assert.assertTrue(loginPage.getLoginPageUrl());
	}
	
	@Description("forgot password link test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("Register link test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=4)
	public void registerLinkTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
	@Description("Login with valid username and password test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=5)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(accountsPage.getAccPageTitle(), Constants.ACCOUNTS_PAGE_TITLE); 
		
	}

}
