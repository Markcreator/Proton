package me.Markcreator.Proton.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import me.Markcreator.Proton.event.EventCaller;
import me.Markcreator.Proton.event.events.WebViewManagerLoadedEvent;

public class WebViewManager extends Application implements EventCaller {
	
	private static WebViewManager singleton = new WebViewManager();
	private static Stage primaryStage = null;
	
	public static WebViewManager getInstance() {
		return singleton;
	}
	
	public static void loadViewManager() {
		// Start JavaFX
		Thread t = new Thread(() -> {
			Application.launch();
		});
		t.start();
	}
	
	public static boolean isLoaded() {
		return primaryStage != null;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		WebViewManager.primaryStage = primaryStage;
		
		callEvent(new WebViewManagerLoadedEvent(this));
	}
}
