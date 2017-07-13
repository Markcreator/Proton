package me.Markcreator.Proton.ui;

import java.awt.Window.Type;

import me.Markcreator.Proton.util.MethodCall;
import me.Markcreator.Proton.util.ReflectionUtils;

public enum WebPaneLayout {

	DEFAULT (new MethodCall[] {
		
	}),
	
	POPUP (new MethodCall[] {
		new MethodCall("setType", Type.UTILITY),
		new MethodCall("setUndecorated", true),
		new MethodCall("setAlwaysOnTop", true)
	}),
	
	APPLICATION (new MethodCall[] {
		
	});
	
	private MethodCall[] builder;
	
	WebPaneLayout(MethodCall[] builder) {
		this.builder = builder;
	}
	
	public void build(WebPane pane) {
		for(MethodCall command : builder) {
			ReflectionUtils.invoke(pane, command.getMethod(), command.getArgs());
		}
	}
}
