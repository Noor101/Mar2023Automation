package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Epic("Epic 101 : Open Cart Login page")
@Story("User Story 101 : Open cart login with multiple features ")
@Listeners(TestAllureListener.class)
public class LoginPage {

	// 1. Declare private driver
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 2. Page Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. By Locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By registerLink = By.linkText("Register");
	private By forgotpwdLink = By.linkText("Forgotten Password");
	private By loginErrorMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");

	// 4. Page Actions
	@Step("getting login page title")
	public String getLoginPageTitle() {
		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	@Step("getting login page url")
	public boolean getLoginPageUrl() {
		return eleUtil.waitForUrlToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}

	@Step("checking forgot password link exist or not")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotpwdLink);
	}

	@Step("checking register link exist or not")
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}

	@Step("do login with username: {0} and password {1}")
	public AccountsPage doLogin(String un, String pwd) {

		System.out.println("login with : " + un + " : " + pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}

	@Step("do login with wrong username: {0} and wrong password {1}")
	public boolean doLoginWithWrongCredentials(String un, String pwd) {
		System.out.println("try to login with Wrong Credentials : " + un + ": " + pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, un);
		eleUtil.doClick(loginBtn);
		String errorMsg = eleUtil.doGetText(loginErrorMsg);
		System.out.println(errorMsg);
		if (errorMsg.contains(Constants.LOGIN_ERROR_MESSG)) {
			System.out.println("Login is not done");
			return false;
		} else if (errorMsg.contains(Constants.LOGIN_WARNING_MESSG)) {
			System.out.println("Login is not done");
			return false;
		}
		return true;
	}

	@Step("navigation to register page")
	public RegisterPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

}
