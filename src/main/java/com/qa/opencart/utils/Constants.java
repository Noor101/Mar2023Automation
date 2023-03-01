package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String LOGIN_PAGE_TITLE = "Account Login11";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account12";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final int DEFAULT_TIME_OUT = 5;
	public static final int MACBOOK_PRO_IMAGE_COUNT = 4;
	public static final String LOGIN_ERROR_MESSG = "No match for E-Mail Address and/or Password.";
	public static final String LOGIN_WARNING_MESSG = "Your account has exceeded allowed number of login attempts. Please try again in 1 hour";
	public static final String REGISTER_SUCCESS_MSG = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "registration";
	
	public static List<String> getExpectedAccList() {
		List<String> expSecList = new ArrayList<String>();
		expSecList.add("My Account");
		expSecList.add("My Orders");
		expSecList.add("My Affiliate Account");
		expSecList.add("Newsletter");
		return expSecList;
	}

}