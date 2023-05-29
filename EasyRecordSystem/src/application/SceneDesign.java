package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class SceneDesign {
	
	protected static Scene scene;
	
	// Page Change Buttons
	protected static Button entryPageButton = new Button("Entry Page");
	protected static Button displayPageButton = new Button("Display Page");
	protected static Button entryExitButton = new Button("Back to Main Page");
	protected static Button displayExitButton = new Button("Back to Main Page");
	protected static Button ExitButton = new Button("Exit System");
	
	// Entry Action Buttons
	protected static Button addButton = new Button("Add");
	protected static Button searchButton = new Button("Search");
	protected static Button updateButton = new Button("Update");
	
	// Entry Page Fields
	protected static TextField idField = new TextField(); 
	protected static TextField dateField = new TextField(); 
	protected static ComboBox<String> categoryComboBox;
	protected static TextField eventField = new TextField(); 
	protected static TextField accountField = new TextField(); 
	protected static TextField amountField = new TextField(); 
	
	// Display Action Buttons 
	protected static Button displayButton = new Button("Display Record");
	
	// Display Page Fields
	protected static TextField yearField = new TextField();
	protected static ComboBox<String> monthComboBox;
	protected static CheckBox yearCheckBox = new CheckBox("Yearly Sum");
	protected static CheckBox monthCheckBox = new CheckBox("Monthly Sum");
	
	public static void getMainScene()
	{
		HBox hbox = new HBox(25);
		hbox.getChildren().addAll(entryPageButton, displayPageButton, ExitButton);
		hbox.setAlignment(Pos.CENTER);
		
		scene = new Scene(hbox, 854, 480);
	}
	
	public static void getEntryScene()
	{
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setMinSize(854, 480);
		
		Text idText = new Text("ID");
		Text dateText = new Text("Date");
		Text categoryText = new Text("Category");
		Text eventText = new Text("Event Detail");
		Text accountText = new Text("Bank Account");
		Text amountText = new Text("Payment Amount");
		
		addButton.setPrefSize(150, 25);
		searchButton.setPrefSize(150, 25);
		updateButton.setPrefSize(150, 25);
		entryExitButton.setPrefSize(150, 25);
		
		ObservableList<String> categories = FXCollections.observableArrayList("Entertainment", "Grocery", "Housing", "Meal", "Professional", "Personal", "Transportation");
		categoryComboBox = new ComboBox<String>(categories);
		
		gridPane.add(idText, 0, 12);
		gridPane.add(idField, 0, 13);
		gridPane.add(dateText, 1, 12);
		gridPane.add(dateField, 1, 13);
		gridPane.add(categoryText, 2, 12);
		gridPane.add(categoryComboBox, 2, 13);
		gridPane.add(eventText, 3, 12);
		gridPane.add(eventField, 3, 13);
		gridPane.add(accountText, 4, 12);
		gridPane.add(accountField, 4, 13);
		gridPane.add(amountText, 5, 12);
		gridPane.add(amountField, 5, 13);
		gridPane.add(addButton, 1, 25);
		gridPane.add(searchButton, 2, 25);
		gridPane.add(updateButton, 3, 25);
		gridPane.add(entryExitButton, 4, 25);
		

		scene = new Scene(gridPane);
	}
	
	public static void getDisplayScene()
	{
		HBox hbox = new HBox(25);
		Text yearText = new Text("Year:");
		Text monthText = new Text("Month:");
		ObservableList<String> months = FXCollections.observableArrayList("All", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
		monthComboBox = new ComboBox<String>(months);
		monthComboBox.setValue("All");
		
		hbox.getChildren().addAll(yearText, yearField, monthText, monthComboBox, yearCheckBox, monthCheckBox, displayButton, displayExitButton);
		hbox.setAlignment(Pos.CENTER);
		
		scene = new Scene(hbox, 854, 480);
	}
}
