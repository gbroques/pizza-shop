package app;

import java.io.IOException;

import app.model.CheesePizza;
import app.model.PepperoniPizza;
import app.model.VeggiePizza;
import app.view.StoreController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private CheesePizza cheesePizza;
    private VeggiePizza veggiePizza;
    private PepperoniPizza pepperoniPizza;
    
    public Main() {
    	cheesePizza = new CheesePizza();
    	veggiePizza = new VeggiePizza();
    	pepperoniPizza = new PepperoniPizza();
    }
    
    public CheesePizza getCheesePizza() {
    	return cheesePizza;
    }
    
    public VeggiePizza getVeggiePizza() {
    	return veggiePizza;
    }
    
    public PepperoniPizza getPepperoniPizza() {
    	return pepperoniPizza;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Pablo's Pizza");

        initRootLayout();

        showStore();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the store inside the root layout.
     */
    public void showStore() {
        try {
            // Load store.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Store.fxml"));
            AnchorPane store = (AnchorPane) loader.load();
            
            // Give the controller access to the main app.
            StoreController controller = loader.getController();
            controller.setMainApp(this);

            // Set store into the center of root layout.
            rootLayout.setCenter(store);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
