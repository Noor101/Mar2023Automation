package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {

	@DataProvider
	public Object[][] loginWrongTestData() {
		return new Object[][] { { "feeff@gmail.com", "test!123" }, { "noormohammed1050@gmail.com", "Noor@12345" },
				{ "   ", "Noor@100" }, { "##$$fsf@gmail.com", "Noor@12334" }, { " ", " " }

		};
	}

	@Test(dataProvider = "loginWrongTestData")
	public void loginNegativeTest(String username, String password) {
	Assert.assertFalse(loginPage.doLoginWithWrongCredentials(username, password));
	}

}
