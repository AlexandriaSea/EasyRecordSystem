package application;
      
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {
		
	@Override
    public void start(Stage primaryStage) 
    {
    	SceneDesign.getMainScene();
		primaryStage.setScene(SceneDesign.scene);
		primaryStage.setTitle("EasyRecord System - Main Page");
		
		ApplyAction.addButtonAction();
		ApplyAction.searchButtonAction();
		ApplyAction.updateButtonAction();
		ApplyAction.displayButtonAction();
    	
    	primaryStage.show();
    	
    	SceneDesign.entryPageButton.setOnAction(e->{
			try
			{
				SceneDesign.getEntryScene();
				primaryStage.setScene(SceneDesign.scene);
				primaryStage.setTitle("EasyRecord System - Entry Page");
			}
			catch(Exception entryPageException)
			{
				entryPageException.printStackTrace();
			}
		});
    	
    	SceneDesign.displayPageButton.setOnAction(e->{
			try
			{
				SceneDesign.getDisplayScene();
				primaryStage.setScene(SceneDesign.scene);
				primaryStage.setTitle("EasyRecord System - Display Page");
			}
			catch(Exception entryPageException)
			{
				entryPageException.printStackTrace();
			}
		});
    	
    	SceneDesign.ExitButton.setOnAction(e->{
			try
			{
				Platform.exit();
			}
			catch(Exception exitException)
			{
				exitException.printStackTrace();
			}
		});
    	
    	SceneDesign.entryExitButton.setOnAction(e->{
			try
			{
				SceneDesign.getMainScene();
				primaryStage.setScene(SceneDesign.scene);
				primaryStage.setTitle("EasyRecord System - Main Page");
			}
			catch(Exception entryPageException)
			{
				entryPageException.printStackTrace();
			}
		});
    	
    	SceneDesign.displayExitButton.setOnAction(e->{
			try
			{
				SceneDesign.getMainScene();
				primaryStage.setScene(SceneDesign.scene);
				primaryStage.setTitle("EasyRecord System - Main Page");
			}
			catch(Exception entryPageException)
			{
				entryPageException.printStackTrace();
			}
		});
    }       
    
    public static void main(String[] args)
    {
            Main.launch(args);
    }     
}