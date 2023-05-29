package application;

import java.awt.Dimension;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javafx.embed.swing.SwingNode;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
 
public class ApplyAction {
	
	public static void addButtonAction()
	{
		SceneDesign.addButton.setOnAction(e->{
			try
			{
				Database.getConnection();
				
				CallableStatement addNewEntry = Database.connetion.prepareCall("{call easyrecord_pkg.add_entry_pp(?, ?, ?, ?, ?)}");
				addNewEntry.setDate(1, java.sql.Date.valueOf(SceneDesign.dateField.getText()));
				addNewEntry.setString(2, SceneDesign.categoryComboBox.getSelectionModel().getSelectedItem());
				addNewEntry.setString(3, SceneDesign.eventField.getText());
				addNewEntry.setString(4, SceneDesign.accountField.getText());
				addNewEntry.setDouble(5, Double.parseDouble(SceneDesign.amountField.getText()));
				
				addNewEntry.executeUpdate();
				addNewEntry.close();
				
				SceneDesign.dateField.clear();
				SceneDesign.categoryComboBox.setValue(null);
				SceneDesign.eventField.clear();
				SceneDesign.accountField.clear();
				SceneDesign.amountField.clear();
				
				Database.disConnect();
			}
			catch(Exception addException)
			{
				addException.printStackTrace();
			}
		});
	}
	
	public static void searchButtonAction()
	{
		SceneDesign.searchButton.setOnAction(e->{
			try
			{
				Database.getConnection();
				
				SceneDesign.categoryComboBox.setValue(null);
				
				CallableStatement searchOldEntry = Database.connetion.prepareCall("{call easyrecord_pkg.search_entry_pp(?, ?, ?, ?, ?, ?)}");
				searchOldEntry.setInt(1, Integer.parseInt(SceneDesign.idField.getText()));
				
				searchOldEntry.registerOutParameter(2, Types.DATE);
				searchOldEntry.registerOutParameter(3, Types.VARCHAR);
				searchOldEntry.registerOutParameter(4, Types.VARCHAR);
				searchOldEntry.registerOutParameter(5, Types.VARCHAR);
				searchOldEntry.registerOutParameter(6, Types.DOUBLE);
				
				searchOldEntry.executeUpdate();
				
				String date = String.valueOf(searchOldEntry.getDate(2));
				String category = searchOldEntry.getString(3); 
				String event = searchOldEntry.getString(4);
				String account = searchOldEntry.getString(5); 
				String amount = String.valueOf(searchOldEntry.getDouble(6)); 
				
				SceneDesign.dateField.setText(date);
				SceneDesign.categoryComboBox.setValue(category);
				SceneDesign.eventField.setText(event);
				SceneDesign.accountField.setText(account);
				SceneDesign.amountField.setText(amount);
				
				searchOldEntry.close();
				Database.disConnect();
			}
			catch(Exception searchEntry)
			{
				searchEntry.printStackTrace();
			}
		});
	}
	
	public static void updateButtonAction()
	{
		SceneDesign.updateButton.setOnAction(e->{
			try
			{
				Database.getConnection();
				
				CallableStatement updateOldEntry = Database.connetion.prepareCall("{call easyrecord_pkg.update_entry_pp(?, ?, ?, ?, ?, ?)}");
				updateOldEntry.setInt(1, Integer.parseInt(SceneDesign.idField.getText()));
				updateOldEntry.setDate(2, java.sql.Date.valueOf(SceneDesign.dateField.getText()));
				updateOldEntry.setString(3, SceneDesign.categoryComboBox.getSelectionModel().getSelectedItem());
				updateOldEntry.setString(4, SceneDesign.eventField.getText());
				updateOldEntry.setString(5, SceneDesign.accountField.getText());
				updateOldEntry.setDouble(6, Double.parseDouble(SceneDesign.amountField.getText()));
				
				updateOldEntry.executeUpdate();
				updateOldEntry.close();
				
				SceneDesign.idField.clear();
				SceneDesign.dateField.clear();
				SceneDesign.categoryComboBox.setValue(null);
				SceneDesign.eventField.clear();
				SceneDesign.accountField.clear();
				SceneDesign.amountField.clear();
				
				Database.disConnect();
			}
			catch(Exception updateEntry)
			{
				updateEntry.printStackTrace();
			}
		});
	}
	
