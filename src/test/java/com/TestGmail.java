package com;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestGmail {

	String driverPath = System.getProperty("user.dir") + "\\Resource\\chromedriver.exe";

	WebDriver driver;
	LoginPage login;
	ComposeAndSendEmail email;
	String username = null;
	String password = null;
	String testDataFilePath = null;

	@BeforeTest
	public void setup() throws IOException {
		testDataFilePath = System.getProperty("user.dir") + "\\Resource";
		File file = new File(testDataFilePath + "\\TestData.properties");
		FileInputStream fis = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fis);
		username = prop.getProperty("EmailUsername");
		password = prop.getProperty("EmailPass");
		if (username.equals("NA")) {
			System.out.println("Please enter the 'Username' and 'Password' in 'TestData.properties' file.");
			System.out.println("'TestData.properties' file path: " + testDataFilePath);
			Assert.assertTrue(false,
					"Need to provide Credential is file 'TestData.properties'. Path is: " + testDataFilePath);
		}
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(
				"https://accounts.google.com/AccountChooser/identifier?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&flowName=GlifWebSignIn&flowEntry=AccountChooser");
		driver.manage().window().maximize();
	}

	@Test(description = "Automation Test Script for Sending an Email with attachment.")
	public void login() throws InterruptedException, AWTException {
		System.out.println("1: " + username);

		System.out.println("1YYY: ");
		login = new LoginPage(driver);
		login.loginToGmail(username, password);
		login.executeAssert();
		email = new ComposeAndSendEmail(driver);
		email.ComposeGmail("abcdemoautomation@gmail.com", "Automation", "TestDemo");
		email.sendEmail();
		email.executeAssert();
	}

	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}

}