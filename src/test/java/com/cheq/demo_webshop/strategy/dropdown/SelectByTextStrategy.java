package com.cheq.demo_webshop.strategy.dropdown;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.cheq.demo_webshop.strategy.UiElementStrategies.DropdownStrategy;

/**
* Dropdown strategy that selects an option using visible text.
*/
public class SelectByTextStrategy implements DropdownStrategy {
	
    /**
    * Selects a dropdown option based on its visible text.
    *
    * @param dropdown the dropdown WebElement
    * @param value the visible text of the option to select
    */
	public void select(WebElement dropdown, String value) {
		new Select(dropdown).selectByVisibleText(value);
	}
}