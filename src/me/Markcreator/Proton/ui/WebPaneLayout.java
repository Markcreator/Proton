package me.Markcreator.Proton.ui;

import java.awt.Window.Type;

import me.Markcreator.Proton.util.Command;
import me.Markcreator.Proton.util.ReflectionUtils;

public enum WebPaneLayout {

	DEFAULT (new Command[] {
		
	}),
	
	POPUP (new Command[] {
		new Command("setType", Type.UTILITY),
		new Command("setUndecorated", true),
		new Command("setAlwaysOnTop", true)
	}),
	
	APPLICATION (new Command[] {
		
	});
	
	private Command[] builder;
	
	WebPaneLayout(Command[] builder) {
		this.builder = builder;
	}
	
	public void build(WebPane pane) {
		for(Command command : builder) {
			ReflectionUtils.invoke(pane, command.getCommand(), command.getArgs());
		}
	}
}
