package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	private By accountSections = By.cssSelector("div#content h2");
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchBtn = By.cssSelector("div#search button");
	private By myAccountHeader = By.cssSelector("div#content h2:nth-of-type(1)");
	
	
	public String getAccPageTitle() {
		return eleUtil.doGetTitle(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public boolean isLogoutLinkExits() {
		return eleUtil.doIsDisplayed(logoutLink);
	}
	
	public void logout() {
		if(isLogoutLinkExits()) {
			eleUtil.doClick(logoutLink);
		}
	}
	
	public List<String> getAccountSecList() {
		List<WebElement> accSecList = eleUtil.waitForElementsToBeVisible(accountSections, 10);
		List<String> accSecValList = new ArrayList<String>();
		for(WebElement e : accSecList) {
			String accSecText = e.getText();
			accSecValList.add(accSecText);
		}
		return accSecValList;
	}
	
	public boolean isSearchFieldExist() {
		return eleUtil.doIsDisplayed(search);
	}
	
	public void getMyAccountHeaderText() {
		eleUtil.doPresenceOfElementLocated(accountSections, Constants.DEFAULT_TIME_OUT, 200);
		
	}
	
	public SearchResultsPage doSearch(String productName) {
		System.out.println("Searching the product : " + productName);
		eleUtil.doSendKeys(search, productName);
		eleUtil.doClick(searchBtn);
		return new SearchResultsPage(driver);
	}

}
