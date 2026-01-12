package com.cheq.demo_webshop.strategy.text_label;

import org.openqa.selenium.By;

import com.cheq.demo_webshop.strategy.UiElementStrategies.TextLabelStrategy;

/**
 * Text label strategy that locates elements with exact text match.
 */
public class TextStrategy implements TextLabelStrategy {
	
    /**
     * Returns a By locator for elements of a given HTML tag that exactly match the specified text.
     *
     * @param htmlTag the HTML tag of the element (e.g., "h2", "span")
     * @param locatorValue the exact text to locate within the element
     * @return By locator for the element with exact text match
     */
	public By getLocator(String htmlTag, String locatorValue) {
	        return By.xpath("//"+ htmlTag +"[text()='" + locatorValue + "']");
    }
}