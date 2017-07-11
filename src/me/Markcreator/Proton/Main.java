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
		String url = Main.class.getResource("html/test.html").toExternalForm(); // Get local file path
		WebPane pane = new WebPane(url); // New web page pane
	}
}