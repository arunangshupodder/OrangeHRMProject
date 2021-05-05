package base;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class BasePages {
	
	private static WebDriver driver = null;
	protected static WebDriverWait wait = null;
	protected static FluentWait<WebDriver> fWait = null;

	
	public BasePages(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 15);
		
		fWait = new FluentWait<>(driver)
	            .withTimeout(Duration.ofSeconds(5))
	            .pollingEvery(Duration.ofSeconds(1))
	            .ignoring(NoSuchElementException.class);
	}

	protected void setText(String text, By element) {
		try {
			getElement(element).sendKeys(text);
		} catch (NoSuchElementException | TimeoutException e) {
			Assert.fail("Element not found: " + e.getMessage());
		}
	}
	
	protected void clickButton(By element) {
		try {
			getElement(element).click();
		} catch (NoSuchElementException | TimeoutException e) {
			Assert.fail("Element not found: " + e.getMessage());
		}
	}
	
	protected void scrollElementToView(By element) {
		try {
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", getElement(element));
		} catch (JavascriptException e) {
			Assert.fail("Element not found: " + e.getMessage());
		}
	}
	
	protected void moveToElement(By element) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(getElement(element)).build().perform();
		} catch (NoSuchElementException | TimeoutException e) {
			Assert.fail("Element not found: " + e.getMessage());
		}
	}
	
	protected WebElement getElement(By element) {
		waitForElementUsingDynamicWait(element);
		return driver.findElement(element);
	}
	
	protected void waitForElementUsingDynamicWait(By element) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	protected void waitForContentInURL(String url) {
		wait.until(ExpectedConditions.urlContains(url));
	}
}
