package me.Markcreator.Proton;

public class Main {

	public static void main(String[] args) {
		// Start the generic view manager to handle newly made stages
		ViewManager.loadViewManager();
		
		// Wait for the view manager to initialize
		while(ViewManager.getPrimaryStage() == null) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) { }
		}
		
		// Do things!
		WebPane pane = new WebPane(); // New web page pane
		pane.change(() -> { // Change the pane on the UI thread
			pane.getWebEngine().loadContent(FileUtils.readFile("test.html")); // Load html page into the pane
		});
	}
}