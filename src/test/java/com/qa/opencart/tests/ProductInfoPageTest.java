package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void productHeaderTest() {
		searchResultsPage = accountsPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}

	@Test
	public void productImageCountTest() {
		searchResultsPage = accountsPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getImagesCount(), Constants.MACBOOK_PRO_IMAGE_COUNT);

	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = accountsPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String , String > actproductInfoMap = productInfoPage.getProductInfo();
		actproductInfoMap.forEach((k,v) -> System.out.println(k+ ": " + v));
		softassert.assertEquals(actproductInfoMap.get("name"), "MacBook Pro");
		softassert.assertEquals(actproductInfoMap.get("price"), "$2,000.00");
		softassert.assertEquals(actproductInfoMap.get("Brand"), "Apple");
		softassert.assertAll();
	}

}
