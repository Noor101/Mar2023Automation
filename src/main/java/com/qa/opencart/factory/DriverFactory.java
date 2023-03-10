package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	private OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the webdriver
	 * 
	 * @param browserName
	 * @return this will return the driver
	 */
	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		System.out.println("browser name is : " + browserName);
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else if (browserName.equals("safari")) {
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else {
			System.out.println("Please pass the right browser: " + browserName);
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		// getDriver().get(prop.getProperty("url"));
		// openUrl(prop.getProperty("url"));
		URL url;
		try {
			url = new URL(prop.getProperty("url"));
			openUrl(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return getDriver();

	}

	/**
	 * getDriver() : it will return a thread local copy of the webdriver
	 * 
	 * @return this will return properties prop reference
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * this method is used to initialize the properties
	 * 
	 * @return this will return properties prop reference
	 */
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");

		if (envName == null) {
			System.out.println("Running on PROD env: ");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on environment :" + envName);
			try {
				switch (envName.toLowerCase()) {

				case "qa":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
					break;
				case "stag":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\stag.config.properties");
					break;
				case "uat":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\uat.config.properties");
					break;

				default:
					System.out.println("please pass the right environment");
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File desitnation = new File(path);
		try {
			FileUtils.copyFile(src, desitnation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * Launch url
	 * 
	 * @param url
	 */
	public void openUrl(String url) {
		try {
			if (url == null) {
				throw new Exception("url is null");
			}
		} catch (Exception e) {
		}
		getDriver().get(url);
	}

	public void openUrl(URL url) {
		try {
			if (url == null) {
				throw new Exception("url is null");
			}
		} catch (Exception e) {
		}
		getDriver().navigate().to(url);
	}

	// http://amazon.com/accpage/users.html
	public void openUrl(String baseUrl, String path) {
		try {
			if (baseUrl == null) {
				throw new Exception("url is null");
			}
		} catch (Exception e) {
		}
		getDriver().get(baseUrl + "/" + path);
	}

	// http://amazon.com/accpage/users.html?fn="naveen"&ln="k"
	public void openUrl(String baseUrl, String path, String queryParam) {
		try {
			if (baseUrl == null) {
				throw new Exception("url is null");
			}
		} catch (Exception e) {
		}
		getDriver().get(baseUrl + "/" + path + "?" + queryParam);
	}

}
