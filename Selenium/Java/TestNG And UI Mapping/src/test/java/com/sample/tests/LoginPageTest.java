package com.sample.tests;

import static org.testng.AssertJUnit.assertTrue;
import org.openqa.selenium.WebDriver;

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
	public void setUp() {
		driver = getDriver();
	}

	@Parameters({ "username", "password" })
	@Test
	public void verifySuccessfulLogin(String username, String password) {
		homePage = new HomePage(driver);
		loginPage = homePage.clickLoginBtn();

		assertTrue(loginPage.verifySuccessfulLogin(username, password));
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