package com.cheq.demo_webshop.strategy.radio_button;

import org.openqa.selenium.By;

import com.cheq.demo_webshop.strategy.UiElementStrategies.RadioButtonStrategy;

/**
* Radio button strategy that locates elements using the ID attribute.
*/
public class IdStrategy implements RadioButtonStrategy {

    /**
    * Returns a By locator using the ID attribute.
    *
    * @param locator the ID of the radio button element
    * @return By locator for the radio button
    */
	public By getLocator(String locator) {
		return By.id(locator);
	}
}