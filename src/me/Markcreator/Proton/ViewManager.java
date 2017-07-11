package me.Markcreator.Proton;

import javafx.application.Application;
import javafx.stage.Stage;

public class ViewManager extends Application {

	private static Stage primaryStage;
	
	public static void loadViewManager() {
		Thread t = new Thread(() -> {
			Application.launch(ViewManager.class);
		});
		t.start();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewManager.primaryStage = primaryStage;
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
}
