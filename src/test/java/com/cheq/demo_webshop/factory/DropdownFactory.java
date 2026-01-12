package com.cheq.demo_webshop.factory;

import com.cheq.demo_webshop.strategy.UiElementStrategies.DropdownStrategy;
import com.cheq.demo_webshop.strategy.dropdown.SelectByIndexStrategy;
import com.cheq.demo_webshop.strategy.dropdown.SelectByTextStrategy;
import com.cheq.demo_webshop.strategy.dropdown.SelectByValueStrategy;

/**
* Factory class responsible for providing the appropriate dropdown
* selection strategy based on the given type.
*/
public class DropdownFactory {

    /**
    * Returns a dropdown selection strategy based on the provided type.
    *
    * @param type the dropdown selection type such as text, value, or index
    * @return the corresponding DropdownStrategy implementation
    * @throws IllegalArgumentException if the strategy type is invalid
    */
	public static DropdownStrategy getStrategy(String type) {
		switch (type.toLowerCase()) {
		case "text":
			return new SelectByTextStrategy();
		case "value":
			return new SelectByValueStrategy();
		case "index":
			return new SelectByIndexStrategy();
		default:
			throw new IllegalArgumentException("Invalid dropdown strategy: " + type);
		}
	}
}
 