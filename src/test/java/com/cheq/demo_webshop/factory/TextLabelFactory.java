package com.cheq.demo_webshop.factory;

import com.cheq.demo_webshop.strategy.UiElementStrategies.TextLabelStrategy;
import com.cheq.demo_webshop.strategy.text_label.ContainsStrategy;
import com.cheq.demo_webshop.strategy.text_label.TextStrategy;

/**
* Factory class responsible for providing the appropriate text label
* matching strategy based on the given type.
*/
public class TextLabelFactory {
	
    /**
    * Returns a text label matching strategy based on the provided type.
    *
    * @param type the matching type such as text or contains
    * @return the corresponding TextLabelStrategy implementation
    * @throws IllegalArgumentException if the matching type is unsupported
    */
	public static TextLabelStrategy getStrategy(String type) {
		switch(type.toLowerCase()) {
			case "text": return new TextStrategy();
			case "contains": return new ContainsStrategy();
			default: throw new IllegalArgumentException("Unsupported locator type");
		}
	}
}