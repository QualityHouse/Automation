package cucumber.feature.login;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.sample.commons.TestBaseSetup;
import com.sample.pageobjects.HomePage;
import com.sample.pageobjects.LoginPage;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps extends TestBaseSetup {
	private final String chromeBrowser = "chrome";
	private final String firefoxBrowser = "firefox";
	private final String appUrl = "http://dir.bg";

	private WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;

	@Before
	public void startUp() {
		initializeTestBaseSetup(chromeBrowser, appUrl);
		driver = getDriver();
	}
	
	@After
	public void tearDown()  {
		driver.quit();
	}

	@Given("^the login page is opened$")
	public void openLoginPage() throws Throwable {
		homePage = new HomePage(driver);
		loginPage = homePage.clickLoginButton();
	}

	@When("^I enter (.+) users's email in the email field$")
	public void enterLoginEmail(String emailType) throws Throwable {
		if (emailType.equalsIgnoreCase("valid")) {
			loginPage.enterUsername("g5140549@trbvm.com");
		} else {
			loginPage.enterUsername("username");
		}
	}

	@And("^I enter (.+) password in the password field$")
	public void enterWrongPassword(String passwordType) throws Throwable {
		if (passwordType.equalsIgnoreCase("valid")) {
			loginPage.enterPassword("2477261f");
		} else {
			loginPage.enterPassword("123456");
		}
	}

	@And("^I submit$")
	public void submitLoginForm() throws Throwable {
		loginPage.clickLoginButton();
	}

	@Then("^an error text appears$")
	public void errorTextAppearance() throws Throwable {
		assertTrue(loginPage.failureMessagePresent());
	}

	@Then("^my email should be displayed in the upper right corner$")
	public void emailDisplayedInTheUpperRightCorner() throws Throwable {
		assertTrue(loginPage.successMessagePresent());
	}

	@And("^I'm (.+) logged in$")
	public void isUserNotLogged(String validity) throws Throwable {
		if (validity.equalsIgnoreCase("not")) {
			assertFalse(loginPage.isUserLogged());
		} else {
			assertTrue(loginPage.isUserLogged());
		}
	}

	@And("^I should be able to logout$")
	public void logout() throws Throwable {
		loginPage.logout();
	}
}
