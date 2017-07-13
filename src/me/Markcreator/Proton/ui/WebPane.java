package me.Markcreator.Proton.ui;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import me.Markcreator.Proton.event.EventCaller;
import me.Markcreator.Proton.event.events.WebPageLoadedEvent;

@SuppressWarnings("serial")
public class WebPane extends JFrame implements EventCaller {

	private WebView browser;
	private WebEngine webEngine;

	public WebPane() {
		change(() -> {
			browser = new WebView();
			webEngine = browser.getEngine();
			
			// JavaFX in Swing
			JFXPanel fxPanel = new JFXPanel();
			getContentPane().add(fxPanel);
			
			fxPanel.setScene(new Scene(browser));
			loadLayout(WebPaneLayout.DEFAULT); // Load default layout
			
			registerEvents();
		});
	}

	public WebPane(String url) {
		this();

		change(() -> {
			getWebEngine().load(url);
		});
	}
	
	public WebView getWebView() {
		return browser;
	}

	public WebEngine getWebEngine() {
		return webEngine;
	}
	
	public void loadLayout(WebPaneLayout layout) {		
		layout.build(this);
	}
	
	public void registerEvents() {
		getWebEngine().getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
		    if (newState == State.SUCCEEDED) {
		        callEvent(new WebPageLoadedEvent(this));
		    }
		});
	}
	
	// Run UI changes on the UI thread
	public void change(Runnable r) {
		Platform.runLater(r);
	}
}

// https://stackoverflow.com/questions/9861178/javafx-primarystage-remove-windows-borders
// http://docs.oracle.com/javafx/2/webview/jfxpub-webview.htm