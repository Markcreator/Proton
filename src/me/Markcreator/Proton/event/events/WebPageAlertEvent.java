package me.Markcreator.Proton.event.events;

import me.Markcreator.Proton.event.Event;
import me.Markcreator.Proton.event.EventCaller;

public class WebPageAlertEvent extends Event {

	private String message;
	
	public WebPageAlertEvent(EventCaller caller, String message) {
		super(caller);
		
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