	public static void displayButtonAction()
	{
		SceneDesign.displayButton.setOnAction(e->{
			try
			{
				Database.getConnection();
				
				Statement displayEntry = Database.connetion.createStatement(); 
				JTable displayTable = new JTable();
				DefaultTableModel model = new DefaultTableModel();
				SwingNode swingNode = new SwingNode();
				
				String year = SceneDesign.yearField.getText();
				String selectedOption = SceneDesign.monthComboBox.getSelectionModel().getSelectedItem();
				
				if(SceneDesign.yearCheckBox.isSelected())
				{
					String query = "SELECT TO_CHAR(entry_date, 'YYYY') AS year, SUM(pay_amount) AS sum FROM er_entry GROUP BY TO_CHAR(entry_date, 'YYYY')";
					ResultSet displayResult = displayEntry.executeQuery(query);
					model.addColumn("Year");
					model.addColumn("Sum");
					while (displayResult.next())
					{
						Object[] row = new Object[2];
						row[0] = displayResult.getString("year");
						row[1] = displayResult.getDouble("sum");
						model.addRow(row);
					}
					displayResult.close();
				}
				else
				{
					if(SceneDesign.monthCheckBox.isSelected())
					{
						String query = "SELECT TO_CHAR(entry_date, 'MM') AS month, SUM(pay_amount) AS sum FROM er_entry WHERE TO_CHAR(entry_date, 'YYYY') = " + year + " GROUP BY TO_CHAR(entry_date, 'MM')";
						ResultSet displayResult = displayEntry.executeQuery(query);
						model.addColumn("Month");
						model.addColumn("Sum");
						while (displayResult.next())
						{
							Object[] row = new Object[2];
							row[0] = displayResult.getString("month");
							row[1] = displayResult.getDouble("sum");
							model.addRow(row);
						}
						displayResult.close();
					}
					else
					{
						if(selectedOption == "All")
						{
							String query = "SELECT entry_id, entry_date, category_type, event_detail, pay_account, pay_amount FROM er_entry WHERE TO_CHAR(entry_date, 'YYYY') = " + year;
							ResultSet displayResult = displayEntry.executeQuery(query);
							model.addColumn("ID");
							model.addColumn("Date");
							model.addColumn("Category");
							model.addColumn("Event");
							model.addColumn("Account");
							model.addColumn("Amount");
							while (displayResult.next())
							{
								Object[] row = new Object[6];
								row[0] = displayResult.getInt("entry_id");
								row[1] = displayResult.getDate("entry_date");
								row[2] = displayResult.getString("category_type");
								row[3] = displayResult.getString("event_detail");
								row[4] = displayResult.getString("pay_account");
								row[5] = displayResult.getDouble("pay_amount");
								model.addRow(row);
							}
							displayResult.close();
						}
						else
						{
							String query = "SELECT entry_id, entry_date, category_type, event_detail, pay_account, pay_amount FROM er_entry WHERE TO_CHAR(entry_date, 'YYYY') = " + year + " AND TO_CHAR(entry_date, 'MM') = " + selectedOption;
							ResultSet displayResult = displayEntry.executeQuery(query);
							model.addColumn("ID");
							model.addColumn("Date");
							model.addColumn("Category");
							model.addColumn("Event");
							model.addColumn("Account");
							model.addColumn("Amount");
							while (displayResult.next())
							{
								Object[] row = new Object[6];
								row[0] = displayResult.getInt("entry_id");
								row[1] = displayResult.getDate("entry_date");
								row[2] = displayResult.getString("category_type");
								row[3] = displayResult.getString("event_detail");
								row[4] = displayResult.getString("pay_account");
								row[5] = displayResult.getDouble("pay_amount");
								model.addRow(row);
							}
							displayResult.close();
						}
					}
				}
									
				displayTable.setModel(model);
				JScrollPane scrollPane = new JScrollPane(displayTable);
				scrollPane.setPreferredSize(new Dimension(854, 480));
				swingNode.setContent(scrollPane);
				displayEntry.close();
				
				Database.disConnect();
				
				Scene displayScene = new Scene(new Group(swingNode), 854, 480);
				Stage displayStage = new Stage();
				displayStage.setScene(displayScene);
				displayStage.show();
			}
			catch(Exception displayException)
			{
				displayException.printStackTrace();
				Alert displayAlert = new Alert(Alert.AlertType.ERROR);
				displayAlert.setHeaderText("No Data Found! (Remember to input year value)");
				displayAlert.showAndWait();
			}
		});
	}
}
