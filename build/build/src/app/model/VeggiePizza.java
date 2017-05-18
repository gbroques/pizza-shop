package app.model;

import javafx.beans.property.SimpleDoubleProperty;

public class VeggiePizza extends Pizza {

	public VeggiePizza() {
		this.price = new SimpleDoubleProperty(2.00);
	}

}
