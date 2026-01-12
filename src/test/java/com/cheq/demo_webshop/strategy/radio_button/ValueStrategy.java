package com.cheq.demo_webshop.strategy.radio_button;

import org.openqa.selenium.By;

import com.cheq.demo_webshop.strategy.UiElementStrategies.RadioButtonStrategy;

/**
 * Radio button strategy that locates elements based on their value attribute.
 */
public class ValueStrategy implements RadioButtonStrategy {
   
    /**
     * Returns a By locator using the value attribute for radio buttons.
     *
     * @param locatorValue the value of the radio button element
     * @return By locator for the radio button
     */
    public By getLocator(String locatorValue) {
        return By.xpath("//input[@type='radio' and @value='" + locatorValue + "']");
    }
}