package me.Markcreator.Proton;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebPane {

	private Stage stage;
	private WebView browser;
	private WebEngine webEngine;
	
	public Stage getStage() {
		return stage;
	}
	
	public WebView getWebView() {
		return browser;
	}
	
	public WebEngine getWebEngine() {
		return webEngine;
	}
	
	public WebPane() {
		change(() -> {
			stage = new Stage();
			
			// Remove window decoration
			//stage.initStyle(StageStyle.UNDECORATED);
			
			browser = new WebView();
			webEngine = browser.getEngine();
			
			stage.setScene(new Scene(browser, 400, 400));
			stage.show();
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