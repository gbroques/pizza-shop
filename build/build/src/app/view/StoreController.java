package app.view;

import java.text.NumberFormat;
import java.util.Optional;

import app.Main;
import app.model.CheesePizza;
import app.model.PepperoniPizza;
import app.model.Pizza;
import app.model.VeggiePizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.shape.Arc;

public class StoreController {
	@FXML
	private ChoiceBox<Integer> numCheeseSlices;
	@FXML
	private ChoiceBox<Integer> numVeggieSlices;
	@FXML
	private ChoiceBox<Integer> numPepperoniSlices;
	@FXML
	private Label totalCostLabel;
	@FXML
	private Arc cheeseArc;
	@FXML
	private Arc veggieArc;
	@FXML
	private Arc pepperoniArc;
	
	private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
	public StoreController() {
	}
	
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	int totalSlices = Pizza.getTotalSlices();

    	ObservableList<Integer> slices = FXCollections.observableArrayList();
    	for (int i = 0; i <= totalSlices; i++) {
    		slices.add(i);
    	}
    	
    	numCheeseSlices.setItems(slices);
    	numCheeseSlices.setValue(0);
    	
    	numVeggieSlices.setItems(slices);
    	numVeggieSlices.setValue(0);

    	numPepperoniSlices.setItems(slices);
    	numPepperoniSlices.setValue(0);

    	    	
    	addChangeListeners();
    }
    
    /**
     * Adds change listeners to the choice boxes.
     */
    private void addChangeListeners() {
    	numCheeseSlices.getSelectionModel().selectedItemProperty().addListener(
        		(observable, oldValue, newValue) -> updateStore("cheese", newValue));
    	numVeggieSlices.getSelectionModel().selectedItemProperty().addListener(
        		(observable, oldValue, newValue) -> updateStore("veggie", newValue));
    	numPepperoniSlices.getSelectionModel().selectedItemProperty().addListener(
        		(observable, oldValue, newValue) -> updateStore("pepperoni", newValue));
    }
    
    /**
     * Updates the store with new slices for a pizza.
     * 
     * @param pizza The type of the pizza.
     * @param slices The number of slices.
     */
    private void updateStore(String pizza, int slices) {
    	switch (pizza) {
    		case "cheese":
    			main.getCheesePizza().setSlices(slices);
    			break;
    		case "veggie":
    			main.getVeggiePizza().setSlices(slices);
    			break;
    		case "pepperoni":
    			main.getPepperoniPizza().setSlices(slices);
    			break;
    	}
    	
    	updateArcs();
    	
    	displayTotalCost();
    }
    
    /**
     * Updates the arcs to show how many slices of pizza is gone.
     */
    private void updateArcs() {
    	CheesePizza cheesePizza = main.getCheesePizza();
    	VeggiePizza veggiePizza = main.getVeggiePizza();
    	PepperoniPizza pepperoniPizza = main.getPepperoniPizza();
    	
    	cheeseArc.setLength(cheesePizza.slices().get() * 45.0);
    	veggieArc.setLength(veggiePizza.slices().get() * 45.0);
    	pepperoniArc.setLength(pepperoniPizza.slices().get() * 45.0);
    }
    
    /**
     * Displays the total cost to the user.
     */
    private void displayTotalCost() {
    	NumberFormat formatter = NumberFormat.getCurrencyInstance();
    	totalCostLabel.setText(formatter.format(getTotalCost()));
    }
    
    /**
     * Calculates the total cost.
     * 
     * @return The total cost.
     */
    private double getTotalCost() {
    	double totalCost = 0;
    	CheesePizza cheesePizza = main.getCheesePizza();
    	VeggiePizza veggiePizza = main.getVeggiePizza();
    	PepperoniPizza pepperoniPizza = main.getPepperoniPizza();
    	
    	totalCost += cheesePizza.slices().get() * cheesePizza.price().get();
    	totalCost += veggiePizza.slices().get() * veggiePizza.price().get();
    	totalCost += pepperoniPizza.slices().get() * pepperoniPizza.price().get();

    	return totalCost;
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param main
     */
    public void setMainApp(Main main) {
        this.main = main;
    }
    
    @FXML
    private void handlePlaceOrder() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Thank You");
    	alert.setHeaderText("Your order has successfully been placed.");
    	alert.setContentText("Thanks for shopping at Pablo's!");

    	Optional<ButtonType> result = alert.showAndWait();
    	
    	// If the user closes the window by clicking the red X, then return.
    	if (result.equals(Optional.empty())) return;
    	
    	if (result.get() == ButtonType.OK) {
    		resetStore();
    	}

    }
    
    private void resetStore() {
    	numCheeseSlices.setValue(0);
    	numVeggieSlices.setValue(0);
    	numPepperoniSlices.setValue(0);
    	totalCostLabel.setText("$0.00");
    	cheeseArc.setLength(0.0);
    	veggieArc.setLength(0.0);
    	pepperoniArc.setLength(0.0);
    }
}
