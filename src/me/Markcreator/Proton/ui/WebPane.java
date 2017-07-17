package me.Markcreator.Proton.ui;

import javax.swing.JFrame;

import com.sun.javafx.webkit.WebConsoleListener;

import javafx.application.Platform;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import me.Markcreator.Proton.event.EventCaller;
import me.Markcreator.Proton.event.SingleEventWaiter;
import me.Markcreator.Proton.event.events.WebViewManagerLoadedEvent;
import me.Markcreator.Proton.event.events.WebPageAlertEvent;
import me.Markcreator.Proton.event.events.WebPageLoadedEvent;
import me.Markcreator.Proton.ui.layout.WebPaneLayout;
import me.Markcreator.Proton.ui.layout.WebPaneLayoutBuilder;
import netscape.javascript.JSObject;

@SuppressWarnings({ "serial", "restriction" })
public class WebPane extends JFrame implements EventCaller {

	private WebView browser;
	private WebEngine webEngine;

	public WebPane() {
		if(!WebViewManager.isLoaded()) {
			// Create the view manager to handle the UI thread
			WebViewManager.loadViewManager();

			// Wait for the view manager to initialize
			new SingleEventWaiter(WebViewManager.getInstance(), WebViewManagerLoadedEvent.class);
		}
		
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

	public void registerEvents() {
		WebPane pane = this;

		webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
			public void handle(WebEvent<String> event) {
				callEvent(new WebPageAlertEvent(pane, event.getData()));

				System.out.println(event.getData());
			}
		});

		WebConsoleListener.setDefaultListener(new WebConsoleListener() {
			public void messageAdded(WebView view, String err, int line, String file) {
				if(view == getWebView()) {
					System.err.println("Error in file '" + file + "'; " + err + " at line " + line);
				}
			}
		});
		
		getWebEngine().getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
			if (newState == State.SUCCEEDED) {
				shareObject("app", pane);

				callEvent(new WebPageLoadedEvent(pane));
			}
		});
	}

	public void shareObject(String name, Object obj) {
		JSObject win = (JSObject) getWebEngine().executeScript("window");
		win.setMember(name, obj);
		getWebEngine().executeScript("try { onJavaObjectShared(" + name + ") } catch(e) { alert('onJavaObjectShared(name) is not defined on page!') }");

		System.out.println("Shared " + obj.getClass().getSimpleName());
	}

	// Layouts
	public void loadLayout(WebPaneLayout layout) {
		layout.build(this);
	}

	public void loadLayout(WebPaneLayoutBuilder builder) {
		builder.build(this);
	}

	// Configuration methods
	public void shutdownOnClose() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// Run UI changes on the UI thread
	public void change(Runnable r) {
		Platform.runLater(r);
	}
}

// https://stackoverflow.com/questions/9861178/javafx-primarystage-remove-windows-borders
// http://docs.oracle.com/javafx/2/webview/jfxpub-webview.htm
