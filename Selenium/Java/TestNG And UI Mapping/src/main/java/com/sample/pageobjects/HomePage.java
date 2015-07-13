package com.sample.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sample.commons.PropertiesCache;

public class HomePage extends BasePageObject {
	private final String expectedPageTitle = "Българският Интернет портал!";

	private By loginBtn = By.cssSelector(PropertiesCache.getInstance()
			.getProperty("homePageLoginBtnCss"));

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public boolean verifyBasePageTitle() {
		return getPageTitle().contains(expectedPageTitle);
	}

	public LoginPage clickLoginBtn() {
		WebElement loginButton = fluentWait(loginBtn);
		loginButton.click();

		return new LoginPage(getDriver());
	}
}