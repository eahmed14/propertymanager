/*
 * Class: CMSC203 
 * Instructor: 
 * Description: JavaFX Graphical User Interface for the Property Management Application.
 * Due: 11/10/2026
 * Platform/compiler: Eclipse / Java
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print Your Name Here: Ezedin
*/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PropertyAppFX extends Application {
    private ManagementCompany mc = new ManagementCompany("Campus Realty", "123-45-6789", 5.0);

    private TextField nameField = new TextField();
    private TextField cityField = new TextField();
    private TextField rentField = new TextField();
    private TextField ownerField = new TextField();
    private TextArea outputArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Property Management");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        grid.add(new Label("Property Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("City:"), 0, 1);
        grid.add(cityField, 1, 1);
        grid.add(new Label("Rent:"), 0, 2);
        grid.add(rentField, 1, 2);
        grid.add(new Label("Owner:"), 0, 3);
        grid.add(ownerField, 1, 3);

        Button addBtn = new Button("Add Property");
        Button showAllBtn = new Button("Show All Properties");
        Button showTotalBtn = new Button("Show Total Rent");

        GridPane btnPane = new GridPane();
        btnPane.setHgap(10);
        btnPane.add(addBtn, 0, 0);
        btnPane.add(showAllBtn, 1, 0);
        btnPane.add(showTotalBtn, 2, 0);

        grid.add(btnPane, 0, 4, 2, 1);

        outputArea.setPrefRowCount(8);
        outputArea.setEditable(false);

        VBox layout = new VBox(10, grid, outputArea);
        layout.setPadding(new Insets(10));

        addBtn.setOnAction(e -> handleAddProperty());
        showAllBtn.setOnAction(e -> outputArea.appendText(mc.toString() + "\n"));
        showTotalBtn.setOnAction(e -> outputArea.appendText("Total rent: $" + mc.totalRent() + "\n"));

        primaryStage.setScene(new Scene(layout, 500, 400));
        primaryStage.show();
    }

    private void handleAddProperty() {
        try {
            String name = nameField.getText();
            String city = cityField.getText();
            double rent = Double.parseDouble(rentField.getText());
            String owner = ownerField.getText();

            int index = mc.addProperty(new Property(name, city, rent, owner));
            if (index >= 0) {
                outputArea.appendText("Property added at index " + index + ".\n");
            } else {
                outputArea.appendText("Failed to add property. Code: " + index + "\n");
            }
        } catch (NumberFormatException ex) {
            outputArea.appendText("Invalid rent value.\n");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
