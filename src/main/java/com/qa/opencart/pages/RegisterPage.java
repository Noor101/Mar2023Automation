package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By contactNo = By.xpath("//input[@type='tel']");
	private By password = By.id("input-password");
	private By confirmPwd = By.id("input-confirm");
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[1]//input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[2]//input[@type='radio']");
	private By checkBox = By.xpath("//input[@type='checkbox']");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By accountsuccessMsg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public boolean accountRegistration(String firstName, String lastName, String email, String contactNo,
			String password, String subsribe) {
		eleutil.doSendKeys(this.firstName, firstName);
		eleutil.doSendKeys(this.lastName, lastName);
		eleutil.doSendKeys(this.email, email);
		eleutil.doSendKeys(this.contactNo, contactNo);
		eleutil.doSendKeys(this.password, password);
		eleutil.doSendKeys(this.confirmPwd, password);
		if (subsribe.equals("yes")) {
			eleutil.doClick(subscribeYes);
		} else if (subsribe.equals("no")) {
			eleutil.doClick(subscribeNo);
		}
		eleutil.doClick(checkBox);
		eleutil.doClick(loginBtn);
		String text = eleutil.waitForElement(accountsuccessMsg, 10, 1000).getText();
		if (text.contains(Constants.REGISTER_SUCCESS_MSG)) {
			eleutil.doClick(logoutLink);
			eleutil.doClick(registerLink);
			return true;
		}
		return false;

	}

}
