package me.Markcreator.Proton;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

@SuppressWarnings("serial")
public class WebPane extends JFrame {

	private WebView browser;
	private WebEngine webEngine;

	public WebView getWebView() {
		return browser;
	}

	public WebEngine getWebEngine() {
		return webEngine;
	}

	public WebPane() {
		change(() -> {
			browser = new WebView();
			webEngine = browser.getEngine();
			
			// JavaFX in Swing
			JFXPanel fxPanel = new JFXPanel();
			getContentPane().add(fxPanel);
			
			fxPanel.setScene(new Scene(browser));
		});
	}

	public WebPane(String url) {
		this();

		change(() -> {
			getWebEngine().load(url);
		});
	}

	// Run UI changes on the UI thread
	public void change(Runnable r) {
		Platform.runLater(r);
	}
}

// https://stackoverflow.com/questions/9861178/javafx-primarystage-remove-windows-borders
// http://docs.oracle.com/javafx/2/webview/jfxpub-webview.htm