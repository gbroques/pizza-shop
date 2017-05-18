package app.model;

import javafx.beans.property.SimpleDoubleProperty;

public class PepperoniPizza extends Pizza {

	public PepperoniPizza() {
		this.price = new SimpleDoubleProperty(2.25);
	}

}
