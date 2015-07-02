package com.sample.commons;

import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestBaseSetup {
	private static final String pathToTheResourcesDir = "\\src\\test\\resources\\";
	private static final String currentWorkingDirPath = Paths.get("")
			.toAbsolutePath().toString();

	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	@Parameters({ "browserType", "appURL" })
	@BeforeMethod
	public void initializeTestBaseSetup(String browserType, String appURL) {
		try {
			setDriver(browserType, appURL);

		} catch (final Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	private void setDriver(String browserType, String appURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType
					+ " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		}
	}

	private static WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching Google Chrome with new profile..");

		System.setProperty("webdriver.chrome.driver", currentWorkingDirPath
				+ pathToTheResourcesDir + "chromedriver.exe");
		final WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);

		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		final WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);

		return driver;
	}
}