package me.Markcreator.Proton;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WebPane {

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
			Stage stage = new Stage();
			
			// Remove window decoration
			stage.initStyle(StageStyle.UNDECORATED);

			browser = new WebView();
			webEngine = browser.getEngine();
			
			stage.setScene(new Scene(browser, 400, 400));
			stage.show();
		});
	}
	
	public void change(Runnable r) {
		Platform.runLater(r);
	}
}

// https://stackoverflow.com/questions/9861178/javafx-primarystage-remove-windows-borders
// http://docs.oracle.com/javafx/2/webview/jfxpub-webview.htm