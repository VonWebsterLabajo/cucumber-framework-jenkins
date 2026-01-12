package com.cheq.demo_webshop.strategy.text_label;

import org.openqa.selenium.By;

import com.cheq.demo_webshop.strategy.UiElementStrategies.TextLabelStrategy;

/**
 * Text label strategy that locates elements containing the specified text.
 */
public class ContainsStrategy implements TextLabelStrategy {
	
    /**
     * Returns a By locator for elements of a given HTML tag that contain the specified text.
     *
     * @param htmlTag the HTML tag of the element (e.g., "h2", "span")
     * @param locatorValue the partial text to locate within the element
     * @return By locator for the element containing the text
     */
	 public By getLocator(String htmlTag, String locatorValue) {
        return By.xpath("//"+ htmlTag +"[contains(text(),'" + locatorValue + "')]");
	 }
}