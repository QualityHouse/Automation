package com.sample.tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sample.commons.TestBaseSetup;
import com.sample.pageobjects.HomePage;
import com.sample.pageobjects.LoginPage;

public class LoginPageTest extends TestBaseSetup {
	private WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;

	@BeforeMethod
	@Parameters({ "browserType", "appURL" })
	public void setUp(String browserType, String appURL) {
		initializeTestBaseSetup(browserType, appURL);
		driver = getDriver();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Parameters({ "username", "password" })
	@Test
	public void verifySuccessfulLogin(String username, String password) {
		homePage = new HomePage(driver);
		loginPage = homePage.clickLoginBtn();

		assertTrue(loginPage.verifySuccessfulLogin(username,
				password));
	}

	@Parameters({ "wrongUsername", "wrongPassword" })
	@Test
	public void verifyUnsuccessfulLogin(String wrongUsername,
			String wrongPassword) {
		homePage = new HomePage(driver);
		loginPage = homePage.clickLoginBtn();

		assertTrue(loginPage.verifyUnsuccessfulLogin(wrongUsername,
				wrongPassword));
	}
}