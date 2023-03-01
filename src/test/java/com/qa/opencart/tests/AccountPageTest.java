package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void accPageTitleTest() {
		String actTitle = accountsPage.getAccPageTitle();
		System.out.println("Account page title is : " + actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void isLogoutExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExits());
	}

	@Test(priority = 3)
	public void accPageSectionTest() {
		List<String> actAccSecList = accountsPage.getAccountSecList();
		Assert.assertEquals(actAccSecList, Constants.getExpectedAccList());
	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] { { "Macbook" }, { "Apple" }, { "Samsung" },

		};
	}

	@Test(priority = 4, dataProvider = "productData")
	public void searchTest(String productName) {
		searchResultsPage = accountsPage.doSearch(productName);
		Assert.assertTrue(searchResultsPage.getProductCountList() > 0);
	}

	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] { { "Macbook", "MacBook Pro" }, { "Apple", "Apple Cinema 30\"" },
				{ "samsung", "Samsung SyncMaster 941BW" }, {"imac" , "iMac"}

		};
	}

	@Test(priority = 5, dataProvider = "productSelectData")
	public void selectProductTest(String ProductName, String mainProductName) {
		searchResultsPage = accountsPage.doSearch(ProductName);
		productInfoPage = searchResultsPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(), mainProductName);

	}

}
