package com.sample.tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import com.sample.commons.TestBaseSetup;
import com.sample.pageobjects.HomePage;
import com.sample.pageobjects.LoginPage;

/*
 * In case we need not random execution of the tests. Considered bad practice!
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginPageTest extends TestBaseSetup {
	private WebDriver driver;
	private LoginPage login;
	private HomePage home;

	@Before
	public void setUp() {
		initializeTestBaseSetup(chromeBrowser, appUrl);
		driver = getDriver();
	}

	@Test
	public void _001_correctUserNameOrPassword() {
		home = new HomePage(driver);
		login = home.clickLoginButton();
		login.enterUsername("g5140549@trbvm.com");	
		login.enterPassword("2477261f");
		login.clickLoginButton();
		
		assertTrue(login.successMessagePresent());
		
		login.logout();
	}

	@Test
	public void _002_wrongUserNameOrPassword() {
		home = new HomePage(driver);
		login = home.clickLoginButton();
		login.enterUsername("username");
		login.enterPassword("password");
		login.clickLoginButton();
		
		assertTrue(login.failureMessagePresent());
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
}