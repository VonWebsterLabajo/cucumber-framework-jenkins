package com.cheq.demo_webshop.strategy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
* Container class that defines common UI element strategy interfaces
* used for locating and interacting with web elements.
*/
public class UiElementStrategies {

    /**
    * Strategy interface for locating text-based labels or elements.
    */
	public interface TextLabelStrategy{
        /**
        * Builds and returns a locator for a text label element.
        *
        * @param tag the HTML tag to locate
        * @param value the text value used for matching
        * @return the constructed By locator
        */
		By getLocator(String tag, String value);
	}

    /**
    * Strategy interface for handling dropdown selection behavior.
    */
	public interface DropdownStrategy{
        /**
        * Selects a value from the given dropdown element.
        *
        * @param dropdown the dropdown WebElement
        * @param value the value to select
        */
		void select(WebElement dropdown, String value);
	}

    /**
    * Strategy interface for locating radio button elements.
    */
	public interface RadioButtonStrategy{
        /**
        * Builds and returns a locator for a radio button.
        *
        * @param value the identifying value of the radio button
        * @return the constructed By locator
        */
		By getLocator(String value);
	}

}