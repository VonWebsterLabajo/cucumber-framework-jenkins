package com.cheq.demo_webshop.strategy.dropdown;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.cheq.demo_webshop.strategy.UiElementStrategies;
import com.cheq.demo_webshop.strategy.UiElementStrategies.DropdownStrategy;

/**
* Dropdown strategy that selects an option using the option value attribute.
*/
public class SelectByValueStrategy implements DropdownStrategy {
	
    /**
    * Selects a dropdown option based on its value attribute.
    *
    * @param dropdown the dropdown WebElement
    * @param value the value attribute of the option to select
    */
	 public void select(WebElement dropdown, String value) {
       new Select(dropdown).selectByValue(value);
    }
}