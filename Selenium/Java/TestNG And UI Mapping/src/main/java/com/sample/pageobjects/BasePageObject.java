package com.sample.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePageObject {
	private final WebDriver driver;

	public BasePageObject(WebDriver driver) {
		this.driver = driver;
	}

	protected WebDriver getDriver() {
		return driver;
	}

	protected String getPageTitle() {
		return driver.getTitle();
	}

	protected Boolean waitForElementToBeDisplayed(By locator,
			Integer... timeout) {
		try {
			waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
					(timeout.length > 0 ? timeout[0] : null));
		} catch (final TimeoutException exception) {
			return false;
		}
		return true;
	}

	private void waitFor(ExpectedCondition<WebElement> condition,
			Integer timeout) {
		timeout = timeout != null ? timeout : 5;
		final WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(condition);
	}
}
