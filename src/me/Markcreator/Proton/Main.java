package me.Markcreator.Proton;

import java.awt.Window.Type;

import com.sun.glass.ui.Screen;

import me.Markcreator.Proton.event.Event;
import me.Markcreator.Proton.event.EventHandler;
import me.Markcreator.Proton.event.EventListener;
import me.Markcreator.Proton.event.events.ViewManagerLoadedEvent;
import me.Markcreator.Proton.event.events.WebPageLoadedEvent;

@SuppressWarnings("restriction")
public class Main {

	public static void main(String[] args) {
		// Create the view manager to handle the UI thread
		ViewManager.loadViewManager();

		// Wait for the view manager to initialize
		new EventListener(ViewManager.getInstance()).awaitEventOnce(ViewManagerLoadedEvent.class);

		// Do things!
		String url = Main.class.getResource("example/dontsnack.html").toExternalForm(); // Get local file path
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

			pane.setLocation(screenWidth - pane.getWidth(), screenHeight - pane.getHeight());

			// Display UI
			pane.setVisible(true);
		});

		// On page load
		new EventListener(pane).onEvent(new EventHandler(WebPageLoadedEvent.class) {
			public void handle(Event event) {
				System.out.println("Page loaded");

				pane.change(() -> {
					// Do things to page
					//pane.getWebEngine().executeScript("document.getElementById('a').style.color = 'green';");
				});
			}
		});
	}
}