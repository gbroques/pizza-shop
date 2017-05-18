package app.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Pizza {
	
	protected IntegerProperty slices;
	protected DoubleProperty price;
	private static final int TOTAL_SLICES = 8;

	public Pizza() {
		this.slices = new SimpleIntegerProperty(0);
		this.price = new SimpleDoubleProperty(0.0);
	}
	
	public IntegerProperty slices() {
		return slices;
	}
	
	public DoubleProperty price() {
		return price;
	}
	
	public static int getTotalSlices() {
		return TOTAL_SLICES;
	}
	
	public void setSlices(int slices) {
		this.slices.set(slices);
	}

}
