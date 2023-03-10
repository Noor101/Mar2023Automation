package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {

	DriverFactory df;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	RegisterPage registerPage;
	AccountsPage accountsPage;
	SearchResultsPage searchResultsPage;
	ProductInfoPage productInfoPage;
	SoftAssert softassert;

	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_driver(prop);
		loginPage = new LoginPage(driver);
		registerPage = new RegisterPage(driver);
		softassert = new SoftAssert();

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
