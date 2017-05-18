package app.model;

import javafx.beans.property.SimpleDoubleProperty;

public class CheesePizza extends Pizza {

	public CheesePizza() {
		this.price = new SimpleDoubleProperty(1.50);
	}

}
