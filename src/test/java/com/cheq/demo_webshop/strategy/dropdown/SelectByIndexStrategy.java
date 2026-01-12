package com.cheq.demo_webshop.strategy.dropdown;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.cheq.demo_webshop.strategy.UiElementStrategies.DropdownStrategy;

/**
* Dropdown strategy that selects an option using its index position.
*/
public class SelectByIndexStrategy implements DropdownStrategy {
	
    /**
    * Selects a dropdown option based on the given index value.
    *
    * @param dropdown the dropdown WebElement
    * @param value the index of the option to select, provided as a String
    */
    public void select(WebElement dropdown, String value) {
    	int index = Integer.parseInt(value);
        new Select(dropdown).selectByIndex(index);
    }
}