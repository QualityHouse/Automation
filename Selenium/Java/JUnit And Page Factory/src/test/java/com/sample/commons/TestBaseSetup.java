package com.sample.commons;

import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBaseSetup {
	private static final String pathToTheResourcesDir = "\\src\\test\\resources\\drivers\\";
	private static final String currentWorkingDirPath = Paths.get("")
			.toAbsolutePath().toString();
	
	private WebDriver driver;
	
	public void initializeTestBaseSetup(String browserType, String appURL) {
		try {
			setDriver(browserType, appURL);
		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	private void setDriver(String browserType, String appURL) {
		switch (browserType) {
		case "chrome":
			this.driver = initChromeDriver(appURL);
			break;
		case "firefox":
			this.driver = initFirefoxDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType
					+ " is invalid, Launching Firefox as browser of choice..");
			this.driver = initFirefoxDriver(appURL);
		}
	}

	private static WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching Google Chrome with new profile..");

		System.setProperty("webdriver.chrome.driver", currentWorkingDirPath
				+ pathToTheResourcesDir + "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		
		return driver;
	}
}
