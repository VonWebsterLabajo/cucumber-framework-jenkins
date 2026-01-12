package com.cheq.demo_webshop.factory;

import com.cheq.demo_webshop.strategy.UiElementStrategies.RadioButtonStrategy;
import com.cheq.demo_webshop.strategy.radio_button.IdStrategy;
import com.cheq.demo_webshop.strategy.radio_button.NameStrategy;
import com.cheq.demo_webshop.strategy.radio_button.ValueStrategy;

/**
* Factory class responsible for providing the appropriate radio button
* selection strategy based on the given locator type.
*/
public class RadioButtonFactory {

    /**
    * Returns a radio button selection strategy based on the provided locator type.
    *
    * @param type the locator type such as id, name, or value
    * @return the corresponding RadioButtonStrategy implementation
    * @throws IllegalArgumentException if the locator type is unsupported
    */
	public static RadioButtonStrategy getRadioButtonStrategy(String type) {
		switch(type.toLowerCase()) {
		case "id": return new IdStrategy();
		case "name": return new NameStrategy();
		case "value": return new ValueStrategy();
		default: throw new IllegalArgumentException("Unsupported locator type");
		}
	}
}