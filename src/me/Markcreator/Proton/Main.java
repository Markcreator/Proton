package me.Markcreator.Proton;

import java.awt.Window.Type;

import com.sun.glass.ui.Screen;

@SuppressWarnings("restriction")
public class Main {

	public static void main(String[] args) {
		// Start the view manager to handle the UI thread
		ViewManager.loadViewManager();
		
		// Wait for the view manager to initialize
		while(ViewManager.getPrimaryStage() == null) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) { }
		}
		
		// Do things!
		String url = Main.class.getResource("html/dontsnack.html").toExternalForm(); // Get local file path
		WebPane pane = new WebPane(url); // New web page pane
		
		pane.change(() -> {
			// Config
			pane.setSize(400, 75);
			pane.setType(Type.UTILITY);
			pane.setUndecorated(true);
			pane.setAlwaysOnTop(true);
			
			pane.getWebView().setContextMenuEnabled(false);
			
			// Location
			int screenHeight = Screen.getMainScreen().getVisibleHeight();
			int screenWidth = Screen.getMainScreen().getVisibleWidth();
			
			pane.setLocation(screenWidth-pane.getWidth(), screenHeight-pane.getHeight());
			
			// Display UI
			pane.setVisible(true);
		});
	}
}