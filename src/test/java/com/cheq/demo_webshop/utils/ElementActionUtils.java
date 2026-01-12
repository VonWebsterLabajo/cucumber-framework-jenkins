package com.cheq.demo_webshop.utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Utility class that provides common WebDriver element actions with configurable waits.
 * Wraps click, input, visibility checks, and text validation using custom wait logic
 * and centralized logging for better stability and traceability.
 */
public class ElementActionUtils {
	
	private WebDriver driver;
	private WaitUtil wait;
	private final long visibilityTimeout = Long.parseLong(ConfigReader.get("visibilityTimeout"));
	private final long clickableTimeout = Long.parseLong(ConfigReader.get("clickableTimeout"));
	private final long fluentTimeout = Long.parseLong(ConfigReader.get("fluentTimeout"));
	private final long pollingInterval = Long.parseLong(ConfigReader.get("pollingInterval"));
	private static final Logger logger = LoggerUtil.getLogger(ElementActionUtils.class);
	
    /**
     * Initializes the utility with a WebDriver instance and custom wait handler.
     *
     * @param driver the WebDriver instance used for element interactions
     */
	public ElementActionUtils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WaitUtil(driver);
	}
	
    /**
     * Waits for an element to be clickable using an explicit timeout and clicks it.
     *
     * @param locator the locator of the element to click
     */
	public void clickElement(By locator) {
		try {
			WebElement element = wait.waitForElementClickable(locator, clickableTimeout);
			element.click();
			logger.info("Clicked on element: {}", locator.toString());
			
		} catch (Exception e ) {
			logger.error("Failed to click on element: {}", locator.toString(), e);
			throw e;
		}
	}
	
    /**
     * Clicks an element using fluent wait with configurable timeout and polling interval.
     *
     * @param locator the locator of the element to click
     */
	public void clickFluentElement(By locator) {
		try {
			WebElement element = wait.fluentWait(locator, fluentTimeout, pollingInterval);
			element.click();
			logger.info("Clicked on element: " + locator.toString());
			
		} catch (Exception e ) {
			logger.error("Failed to click on element: " + locator.toString());
			throw e;
		}
	}
	
    /**
     * Waits for an element to be visible and sends the provided text.
     *
     * @param locator the locator of the input element
     * @param text the text value to enter
     */
	public void inputElement(By locator, String text) {
		try {
			WebElement element = wait.waitForElementVisible(locator, visibilityTimeout);
			element.sendKeys(text);
			logger.info("Entered text in element: " + locator.toString() + " - Text: " + text);
			
		} catch (Exception e) {
			logger.error("Failed to enter text in element: " + locator.toString() + " - Text: " + text);
			throw e;
		}
	}
	
    /**
     * Verifies that an element is visible on the page.
     *
     * @param locator the locator of the element to verify
     */
	public void verifyDisplayed(By locator) {
		try {
			WebElement element = wait.waitForElementVisible(locator, visibilityTimeout);
			element.isDisplayed();
			logger.info("Element is displayed: " + locator.toString());
			
		} catch (Exception e) {
			logger.error("Element is not displayed: " + locator.toString());
			throw e;
		}
	}
	
    /**
     * Retrieves the text of an element and compares it with the expected value.
     *
     * @param locator the locator of the element
     * @param expectedText the expected text value
     */
	public void getTextAndCompare(By locator, String expectedText) {
		try {
			WebElement element = wait.waitForElementVisible(locator, visibilityTimeout);
			if (element.getText().equals(expectedText)) {
				logger.info("Text matches expected: " + expectedText);
			} else {
				logger.error("Text does not match expected. Found: " + element.getText() + ", Expected: " + expectedText);
				throw new AssertionError("Text does not match expected.");
			}
		} catch (Exception e) {
			logger.error("Failed to get text or compare: " + locator.toString());
			throw e;
		}
	}
}