package me.Markcreator.Proton.ui.layout;

import java.awt.Window.Type;

import me.Markcreator.Proton.ui.WebPane;

public enum WebPaneLayout {

	DEFAULT (new WebPaneLayoutBuilder() {
		public void build(WebPane pane) {
			pane.getWebView().setContextMenuEnabled(false);
		}
	}),
	
	POPUP (new WebPaneLayoutBuilder() {
		public void build(WebPane pane) {
			pane.setType(Type.UTILITY);
			pane.setUndecorated(true);
			pane.setAlwaysOnTop(true);
		}
	}),
	
	APPLICATION (new WebPaneLayoutBuilder() {
		public void build(WebPane pane) {
			
		}
	});
	
	private WebPaneLayoutBuilder builder;
	
	WebPaneLayout(WebPaneLayoutBuilder builder) {
		this.builder = builder;
	}
	
	public void build(WebPane pane) {
		builder.build(pane);
	}
}
