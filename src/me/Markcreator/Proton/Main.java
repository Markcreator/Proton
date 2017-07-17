package me.Markcreator.Proton;

import com.sun.glass.ui.Screen;

import me.Markcreator.Proton.event.Event;
import me.Markcreator.Proton.event.EventHandler;
import me.Markcreator.Proton.event.events.WebPageLoadedEvent;
import me.Markcreator.Proton.ui.WebPane;
import me.Markcreator.Proton.ui.layout.WebPaneLayout;
import me.Markcreator.Proton.util.FileUtils;

@SuppressWarnings("restriction")
public class Main {

	public static void main(String[] args) {
		String url = FileUtils.getResourcePath(Main.class, "examples/test.html"); // Get local file path
		WebPane pane = new WebPane(url); // New web page pane
		
		// Load UI look
		pane.loadLayout(WebPaneLayout.POPUP);
		pane.setSize(400, 75);
		
		// Display UI
		pane.setVisible(true);
		
		// Location
		int screenHeight = Screen.getMainScreen().getVisibleHeight();
		int screenWidth = Screen.getMainScreen().getVisibleWidth();
		//int screenHeight = MouseInfo.getPointerInfo().getLocation().y;
		//int screenWidth = MouseInfo.getPointerInfo().getLocation().x;
		
		pane.setLocation(screenWidth - pane.getWidth(), screenHeight - pane.getHeight());

		// On page load
		new EventHandler(pane) {
			public void handle(Event event) {
				if(event instanceof WebPageLoadedEvent) {
					WebPageLoadedEvent e = (WebPageLoadedEvent) event;
					WebPane pane = (WebPane) e.getCaller();
					
					System.out.println("Page loaded");

					pane.change(() -> {
						// Do things to page
						//pane.getWebEngine().executeScript("document.getElementById('a').style.color = 'green';");
					});
				}
			}
		};
	}
}