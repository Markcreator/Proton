package me.Markcreator.Proton;

import java.awt.Window.Type;

import com.sun.glass.ui.Screen;

import me.Markcreator.Proton.event.EventListener;
import me.Markcreator.Proton.event.events.ViewManagerLoadedEvent;
import me.Markcreator.Proton.event.events.WebPageLoadedEvent;

@SuppressWarnings("restriction")
public class Main {

	public static void main(String[] args) {
		// Create the view manager to handle the UI thread
		ViewManager.loadViewManager();
		
		// Wait for the view manager to initialize
		new EventListener(ViewManager.getInstance(), ViewManagerLoadedEvent.class).awaitEventOnce();
		
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
		
		// Wait for page to load
		new EventListener(pane, WebPageLoadedEvent.class).awaitEventOnce();
		
		pane.change(() -> {
			// Do things to page
		});
	}
}