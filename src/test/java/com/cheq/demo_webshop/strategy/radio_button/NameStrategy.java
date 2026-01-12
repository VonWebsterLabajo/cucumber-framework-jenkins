package com.cheq.demo_webshop.strategy.radio_button;

import org.openqa.selenium.By;

import com.cheq.demo_webshop.strategy.UiElementStrategies.RadioButtonStrategy;

/**
 * Radio button strategy that locates elements using the name attribute.
 */
public class NameStrategy implements RadioButtonStrategy {
	
    /**
     * Returns a By locator using the name attribute.
     *
     * @param locator the name of the radio button element
     * @return By locator for the radio button
     */
	public By getLocator(String locator) {
		return By.name(locator);
	}	
}