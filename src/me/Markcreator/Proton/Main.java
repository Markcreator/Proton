package me.Markcreator.Proton;

import com.sun.glass.ui.Screen;

import me.Markcreator.Proton.event.SingleEventWaiter;
import me.Markcreator.Proton.event.Event;
import me.Markcreator.Proton.event.EventHandler;
import me.Markcreator.Proton.event.events.ViewManagerLoadedEvent;
import me.Markcreator.Proton.event.events.WebPageLoadedEvent;
import me.Markcreator.Proton.ui.ViewManager;
import me.Markcreator.Proton.ui.WebPane;
import me.Markcreator.Proton.ui.WebPaneLayout;

@SuppressWarnings("restriction")
public class Main {

	public static void main(String[] args) {
		// Create the view manager to handle the UI thread
		ViewManager.loadViewManager();

		// Wait for the view manager to initialize
		new SingleEventWaiter(ViewManager.getInstance(), ViewManagerLoadedEvent.class);

		// Do things!
		String url = Main.class.getResource("examples/dontsnack.html").toExternalForm(); // Get local file path
		WebPane pane = new WebPane(url); // New web page pane

		pane.change(() -> {
			// Config
			pane.loadLayout(WebPaneLayout.POPUP);
			pane.setSize(400, 75);
			
			pane.getWebView().setContextMenuEnabled(false);
			
			// Location
			int screenHeight = Screen.getMainScreen().getVisibleHeight();
			int screenWidth = Screen.getMainScreen().getVisibleWidth();

			pane.setLocation(screenWidth - pane.getWidth(), screenHeight - pane.getHeight());
			
			// Display UI
			pane.setVisible(true);
		});

		// On page load
		new EventHandler(pane) {
			public void handle(Event event) {
				if(event instanceof WebPageLoadedEvent) {
					WebPageLoadedEvent e = (WebPageLoadedEvent) event;
					WebPane pane = (WebPane) e.getCaller();
					
					System.out.println("Page loaded");

					pane.change(() -> {
						// Do things to page
						pane.getWebEngine().executeScript("document.getElementById('a').style.color = 'green';");
					});
				}
			}
		};
	}
}