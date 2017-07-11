package me.Markcreator.Proton;

import javafx.application.Application;
import javafx.stage.Stage;
import me.Markcreator.Proton.event.EventCaller;
import me.Markcreator.Proton.event.events.ViewManagerLoadedEvent;

public class ViewManager extends Application implements EventCaller {
	
	private static ViewManager singleton = new ViewManager();
	
	public static ViewManager getInstance() {
		return singleton;
	}
	
	public static void loadViewManager() {
		// Start JavaFX
		Thread t = new Thread(() -> {
			Application.launch();
		});
		t.start();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        callEvent(new ViewManagerLoadedEvent(this));
	}
}
